package test_study.cafekiosk.unit;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CafeKiosk {

    private static final LocalTime SHOP_OPEN_TIME = java.time.LocalTime.of(10, 0);
    private static final LocalTime SHOP_CLOSE_TIME = LocalTime.of(22, 0);

    private final List<Beverage> beverages = new ArrayList<>();

    public void add(Beverage beverage) {
        beverages.add(beverage);
    }

    public int calculateTotalPrice() {
        return   beverages.stream().mapToInt(Beverage::getPrice).sum();
    }

    public void remove(Beverage beverage) {
        beverages.remove(beverage);
    }

    public void clear(){
        beverages.clear();
    }

    public Order createOrder() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalTime currentTime = currentDateTime.toLocalTime();
        if (currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isBefore(SHOP_CLOSE_TIME)) {
            throw new IllegalArgumentException("주문 시간이 아닙니다. 관리자에게 문의하세요.");
        }

        return new Order(LocalDateTime.now(), beverages);

    }

    public Order createOrder(LocalDateTime time) {
        LocalTime currentTime = time.toLocalTime();
        if (currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isBefore(SHOP_CLOSE_TIME)) {
            throw new IllegalArgumentException("주문 시간이 아닙니다. 관리자에게 문의하세요.");
        }

        return new Order(LocalDateTime.now(), beverages);

    }

    public void add(Beverage beverage, int count) {
        if(count <=0){
            throw new IllegalArgumentException("0 이하는 추가할 수 없습니다. ");
        }
        for (int i = 0; i < count; i++) {
            beverages.add(beverage);
        }
    }

}
