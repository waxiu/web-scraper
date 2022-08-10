import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CeneoWebScraper extends WebScraperUtil implements WebScraper {
    private final String url;
    private List<Elements> listOfElements;

    public CeneoWebScraper(String url) {
        this.url = url;
        generateElements();
    }



    public void generateElements() {
        try {
            List<Elements> listOfElements = new ArrayList<>();
            Document doc = Jsoup.connect(url).get();
            String temp = "X";
            Elements elements;
            while(!temp.isEmpty()) {
                try {
                    elements = doc.getElementsByClass("category-list-body js_category-list-body js_search-results js_products-list-main js_async-container");
                    listOfElements.add(elements);
                    temp = doc.getElementsByClass("pagination__item pagination__next").first().absUrl("href");
                    doc = Jsoup.connect(temp).get();
                }catch (Exception e) {
                    temp = "";
                }
            }
            this.listOfElements = listOfElements;
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<String> generateListOfNames() {
        List<String> listOfNames = new ArrayList<>();
        List<String> tempList;
        for(Elements elements : this.listOfElements) {
            tempList = elements.first().getElementsByClass("cat-prod-row__name").eachText();
            listOfNames.addAll(tempList);
        }
        return listOfNames;
    }
    public List<String> generateListOfPrices() {
        List<String> tempList;
        List<String> listOfValue = new ArrayList<>();
        List<String> listOfPenny = new ArrayList<>();
        for(Elements elements : this.listOfElements) {
            tempList = elements.first().getElementsByClass("value").eachText();
            listOfValue.addAll(tempList);
            tempList = elements.first().getElementsByClass("penny").eachText();
            listOfPenny.addAll(tempList);
        }
        List<String> listOfPrices = new ArrayList<>();
        for(int i = 0; i < listOfValue.size(); i++) {
            String finalPrice = listOfValue.get(i) + listOfPenny.get(i);
            listOfPrices.add(finalPrice);
        }
        return listOfPrices;

    }
    public List<String> generateListOfImages() {
        List<String> listOfImages = new ArrayList<>();
        List<String> tempList;
        for(Elements elements : this.listOfElements) {
            tempList = elements.first().getElementsByTag("img").eachAttr("abs:src");
            listOfImages.addAll(tempList);
        }
        return listOfImages;
    }
    public List<Item> generateListOfItems() {
        List<Item> listOfItems = new ArrayList<>();
        List<String> listOfNames = generateListOfNames();
        List<String> listOfPrices = generateListOfPrices();
        List<String> listOfImages = generateListOfByte64Images(generateListOfImages());
        for(int i = 0; i < listOfNames.size(); i++) {
            listOfItems.add(new Item(listOfNames.get(i), listOfPrices.get(i), listOfImages.get(i)));
        }
        return listOfItems;
    }
}
