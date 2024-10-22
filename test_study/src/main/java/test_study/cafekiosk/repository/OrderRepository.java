package test_study.cafekiosk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test_study.cafekiosk.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
