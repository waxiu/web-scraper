import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XmlSaver implements ItemFileSaver {

    public void save(List<Item> listOfItems) {
        try{
            FileOutputStream fos = new FileOutputStream(new File("src/main/resources", "items.xml"));
            XMLEncoder encoder = new XMLEncoder(fos);
            for(Item item : listOfItems) {
                encoder.writeObject(item);
            }
            encoder.close();
            fos.close();
            System.out.println("Data was successfully exported to XML file :) ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
