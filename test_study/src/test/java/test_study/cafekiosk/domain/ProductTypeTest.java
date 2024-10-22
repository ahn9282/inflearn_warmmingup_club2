package test_study.cafekiosk.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTypeTest {

    @DisplayName("")
    @Test
    void containsStockType() {
        //Given
        ProductType type = ProductType.HANDMADE;

        //When
        boolean result1 = ProductType.containsStockType(type);
        boolean result2 = ProductType.containsStockType(ProductType.BOTTLE);

        //Then
        Assertions.assertThat(result1).isFalse();
        Assertions.assertThat(result2).isTrue();

    }

}