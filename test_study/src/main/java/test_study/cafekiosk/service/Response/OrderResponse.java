package test_study.cafekiosk.service.Response;

import lombok.Builder;
import lombok.Getter;
import test_study.cafekiosk.domain.Order;
import test_study.cafekiosk.domain.OrderStatus;
import test_study.cafekiosk.dto.ProductResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderResponse {


    private Long id;
    private OrderStatus orderStatus;
    private int totalPrice;
    private LocalDateTime registeredDateTime;
    private List<ProductResponse> products;

    @Builder
    public OrderResponse(Long id, OrderStatus orderStatus, int totalPrice, LocalDateTime registeredDateTime, List<ProductResponse> products) {

        this.id = id;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.registeredDateTime = registeredDateTime;
        this.products = products;
    }

    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                        .totalPrice(order.getTotalPrice())
                .registeredDateTime(order.getRegisteredDateTime())
                .products(order.getOrderProducts().stream()
                        .map(orderProduct -> ProductResponse.of(orderProduct.getProduct()))
                        .collect(Collectors.toList()))
                .build();
    }
}
