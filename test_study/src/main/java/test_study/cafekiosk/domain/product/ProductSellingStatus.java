package test_study.cafekiosk.domain.product;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public enum ProductSellingStatus {

    SELLING("판매 중"),
    HOLD("판매 보류"),
    STOP_SELLING("판매중지");

    private final String text;

    public static List<ProductSellingStatus> forDisplay() {

        return List.of(SELLING, HOLD);
    }
}
