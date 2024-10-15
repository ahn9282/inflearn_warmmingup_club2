package cleancode.oop.value_object;

public class Money {
    private final long value;

    public Money(long value) {
        if(value <0){
            throw new IllegalArgumentException("돈은 0원 이상이어야 합니다.");
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object thing) {
        if(thing instanceof Money) {
            return this.value == ((Money) thing).value;
        }
        throw new IllegalArgumentException("비교 대상이 문제가 있음");
    }
}
