package test_study.cafekiosk.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import test_study.cafekiosk.IntegrationTestSupport;
import test_study.cafekiosk.controller.ProductCreateRequest;
import test_study.cafekiosk.domain.product.Product;
import test_study.cafekiosk.dto.ProductResponse;
import test_study.cafekiosk.domain.product.ProductSellingStatus;
import test_study.cafekiosk.domain.product.ProductType;
import test_study.cafekiosk.repository.ProductRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static test_study.cafekiosk.domain.product.ProductSellingStatus.*;
import static test_study.cafekiosk.domain.product.ProductType.HANDMADE;
@ActiveProfiles("test")
@SpringBootTest
class ProductServiceTest extends IntegrationTestSupport {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;


    @BeforeAll
    static void beforeAll(){
        //before Class
    }
    @BeforeEach
    void setUp(){
        // before method

        //각 테스트 입장에서 봤을 때 :" 아예 몰라도 테스트 내용을 이해하는 데에 문제가 없는가?
        //수정해도 모든 테스트에 영향을 주지 않는가?

    }
    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();

    }


    @DisplayName("등록된 상품이 없을 경우. 상품 번호는 가장 최근 상품번호는 001")
    @Test
    void createProductNonExistProduct() {
        //Given

        ProductCreateRequest request = ProductCreateRequest.builder()
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