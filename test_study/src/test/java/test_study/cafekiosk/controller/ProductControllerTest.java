package test_study.cafekiosk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import test_study.cafekiosk.domain.ProductSellingStatus;
import test_study.cafekiosk.domain.ProductType;
import test_study.cafekiosk.dto.ProductResponse;
import test_study.cafekiosk.service.ProductService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ActiveProfiles("test")
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @DisplayName("신규 상품을 생성한다.")
    @Test
    void createProduct() throws Exception {
        //Given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(ProductType.HANDMADE)
                .status(ProductSellingStatus.SELLING)
                .name("아메리카노")
                .price(4000)
                .build();

        //When
        //Then
        mockMvc.perform(post("/api/v1/products/new")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @DisplayName("신규 상품을 등록 시 상품타입은 필수 값이다.")
    @Test
    void createProductWithoutProductType() throws Exception {
        //Given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .status(ProductSellingStatus.SELLING)
                .name("아메리카노")
                .price(4000)
                .build();

        //When
        //Then
        mockMvc.perform(
                        post("/api/v1/products/new")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("상품 타입은 필수입니다."))
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.code").value("400"));


    }

    @DisplayName("판매상품을 조회한다.")
    @Test
    void getSellingProducts() throws Exception {
        //Given
        List<ProductResponse> result = List.of();
        when(productService.getSellingProducts()).thenReturn(result);

        //When
        //Then
        mockMvc.perform(
                        get("/api/v1/products/selling")
                                .accept(MediaType.APPLICATION_JSON)

                        // .queryParam("name","이름")

                )
                .andDo(print())
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value("200"))
//                .andExpect(jsonPath("$.status").value("OK"))
//                .andExpect(jsonPath("$.message").value("OK"))
//                .andExpect(jsonPath("$.data").isArray())
               ;



    }

}