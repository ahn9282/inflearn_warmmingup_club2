package test_study.cafekiosk.unit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Order {
    private final LocalDateTime orderDate;
    private List<Beverage> beverages;

}
