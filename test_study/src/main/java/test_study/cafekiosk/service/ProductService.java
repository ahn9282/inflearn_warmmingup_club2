package test_study.cafekiosk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test_study.cafekiosk.controller.ProductCreateRequest;
import test_study.cafekiosk.domain.Product;
import test_study.cafekiosk.dto.ProductResponse;
import test_study.cafekiosk.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

import static test_study.cafekiosk.domain.ProductSellingStatus.forDisplay;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getSellingProducts() {

        List<Product> products = productRepository.findAllBySellingStatusIn(forDisplay());

        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    public ProductResponse createProduct(ProductCreateRequest request) {

        String nextProductNumber = createNextProductNumber();
        Product product = request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.builder()
                .id(savedProduct.getId())
                .productNumber(nextProductNumber)
                .productType(request.getType())
                .sellingStatus(request.getStatus())
                .price(request.getPrice())
                .name(request.getName())
                .build();
    }

    private String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProduct();
        if(latestProductNumber == null) return  String.format("%03d",1);
        return String.format("%03d", Integer.parseInt(latestProductNumber) + 1);
    }

}
