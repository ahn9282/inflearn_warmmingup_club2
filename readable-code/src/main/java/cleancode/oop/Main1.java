package cleancode.oop;

public class Main1 {
    public static void main(String[] args) {
        Person person = new Person(20);

        if(person.isAgeGraterThanOrEqualTo(19)){
            System.out.println("연령 인증 완료");
        }else{
            System.out.println("연령 인증 실패");

        }
    }

}
