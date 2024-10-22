package test_study.cafekiosk.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import test_study.cafekiosk.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productNumber;

    private int quantity;

    @Builder
    public Stock(int quantity, String productNumber) {
        this.quantity = quantity;
        this.productNumber = productNumber;
    }

    public static Stock create(String productNumber, int quantity) {
        return Stock.builder()
                .quantity(quantity)
                .productNumber(productNumber)
                .build();

    }

    public boolean isQuantityLessThan(int quantity) {

        return this.quantity < quantity;
    }

    public void deductQuantity(int quantity) {
      if(isQuantityLessThan(quantity)){
          throw new IllegalArgumentException("차감할 재고 수량이 없습니다.");
      }
      this.quantity -= quantity;
    }
}
