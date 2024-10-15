package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.provider.LockerPassFileReader;
import cleancode.studycafe.tobe.provider.LockerPassProvider;
import cleancode.studycafe.tobe.provider.SeatPassFileReader;
import cleancode.studycafe.tobe.provider.SeatPassProvider;

public class StudyCafeApplication {

    public static void main(String[] args) {
        //PassReader passReader = new StudyCafeFileHandler();
        LockerPassProvider lockerPassProvider = new LockerPassFileReader();
        SeatPassProvider seatPassProvider = new SeatPassFileReader();
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(seatPassProvider, lockerPassProvider);
        studyCafePassMachine.run();
    }

}
