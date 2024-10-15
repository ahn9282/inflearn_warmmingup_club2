package cleancode.day4_ex;

public class day4Main {
    private static final MyLogger log = new MyLogger();

    public static void main(String[] args) {

    }

    public boolean validateOrder(Order order) {
        if (order.isEmptyItemsInOrder()) {
            log.info("주문 항목이 없습니다.");
            return false;
        }
        try{
        order.validateTotalPrice();
        }catch(IllegalArgumentException e){
            log.info(e.getMessage());
            return false;
        }
        if (order.hasNotInfo()) {
            log.info("사용자 정보가 없습니다.");
            return false;
        }
        return true;
    }
}
