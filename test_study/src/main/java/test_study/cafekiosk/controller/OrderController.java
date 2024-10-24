package test_study.cafekiosk.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test_study.cafekiosk.request.OrderCreateRequest;
import test_study.cafekiosk.service.order.OrderService;
import test_study.cafekiosk.service.order.OrderResponse;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public OrderResponse createOrder(@RequestBody OrderCreateRequest orderCreateRequest){
        return orderService.createOrder(orderCreateRequest, LocalDateTime.now());
    }
}
