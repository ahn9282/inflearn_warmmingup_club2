package cleancode.studycafe.tobe.model.pass;


import cleancode.studycafe.tobe.model.StudyCafePassType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class StudyCafeSeatPassesTest {

    @Test
    @DisplayName("StudyCafeSeatPasses 생성 체크")
    void testCreateSeatPasses() {
        // given
        StudyCafeSeatPass pass1 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 5, 5000, 0.9);
        StudyCafeSeatPass pass2 = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 7, 7000, 0.95);

        // when
        StudyCafeSeatPasses seatPasses = StudyCafeSeatPasses.of(List.of(pass1, pass2));

        // then
        assertThat(seatPasses).isNotNull();
        assertThat(seatPasses.findPassBy(StudyCafePassType.HOURLY)).hasSize(1);
        assertThat(seatPasses.findPassBy(StudyCafePassType.WEEKLY)).hasSize(1);
    }

    @Test
    @DisplayName("StudyCafePassType을 기준으로 패스 체크")
    void testFindPassBy() {
        // given
        StudyCafeSeatPass hourlyPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 5, 5000, 0.9);
        StudyCafeSeatPass weeklyPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 7, 7000, 0.95);
        StudyCafeSeatPass fixedPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 30, 30000, 1.0);

        StudyCafeSeatPasses seatPasses = StudyCafeSeatPasses.of(List.of(hourlyPass, weeklyPass, fixedPass));

        // when
        List<StudyCafeSeatPass> resultHourly = seatPasses.findPassBy(StudyCafePassType.HOURLY);
        List<StudyCafeSeatPass> resultWeekly = seatPasses.findPassBy(StudyCafePassType.WEEKLY);
        List<StudyCafeSeatPass> resultFixed = seatPasses.findPassBy(StudyCafePassType.FIXED);

        // then
        assertThat(resultHourly).hasSize(1);
        assertThat(resultHourly.get(0)).isEqualTo(hourlyPass);

        assertThat(resultWeekly).hasSize(1);
        assertThat(resultWeekly.get(0)).isEqualTo(weeklyPass);

        assertThat(resultFixed).hasSize(1);
        assertThat(resultFixed.get(0)).isEqualTo(fixedPass);
    }

    @Test
    @DisplayName("존재하지 않는 패스 타입 체크")
    void testFindPassBy_nonExistentType() {
        // given
        StudyCafeSeatPass hourlyPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 5, 5000, 0.9);
        StudyCafeSeatPasses seatPasses = StudyCafeSeatPasses.of(List.of(hourlyPass));

        // when
        List<StudyCafeSeatPass> result = seatPasses.findPassBy(StudyCafePassType.FIXED);

        // then
        assertThat(result).isEmpty();
    }
}