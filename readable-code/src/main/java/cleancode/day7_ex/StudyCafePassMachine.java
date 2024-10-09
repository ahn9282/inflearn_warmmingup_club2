package cleancode.day7_ex;

import cleancode.day7_ex.exception.AppException;
import cleancode.day7_ex.io.StudyCafeFileHandler;
import cleancode.day7_ex.io.StudyCafeIOHandler;
import cleancode.day7_ex.model.StudyCafePassOrder;
import cleancode.day7_ex.model.pass.*;
import cleancode.day7_ex.model.StudyCafePassType;
import cleancode.day7_ex.model.provider.LockerPassProvider;
import cleancode.day7_ex.model.provider.SeatPassProvider;



import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    public static final String UNKNOWN_ERROR = "알 수 없는 오류가 발생했습니다.";
    private final StudyCafeIOHandler ioHandler = new StudyCafeIOHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
    private final SeatPassProvider seatPassProvider;
    private final LockerPassProvider lockerPassProvider;

    public StudyCafePassMachine(SeatPassProvider seatPassProvider, LockerPassProvider lockerPassProvider) {
        this.seatPassProvider = seatPassProvider;
        this.lockerPassProvider = lockerPassProvider;
    }

    public void run() {
        try {
            ioHandler.welcomeAndAnnounce();

            StudyCafeSeatPass selectPass = getSelectSeatPass();

            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectPass);
            StudyCafePassOrder passOrder = StudyCafePassOrder.of(
                    selectPass,
                    optionalLockerPass.orElse(null)
            );

           ioHandler.showPassOrderSummary(passOrder);

        } catch (AppException e) {
            ioHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            ioHandler.showSimpleMessage(UNKNOWN_ERROR);
        }
    }

    private StudyCafeSeatPass getSelectSeatPass() {
        StudyCafePassType passType = ioHandler.getPassesType();
        StudyCafeSeatPasses studyCafePasses = seatPassProvider.getSeatPasses();

        List<StudyCafeSeatPass> passes = studyCafePasses.findPassBy(passType);

        return ioHandler.askPassSelecting(passes);
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafeSeatPass selectPass) {
        if (selectPass.cannotUserLocker()) {
            return Optional.empty();
        }
        Optional<StudyCafeLockerPass> lockerPassCandidate = findLockerPassCandidateBy(selectPass);


        if (lockerPassCandidate.isPresent()) {
            StudyCafeLockerPass lockerPass = lockerPassCandidate.get();
            boolean isLockerSelected = ioHandler.askLockerPass(lockerPass);

            if (isLockerSelected) {
                return Optional.of(lockerPass);
            }
        }

        return Optional.empty();
    }

    private Optional<StudyCafeLockerPass> findLockerPassCandidateBy(StudyCafeSeatPass selectPass) {
        StudyCafeLockerPasses allLockerPasses = lockerPassProvider.getLockerPasses();

        return allLockerPasses.findLockerPassBy(selectPass);
    }



}
