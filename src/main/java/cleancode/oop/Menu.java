package cleancode.oop;

public class Menu {

    private String name;
    private int price;

    public Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Menu() {
    }

    public int getPrice() {
        return price;
    }
}
