package test_study.cafekiosk.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static test_study.cafekiosk.domain.OrderStatus.INIT;
import static test_study.cafekiosk.domain.ProductSellingStatus.SELLING;
import static test_study.cafekiosk.domain.ProductType.*;

class OrderTest {

    @DisplayName("상품 리스트에서 주문의 총 금액을 계산한다.")
    @Test
    void testTotalPriceInCreateOrder() {
        //Given
        List<Product> products = List.of(createProduct("001", 1000), createProduct("002", 2000));
        LocalDateTime now = LocalDateTime.now();

        //When
        Order order = Order.create(products, now);

        //Then
        assertThat(order.getTotalPrice()).isEqualTo(3000);
        assertThat(order.getOrderStatus()).isEqualTo(INIT);
        assertThat(order.getRegisteredDateTime()).isEqualTo(now);

    }
    private Product createProduct( String productNumber, int price){
        return Product.builder()
                .productNumber(productNumber)
                .type(HANDMADE)
                .price(price)
                .sellingStatus(SELLING)
                .name("메뉴 이름")
                .build();
    }

}