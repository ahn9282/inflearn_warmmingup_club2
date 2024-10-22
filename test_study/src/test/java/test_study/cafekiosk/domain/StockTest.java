package test_study.cafekiosk.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class StockTest {

    @DisplayName("재고의 수량이 제공된 수량보다 적은지 확인한다.")
    @Test
    void isQuantityLessThan() {
        //Given
        Stock stock = Stock.create("001", 1);
        int quantity = 2;

        //When
        boolean result = stock.isQuantityLessThan(quantity);

        //Then
        assertThat(result).isTrue();

    }

    @DisplayName("재고를 주어진 개수만큼 차감할 수 있다.")
    @Test
    void deductQuantity() {
        //Given
        Stock stock = Stock.create("001", 1);
        int quantity = 1;


        //When
        stock.deductQuantity(quantity);

        //Then
        assertThat(stock.getQuantity()).isEqualTo(0);

    }

    @DisplayName("재고보다 만흥ㄴ 수량으로 차감 시 예외 발생.")
    @Test
    void deductOverQuantity() {
        //Given
        Stock stock = Stock.create("001", 1);
        int quantity = 3;


        //When
        //Then
        assertThatThrownBy(() ->stock.deductQuantity(quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("차감할 재고 수량이 없습니다.");

    }

}