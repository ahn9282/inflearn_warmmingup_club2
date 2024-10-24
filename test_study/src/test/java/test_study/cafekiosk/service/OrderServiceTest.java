package test_study.cafekiosk.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import test_study.cafekiosk.domain.Product;
import test_study.cafekiosk.domain.ProductType;
import test_study.cafekiosk.domain.Stock;
import test_study.cafekiosk.repository.OrderProductRepository;
import test_study.cafekiosk.repository.OrderRepository;
import test_study.cafekiosk.repository.ProductRepository;
import test_study.cafekiosk.repository.StockRepository;
import test_study.cafekiosk.request.OrderCreateRequest;
import test_study.cafekiosk.service.order.OrderResponse;
import test_study.cafekiosk.service.order.OrderService;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static test_study.cafekiosk.domain.ProductSellingStatus.*;
import static test_study.cafekiosk.domain.ProductType.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class OrderServiceTest {

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

    @AfterEach
    void tearDown(){
       // productRepository.deleteAll();
        orderProductRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
    }

    @DisplayName("재고와 관련된 상품이 포함되어 있는 주문 번호 리스트를 받아 주문을 생성한다.")
    @Test
    void createOrderWithStock1() {
        //Given
        LocalDateTime now = LocalDateTime.now();

        Product product1 = createProduct(BOTTLE, "001", 1000);
        Product product2 = createProduct(BAKERY, "002", 3000);
        Product product3 = createProduct(HANDMADE, "003", 5000);
        productRepository.saveAll(List.of(product1, product2, product3));

        Stock stock1 = Stock.create("001",2);
        Stock stock2 = Stock.create("002",2);
        Stock stock3 = Stock.create("003",1);
        stockRepository.saveAll(List.of(stock1, stock2, stock3));

        OrderCreateRequest request = OrderCreateRequest.builder()
                .productsNumbers(List.of("001", "001", "002", "003"))
                .build();

        //When
        OrderResponse orderResponse = orderService.createOrder(request, now);

        //Then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse)
                .extracting("registeredDateTime", "totalPrice")
                .contains(now,10000);
        assertThat(orderResponse.getProducts()).hasSize(4)
                .extracting("productNumber", "price")
                .containsExactlyInAnyOrder(
                        tuple("001", 1000),
                        tuple("001", 1000),
                        tuple("003", 5000),
                        tuple("002", 3000)
                );


        List<Stock> stocks = stockRepository.findAll();
        assertThat(stocks).hasSize(3)
                .extracting("productNumber", "quantity")
                .containsExactlyInAnyOrder(
                        tuple("001", 0),
                        tuple("002", 1),
                        tuple("003", 1)
                );
    }

    @DisplayName("재고가 없는 상품으로 주문을 생성하려는 경우 예외가 발생")
    @Test
    void createOrderWithNoStock() {
        //Given
        LocalDateTime now = LocalDateTime.now();

        Product product1 = createProduct(BOTTLE, "001", 1000);
        Product product2 = createProduct(BAKERY, "002", 3000);
        Product product3 = createProduct(HANDMADE, "003", 5000);
        productRepository.saveAll(List.of(product1, product2, product3));

        Stock stock1 = Stock.create("001",1);
        Stock stock2 = Stock.create("002",2);
        Stock stock3 = Stock.create("003",1);
        stockRepository.saveAll(List.of(stock1, stock2, stock3));

        OrderCreateRequest request = OrderCreateRequest.builder()
                .productsNumbers(List.of("001", "001", "002", "003"))
                .build();

        //When
        //Then
        assertThatThrownBy(() -> orderService.createOrder(request, now))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("차감할 재고 수량이 없습니다.");
    }


    @DisplayName("재고와 관련된 상품이 포함되어 있는 주문 번호 리스트를 받아 주문을 생성한다.")
    @Test
    void createOrderWithStock() {
        //Given
        LocalDateTime now = LocalDateTime.now();

        Product product1 = createProduct(BOTTLE, "001", 1000);
        Product product2 = createProduct(BAKERY, "002", 3000);
        Product product3 = createProduct(HANDMADE, "003", 5000);
        productRepository.saveAll(List.of(product1, product2, product3));

        Stock stock1 = Stock.create("001",2);
        Stock stock2 = Stock.create("002",2);
        Stock stock3 = Stock.create("003",1);
        stockRepository.saveAll(List.of(stock1, stock2, stock3));

        OrderCreateRequest request = OrderCreateRequest.builder()
                .productsNumbers(List.of("001", "001", "002", "003"))
                .build();

        //When
        OrderResponse orderResponse = orderService.createOrder(request, now);

        //Then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse)
                .extracting("registeredDateTime", "totalPrice")
                .contains(now,10000);
        assertThat(orderResponse.getProducts()).hasSize(4)
                .extracting("productNumber", "price")
                .containsExactlyInAnyOrder(
                        tuple("001", 1000),
                        tuple("001", 1000),
                        tuple("003", 5000),
                        tuple("002", 3000)
                );


        List<Stock> stocks = stockRepository.findAll();
        assertThat(stocks).hasSize(3)
                .extracting("productNumber", "quantity")
                .containsExactlyInAnyOrder(
                        tuple("001", 0),
                        tuple("002", 1),
                        tuple("003", 1)
                );
    }


    @DisplayName("주문 리스트를 받아 주문을 생성한다.")
    @Test
    void testGenerateOrder() {
        //Given
        LocalDateTime now = LocalDateTime.now();

        Product product1 = createProduct(HANDMADE, "001", 1000);
        Product product2 = createProduct(HANDMADE, "002", 3000);
        Product product3 = createProduct(BAKERY, "003", 5000);
        productRepository.saveAll(List.of(product1, product2, product3));

        OrderCreateRequest request = OrderCreateRequest.builder()
                .productsNumbers(List.of("001", "002"))
                .build();

        //When
        OrderResponse orderResponse = orderService.createOrder(request, now);

        //Then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse)
                .extracting("registeredDateTime", "totalPrice")
                .contains(now,4000);
        assertThat(orderResponse.getProducts()).hasSize(2)
                .extracting("productNumber", "price")
                .containsExactlyInAnyOrder(
                        tuple("001", 1000),
                        tuple("002", 3000)
                );

    }
    private Product createProduct(ProductType type, String productNumber, int price){
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .price(price)
                .sellingStatus(SELLING)
                .name("메뉴 이름")
                .build();
    }

}