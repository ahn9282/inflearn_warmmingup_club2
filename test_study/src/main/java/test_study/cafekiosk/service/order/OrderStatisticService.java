package test_study.cafekiosk.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test_study.cafekiosk.domain.order.Order;
import test_study.cafekiosk.domain.order.OrderStatus;
import test_study.cafekiosk.repository.OrderRepository;
import test_study.cafekiosk.service.mail.MailService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderStatisticService {

    private final OrderRepository orderRepository;
    private final MailService mailService;

    public boolean sendOrderStatisticsMail(LocalDate orderDate, String email) {
        // 해당 일자에 해당 결제완료된 주문들을 가져와서
        List<Order> orders = orderRepository.findOrdersBy(
                orderDate.atStartOfDay(),
                orderDate.plusDays(1).atStartOfDay(),
                OrderStatus.PAYMENT_COMPLETED
        );

        //총 매출 합계를 계산하고
        int totalAmount = orders.stream()
                .mapToInt(Order::getTotalPrice)
                .sum();



        //메일 전송
        boolean result =  mailService.sendMail(
                "no-reply@cafekiosk.com", email,
                String.format("[매출 통계] %s", orderDate),
                String.format("총 매출액 합계는 %s원입니다.", totalAmount)
        );
        if(!result){
            throw new IllegalArgumentException("매출 통계 메일 전송에 실패했습니다.");
        }

        return true;
    }
}
