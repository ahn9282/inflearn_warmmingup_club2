package test_study.cafekiosk.unit;

public class CafeKioskRunner {
    public static void main(String[] args) {

        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        String x = ">>> 아메리카노 추가";
        System.out.println(x);

        cafeKiosk.add(new Latte());
        String x1 = ">>> 라떼 추가";
        System.out.println(x1);
        int totalPrice = cafeKiosk.calculateTotalPrice();
        String s = "총 주문 가격 : ";
        System.out.println(s + totalPrice);
    }
}
