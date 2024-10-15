package cleancode.oop.value_object;

public class Main1 {
    public static void main(String[] args) {
        Money money1 = new Money(1_000L);
        Money money2 = new Money(1_000L);

        if(money1 == money2){
            System.out.println("False");

        }
        if(money1.equals(money2)){
            System.out.println("True");

        }
    }
}
