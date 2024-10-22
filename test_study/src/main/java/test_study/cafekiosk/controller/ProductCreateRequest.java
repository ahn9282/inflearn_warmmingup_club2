package test_study.cafekiosk.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import test_study.cafekiosk.domain.Product;
import test_study.cafekiosk.domain.ProductSellingStatus;
import test_study.cafekiosk.domain.ProductType;

@Getter
@NoArgsConstructor
public class ProductCreateRequest {

    private ProductType type;
    private ProductSellingStatus status;
    private String name;
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
