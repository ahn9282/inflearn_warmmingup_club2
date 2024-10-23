package test_study.cafekiosk.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import test_study.cafekiosk.controller.ProductCreateRequest;
import test_study.cafekiosk.domain.Product;
import test_study.cafekiosk.dto.ProductResponse;
import test_study.cafekiosk.domain.ProductSellingStatus;
import test_study.cafekiosk.domain.ProductType;
import test_study.cafekiosk.repository.ProductRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static test_study.cafekiosk.domain.ProductSellingStatus.*;
import static test_study.cafekiosk.domain.ProductType.HANDMADE;
@ActiveProfiles("test")
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;


    @DisplayName("등록된 상품이 없을 경우. 상품 번호는 가장 최근 상품번호는 001")
    @Test
    void createProductNonExistProduct() {
        //Given

        ProductCreateRequest request =  ProductCreateRequest.builder()
                .type(HANDMADE)
                .status(SELLING)
                .name("캬라멜 마끼야또")
                .price(5000)
                .build();

        //When
        ProductResponse productResponse = productService.createProduct(request);

        //Then
        assertThat(productResponse)
                .extracting("productNumber", "productType", "sellingStatus", "name", "price")
                .contains("001", HANDMADE, SELLING, "캬라멜 마끼야또", 5000);


    }

    @DisplayName("신규 상품을 등록한다. 상품 번호는 가장 최근 상품번호에서 1 증가한 번호로")
    @Test
    void createProduct() {
        //Given
        Product product1 = createProduct("001", HANDMADE,SELLING, "아메리카노", 4000);
//        Product product2 = createProduct("002", HANDMADE,HOLD, "카페라떼", 4500);
//        Product product3 = createProduct("003", HANDMADE,STOP_SELLING, "팥빙수", 7000);
        productRepository.saveAll(List.of(product1));

        ProductCreateRequest request =  ProductCreateRequest.builder()
                .type(HANDMADE)
                .status(SELLING)
                .name("카푸치노")
                .price(5000)
                .build();

        //When
        ProductResponse productResponse = productService.createProduct(request);

        //Then
        assertThat(productResponse)
                .extracting("productNumber", "productType", "sellingStatus", "name", "price")
                .contains("002", HANDMADE, SELLING, "카푸치노", 5000);


    }

    private Product createProduct(String number, ProductType productType, ProductSellingStatus status, String name, int price) {
        return Product.builder()
                .productNumber(number)
                .type(productType)
                .sellingStatus(status)
                .name(name)
                .price(price)
                .build();
    }



}