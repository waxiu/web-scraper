public class Item {
    private String name;
    private String price;
    private String img;

    public Item(){
    }

    public Item(String name, String price, String img) {
        this.name = name;
        this.price = price;
        this.img = img;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", url='" + img + '\'' +
                '}';
    }
}