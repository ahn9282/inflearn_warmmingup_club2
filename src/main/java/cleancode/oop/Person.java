package cleancode.oop;

public class Person {
    private String 지갑;
    private String 신분증;
    private int age;
    private String name;

    public int findAge(){
        return age;
    }

    public Person() {

    }

    public Person(int age) {
        this.age = age;
    }

    public boolean isAgeGraterThanOrEqualTo(int value) {
        return value <= age;
    }
}
