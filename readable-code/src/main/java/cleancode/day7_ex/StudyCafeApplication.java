package cleancode.day7_ex;

import cleancode.day7_ex.model.provider.StudyCafeLockerProvider;
import cleancode.day7_ex.model.provider.StudyCafeSeatProvider;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafeSeatProvider seatProvider = new StudyCafeSeatProvider();
        StudyCafeLockerProvider lockerProvider = new StudyCafeLockerProvider();
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(seatProvider, lockerProvider);
        studyCafePassMachine.run();
    }

}
