package test_study.cafekiosk.service.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import test_study.cafekiosk.client.mail.MailSendClient;
import test_study.cafekiosk.domain.Product;
import test_study.cafekiosk.domain.ProductType;
import test_study.cafekiosk.domain.history.MailSendHistory;
import test_study.cafekiosk.domain.order.Order;
import test_study.cafekiosk.domain.order.OrderStatus;
import test_study.cafekiosk.repository.MailSendHistoryRepository;
import test_study.cafekiosk.repository.OrderProductRepository;
import test_study.cafekiosk.repository.OrderRepository;
import test_study.cafekiosk.repository.ProductRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static test_study.cafekiosk.domain.ProductSellingStatus.SELLING;
import static test_study.cafekiosk.domain.ProductType.HANDMADE;


@SpringBootTest
class OrderStatisticServiceTest {

    @Autowired
    OrderProductRepository orderProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private OrderStatisticService orderStatisticService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    MailSendHistoryRepository mailSendHistoryRepository;

    @MockBean
    private MailSendClient mailSendClient;


    @AfterEach
    void tearDown() {

        orderProductRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        mailSendHistoryRepository.deleteAllInBatch();

    }


    @DisplayName("결제 완료 주문둘의 매출 통계 메일을 전송한다.")
    @Test
    void sendOrderStatisticsMail() {
        //Given
        LocalDateTime time = LocalDateTime.of(2023, 3, 5, 0, 0);

        Product product1 = createProduct(HANDMADE, "001", 1000);
        Product product2 = createProduct(HANDMADE, "002", 2000);
        Product product3 = createProduct(HANDMADE, "003", 3000);
        List<Product> products = List.of(product1, product2, product3);
        productRepository.saveAll(products);

        Order order1 = createPaymentCompletedOrder(products, LocalDateTime.of(2023, 3, 4, 23, 59, 59));
        Order order2 = createPaymentCompletedOrder(products, time);
        Order order3 = createPaymentCompletedOrder(products, LocalDateTime.of(2023, 3, 5, 23, 59, 59));
        Order order4 = createPaymentCompletedOrder(products, LocalDateTime.of(2023, 3, 6, 0, 0));
        orderRepository.saveAll(List.of(order1, order2, order3, order4));

        Mockito.when(mailSendClient.sendEmail(any(String.class), any(String.class), any(String.class), any(String.class)))
                .thenReturn(true);

        //When
        boolean result = orderStatisticService.sendOrderStatisticsMail(LocalDate.of(2023, 3, 5), "test@test.com");

        //Then
        assertThat(result).isTrue();


        List<MailSendHistory> histories = mailSendHistoryRepository.findAll();
        System.out.println("histories = " + histories);
        assertThat(histories).hasSize(1)
                .extracting("content")
                .contains("총 매출액 합계는 12000원입니다.");

    }

    private static Order createPaymentCompletedOrder(List<Product> products, LocalDateTime now) {
        return Order.builder()
                .products(products)
                .registeredDateTime(now)
                .orderStatus(OrderStatus.PAYMENT_COMPLETED)
                .totalPrice(products.stream().mapToInt(Product::getPrice).sum())
                .build();
    }

    private Product createProduct(ProductType type, String productNumber, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .price(price)
                .sellingStatus(SELLING)
                .name("메뉴 이름")
                .build();
    }


}