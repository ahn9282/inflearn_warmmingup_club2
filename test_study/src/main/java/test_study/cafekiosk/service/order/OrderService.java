package test_study.cafekiosk.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test_study.cafekiosk.domain.order.Order;
import test_study.cafekiosk.domain.product.Product;
import test_study.cafekiosk.domain.product.ProductType;
import test_study.cafekiosk.domain.Stock;
import test_study.cafekiosk.repository.OrderRepository;
import test_study.cafekiosk.repository.ProductRepository;
import test_study.cafekiosk.repository.StockRepository;
import test_study.cafekiosk.request.OrderCreateRequest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final StockRepository stockRepository;


    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredDateTime) {

        List<String> productNumbers = request.getProductsNumbers();
        List<Product> duplicateProducts = findProductsBy(productNumbers);

        List<String> stockProductNumbers = duplicateProducts.stream()
                .filter(product -> ProductType.containsStockType(product.getType()))
                .map(Product::getProductNumber)
                .toList();

        List<Stock> stocks = stockRepository.findAllByProductNumberIn(stockProductNumbers);
        Map<String, Stock> stockMap = stocks.stream()
                .collect(Collectors.toMap(Stock::getProductNumber, s -> s));

        Map<String, Long> productNumberCountMap = stockProductNumbers.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        for (String stockProductNumber : new HashSet<>(stockProductNumbers)) {
            Stock stock = stockMap.get(stockProductNumber);
            int quantity = productNumberCountMap.get(stockProductNumber).intValue();

            stock.deductQuantity(quantity);
        }


        Order order =  Order.create(duplicateProducts,registeredDateTime);
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.of(savedOrder);
    }

    private List<Product> findProductsBy(List<String> productNumbers) {
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);
        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductNumber, p -> p));
        return productNumbers.stream()
                .map(productMap::get)
                .collect(Collectors.toList());

    }
}
