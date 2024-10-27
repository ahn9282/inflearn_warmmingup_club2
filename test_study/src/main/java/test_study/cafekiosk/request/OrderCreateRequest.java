package test_study.cafekiosk.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCreateRequest {

    private List<String> productsNumbers ;

    @Builder
    public OrderCreateRequest(List<String> productsNumbers) {
        this.productsNumbers = productsNumbers;
    }

    public OrderCreateRequest() {
    }
}
