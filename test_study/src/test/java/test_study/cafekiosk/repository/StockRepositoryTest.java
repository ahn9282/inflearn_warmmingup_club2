package test_study.cafekiosk.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import test_study.cafekiosk.domain.Product;
import test_study.cafekiosk.domain.Stock;
import test_study.cafekiosk.service.OrderService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static test_study.cafekiosk.domain.ProductSellingStatus.*;
import static test_study.cafekiosk.domain.ProductType.BAKERY;
import static test_study.cafekiosk.domain.ProductType.HANDMADE;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class StockRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderProductRepository orderProductRepository;
    @Autowired
    StockRepository stockRepository;

    @Autowired
    OrderService orderService;



    @DisplayName("상품 번호 리스트로 재고를 조회")
    @Test
    void findAllByProductNumberInTest() {
        //Given
        initProducts();
        Stock stock1 = Stock.create("001", 1);
        Stock stock2 = Stock.create("002", 2);
        Stock stock3 = Stock.create("003", 3);
        stockRepository.saveAll(List.of(stock1, stock2, stock3));

        //When
        List<Stock> stocks = stockRepository.findAllByProductNumberIn(List.of("001", "002"));


        //Then
        assertThat(stocks).hasSize(2)
                .extracting("productNumber", "quantity")
                .containsExactlyInAnyOrder(
                        tuple("001", 1),
                        tuple("002", 2)
                );

    }

    private void initProducts() {
        Product product1 = Product.builder()
                .productNumber("001")
                .name("테스트 상품1")
                .type(HANDMADE)
                .price(4000)
                .sellingStatus(SELLING)
                .build();

        Product product2 = Product.builder()
                .productNumber("002")
                .name("테스트 상품2")
                .type(HANDMADE)
                .price(3500)
                .sellingStatus(HOLD)
                .build();

        Product product3 = Product.builder()
                .productNumber("003")
                .name("테스트 상품3")
                .type(BAKERY)
                .price(5000)
                .sellingStatus(STOP_SELLING)
                .build();

        productRepository.saveAll(List.of(product1, product2, product3));
    }

}