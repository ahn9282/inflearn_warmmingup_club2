package cleancode.studycafe.tobe.model.pass;


import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class StudyCafeSeatPassTest {

    StudyCafePassType hourlyPass = StudyCafePassType.HOURLY;
    StudyCafePassType weeklyPass = StudyCafePassType.WEEKLY;
    StudyCafePassType fixedPass = StudyCafePassType.FIXED;


    @DisplayName("SeatPass 생성 확인")
    @Test
    void testConstructor() {
        //Given
        int duration = 10;
        int price = 10000;
        double discountRate = 0.9;

        //When
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(hourlyPass, duration, price, discountRate);


        //Then
        assertThat(seatPass).isNotNull();
        assertThat(seatPass.getPassType()).isEqualTo(hourlyPass);
        assertThat(seatPass.getPassType().toString()).isEqualTo("시간 단위 이용권");
        assertThat(seatPass.getDuration()).isEqualTo(duration);
        assertThat(seatPass.getPrice()).isEqualTo(price);
        assertThat(seatPass.getDiscountRate()).isEqualTo(discountRate);

    }

    @Test
    @DisplayName("동일한 이용 기간과 패스 타입인지 확인")
    void testIsSameDurationType() {
        // given
        StudyCafePassType passType = StudyCafePassType.FIXED;
        int duration = 30;
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(passType, duration, 30000, 1.0);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(passType, duration, 30000);


        assertThat(lockerPass.isSamePassType(passType)).isTrue();
        assertThat(seatPass.isSameDurationType(lockerPass)).isTrue();

        // when
        boolean result = seatPass.isSameDurationType(lockerPass);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("락커 이용 가능 여부 확인")
    void testCannotUserLocker() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(hourlyPass, 5, 5000, 0.9);

        // when
        boolean result = seatPass.cannotUserLocker();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("할인된 가격 계산")
    void testGetDiscountPrice() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(weeklyPass, 7, 10000, 0.8);

        // when
        int discountPrice = seatPass.getDiscountPrice();

        // then
        assertThat(discountPrice).isEqualTo(8000);  // 10000 * 0.8 = 8000
    }
}