package test_study.cafekiosk.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponse {


    private Long id;
    private String productNumber;
    private ProductType productType;
    private ProductSellingStatus sellingStatus;
    private String name;
    private int price;

    @Builder
    public ProductResponse(Long id, String productNumber, ProductType productType, ProductSellingStatus sellingStatus, String name, int price) {
        this.id = id;
        this.productNumber = productNumber;
        this.productType = productType;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }

    public static ProductResponse of(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .productNumber(product.getProductNumber())
                .productType(product.getType())
                .sellingStatus(product.getSellingStatus())
                .name(product.getName())
                .price(product.getPrice())
                .build();

    }
}
