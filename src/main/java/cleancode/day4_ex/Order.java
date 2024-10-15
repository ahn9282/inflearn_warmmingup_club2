package cleancode.day4_ex;

import java.util.List;

public class Order {
    private List<Item> items;
    private String info;

    public boolean isEmptyItemsInOrder(){ return items.isEmpty();  }

    public boolean validateTotalPrice(){
        long totalPrice = items.stream()
                .mapToLong(item -> item.getPrice())
                .sum();
        if(totalPrice < 0) throw new IllegalArgumentException("올바르지 않은 총 가격입니다.");
        return true;
    }
    public boolean hasNotInfo(){return info == null;}
}
