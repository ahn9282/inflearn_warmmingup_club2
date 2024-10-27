package test_study.cafekiosk.controller;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import test_study.cafekiosk.domain.product.Product;
import test_study.cafekiosk.domain.product.ProductSellingStatus;
import test_study.cafekiosk.domain.product.ProductType;

@Getter
@NoArgsConstructor
public class ProductCreateRequest {

    @NotNull(message = "상품 타입은 필수입니다.")
    private ProductType type;

    @NotNull(message = "상품 판매상태는 필수입니다.")
    private ProductSellingStatus status;

    @NotBlank(message = "상품 이름은 필수입니다.")
    private String name;

    @Positive(message = "상품 가격은 양수가 필수입니다.")
    private int price;

    @Builder
    public ProductCreateRequest(ProductType type, ProductSellingStatus status, String name, int price) {
        this.type = type;
        this.status = status;
        this.name = name;
        this.price = price;
    }

    public Product toEntity(String nextProductNumber) {
        return Product.builder()
                .productNumber(nextProductNumber)
                .type(this.type)
                .sellingStatus(this.status)
                .price(this.price)
                .name(this.name)
                .build();
    }
}
