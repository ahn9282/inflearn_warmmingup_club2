package cleancode.day4_ex;

public class Item {
    private String name;
    private long price;

    public Item(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public Item() {
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }
}
