package test_study.cafekiosk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test_study.cafekiosk.domain.Product;
import test_study.cafekiosk.domain.ProductResponse;
import test_study.cafekiosk.domain.ProductSellingStatus;
import test_study.cafekiosk.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getSellingProducts(){

        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());
        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }
}
