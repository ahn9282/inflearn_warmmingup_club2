package cleancode.studycafe.tobe.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class StudyCafePassTypeTest {




    @DisplayName("StudyCafePassType description 체크")
    @Test
    void descriptionCheckTest() {

        //Given
        StudyCafePassType hourlyPass = StudyCafePassType.HOURLY;
        StudyCafePassType weeklyPass = StudyCafePassType.WEEKLY;
        StudyCafePassType fixedPass = StudyCafePassType.FIXED;

        //When & Then
        assertThat(hourlyPass.toString())
                .isEqualTo("시간 단위 이용권");
        assertThat(weeklyPass.toString())
                .isEqualTo("주 단위 이용권");
        assertThat(fixedPass.toString())
                .isEqualTo("1인 고정석");

    }

        @DisplayName("Locker 타입인지에 대한 반환")
            @Test
            void checkIsLocker(){
                //Given
            StudyCafePassType hourlyPass = StudyCafePassType.HOURLY;
            StudyCafePassType weeklyPass = StudyCafePassType.WEEKLY;
            StudyCafePassType fixedPass = StudyCafePassType.FIXED;

            //When & Then
            assertThat(hourlyPass.isNotLockerType()).isTrue();
            assertThat(weeklyPass.isNotLockerType()).isTrue();
            assertThat(fixedPass.isNotLockerType()).isFalse();

            assertThat(hourlyPass.isLockerType()).isFalse();
            assertThat(weeklyPass.isLockerType()).isFalse();
            assertThat(fixedPass.isLockerType()).isTrue();

            }


}