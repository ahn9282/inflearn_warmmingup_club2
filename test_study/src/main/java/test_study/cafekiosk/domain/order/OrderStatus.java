package test_study.cafekiosk.domain.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {

    INIT("주문생성"),
    CANCELED("주문 취소"),
    PAYMENT_COMPLETED("결제완료"),
    PAYMENT_FAILED("결제 실패"),
    RECEIVED("주문 접수"),
    COMPLETED("처리 완료");

    private final String description;

}
