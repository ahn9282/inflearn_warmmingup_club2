package test_study.cafekiosk.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import test_study.cafekiosk.domain.product.Product;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static test_study.cafekiosk.domain.product.ProductSellingStatus.*;
import static test_study.cafekiosk.domain.product.ProductType.BAKERY;
import static test_study.cafekiosk.domain.product.ProductType.HANDMADE;

@ActiveProfiles("test")
//@SpringBootTest
@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;


    @DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어오기, 단 상품이 하나도 없는 경우에는 null을 반환")
    @Test
    void findLatestProductNumberWhenProductNumberIsEmpty() {
        //Given
        productRepository.deleteAll();

        //When
        String result = productRepository.findLatestProductNumber();

        //Then
        assertThat(result).isEqualTo(null);

    }



    @DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어오기")
    @Test
    void findLatestProductNumber() {
        //Given
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

        String targetProductNumber = "003";

        Product product3 = Product.builder()
                .productNumber(targetProductNumber)
                .name("테스트 상품3")
                .type(BAKERY)
                .price(5000)
                .sellingStatus(STOP_SELLING)
                .build();

        productRepository.saveAll(List.of(product1, product2, product3));
        //When
        String result = productRepository.findLatestProductNumber();

        //Then
        assertThat(result).isEqualTo(targetProductNumber);

    }

    @DisplayName("원하는 판매 상태를 가진 상품들을 조회")
    @Test
    void findAllBySellingStatusInTest() {
        //Given
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

        //When
        List<Product> products = productRepository.findAllBySellingStatusIn(List.of(SELLING, HOLD));

        //Then
        assertThat(products).hasSize(2)
                .extracting("productNumber", "name", "sellingStatus")
                .containsExactlyInAnyOrder(
                        tuple("001", "테스트 상품1", SELLING),
                        tuple("002", "테스트 상품2", HOLD)
                );

    }

    @DisplayName("상품 번호 리스트로 상품들을 조회")
    @Test
    void findAllByProductNumberInTest() {
        //Given

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

        //When
        List<Product> products = productRepository.findAllBySellingStatusIn(List.of(SELLING, HOLD));

        //Then
        assertThat(products).hasSize(2)
                .extracting("productNumber", "name", "sellingStatus")
                .containsExactlyInAnyOrder(
                        tuple("001", "테스트 상품1", SELLING),
                        tuple("002", "테스트 상품2", HOLD)
                );

    }

}