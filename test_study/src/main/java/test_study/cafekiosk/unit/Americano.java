package test_study.cafekiosk.unit;

public class Americano implements Beverage {

    @Override
    public int getPrice() {
        return 3500;
    }

    @Override
    public String getName() {
        return "아메리카노";
    }
}
