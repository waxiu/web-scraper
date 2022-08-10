import java.util.List;

public interface WebScraper {
    void generateElements();
    List<String> generateListOfNames();
    List<String> generateListOfPrices();
    List<String> generateListOfImages();
    List<Item> generateListOfItems();
}
