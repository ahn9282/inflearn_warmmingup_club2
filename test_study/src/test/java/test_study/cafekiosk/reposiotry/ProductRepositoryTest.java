package test_study.cafekiosk.reposiotry;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import test_study.cafekiosk.domain.Product;
import test_study.cafekiosk.domain.ProductSellingStatus;
import test_study.cafekiosk.domain.ProductType;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static test_study.cafekiosk.domain.ProductSellingStatus.*;
import static test_study.cafekiosk.domain.ProductType.*;

@ActiveProfiles("test")
//@SpringBootTest
@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

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
                    tuple("001","테스트 상품1", SELLING),
                    tuple("002","테스트 상품2", HOLD)
                );

    }

}