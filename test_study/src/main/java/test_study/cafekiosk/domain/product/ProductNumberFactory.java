package test_study.cafekiosk.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import test_study.cafekiosk.repository.ProductRepository;

@RequiredArgsConstructor
@Component
public class ProductNumberFactory {

    private final ProductRepository productRepository;

    public String createNextProductNumber(){

        String latestProductNumber = productRepository.findLatestProductNumber();

        if(latestProductNumber == null){
            return "001";
        }
        int latestProductNumberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumberInt = latestProductNumberInt + 1;

        return String.format("%03d", nextProductNumberInt);
    }
}
