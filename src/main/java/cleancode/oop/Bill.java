package cleancode.oop;

import java.util.ArrayList;
import java.util.List;

public class Bill {
    private final List<Menu> menus ;
    private final long totalPrice;

    public Bill( ) {
        this.menus = new ArrayList<Menu>();
        menus.add(new Menu("menu1", 15000));
        menus.add(new Menu("menu2", 25000));
        menus.add(new Menu("menu3", 10000));
        menus.add(new Menu("menu4", 9000));
        this.totalPrice = calculateTotalPrice();
    }

    public long calculateTotalPrice(){
        return this.menus.stream()
                .mapToLong(Menu::getPrice)
                .sum();
    }
}
