package test_study.cafekiosk.reposiotry;

import org.springframework.data.jpa.repository.JpaRepository;
import test_study.cafekiosk.domain.Product;
import test_study.cafekiosk.domain.ProductSellingStatus;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllBySellingStatusIn(List<ProductSellingStatus> sellingStatuses);
}

