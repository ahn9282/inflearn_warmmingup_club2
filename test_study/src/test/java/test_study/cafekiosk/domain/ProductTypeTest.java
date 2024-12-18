package test_study.cafekiosk.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import test_study.cafekiosk.domain.product.ProductType;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class ProductTypeTest {

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @MethodSource("provideProductTypesForCheckingStockType")
    @ParameterizedTest
    void containsStockType5(ProductType productType, boolean expected) {
        //Given
        boolean result = ProductType.containsStockType(productType);

        //When
        assertThat(result).isEqualTo(expected);

        //Then


    }
    private static Stream<Arguments> provideProductTypesForCheckingStockType(){
        return Stream.of(
                Arguments.of(ProductType.HANDMADE, false),
                Arguments.of(ProductType.BOTTLE, true),
                Arguments.of(ProductType.BAKERY, true)
        );
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @CsvSource({"HANDMADE,false", "BOTTLE,true", "BAKERY,true"})
    @ParameterizedTest
    void containsStockType4(ProductType productType, boolean expected) {
        //Given
        boolean result = ProductType.containsStockType(productType);

        //When
        assertThat(result).isEqualTo(expected);

        //Then


    }

    @Test
    void containsStockType() {
        //Given
        ProductType type = ProductType.HANDMADE;

        //When
        boolean result1 = ProductType.containsStockType(type);
        boolean result2 = ProductType.containsStockType(ProductType.BOTTLE);

        //Then
        assertThat(result1).isFalse();
        assertThat(result2).isTrue();

    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크")
    @Test
    void containsStockTypeEx() {
        //Given
        ProductType[] productTypes = ProductType.values();

        for (ProductType productType : productTypes) {
            if (productType == ProductType.HANDMADE) {
                //When
                boolean result = ProductType.containsStockType(productType);

                //Then
                assertThat(result).isFalse();
            }


            if (productType == ProductType.BAKERY || productType == ProductType.BOTTLE) {
                //When
                boolean result = ProductType.containsStockType(productType);

                //Then
                assertThat(result).isTrue();
            }

        }
    }

}