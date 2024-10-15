package cleancode.day7_ex.model.pass;


import cleancode.day7_ex.model.StudyCafePassType;

public class StudyCafeSeatPass implements StudyCafePass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafeSeatPass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    @Override
    public String toString() {
        return "Duration : " + duration + ", price : " + price;
    }

    public static StudyCafeSeatPass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafeSeatPass(passType, duration, price, discountRate);
    }

    public boolean isSamePassType(StudyCafePassType passType) {
        return this.passType == passType;
    }

    public boolean isSameDurationType(StudyCafeLockerPass pass) {
        return pass.isSamePassType(this.getPassType())
                && pass.isSameDuration(this.getDuration());
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public StudyCafePassType getPassType() {
        return null;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public boolean cannotUserLocker() {

        return this.passType.isNotLockerType();
    }

    public int getDiscountPrice() {
        return (int) (this.getPrice() * this.getDiscountRate());
    }
}
