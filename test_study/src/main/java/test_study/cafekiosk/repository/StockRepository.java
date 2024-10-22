package test_study.cafekiosk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test_study.cafekiosk.domain.Stock;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findAllByProductNumberIn(List<String> productNumbers);
}
