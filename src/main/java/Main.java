import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Paste url to the Ceneo page:");
        String url = scanner.nextLine();

        WebScraper webScraper = new CeneoWebScraper(url);
        ItemFileSaver itemFileSaver = new XmlSaver();
        itemFileSaver.save(webScraper.generateListOfItems());
    }
}

