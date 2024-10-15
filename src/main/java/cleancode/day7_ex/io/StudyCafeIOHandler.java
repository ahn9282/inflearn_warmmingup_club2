package cleancode.day7_ex.io;

import cleancode.day7_ex.model.StudyCafePassOrder;
import cleancode.day7_ex.model.pass.StudyCafeLockerPass;
import cleancode.day7_ex.model.pass.StudyCafePass;
import cleancode.day7_ex.model.StudyCafePassType;
import cleancode.day7_ex.model.pass.StudyCafeSeatPass;

import java.util.List;

public class StudyCafeIOHandler {
    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();



    public void welcomeAndAnnounce() {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();

    }

    public StudyCafePassType getPassesType() {
        StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();
        return studyCafePassType;
    }

    public StudyCafePass getSelectPass( List<StudyCafeSeatPass> passes) {
        outputHandler.showPassListForSelection(passes);
       return inputHandler.getSelectPass(passes);
    }

    public boolean askLockerPass(StudyCafeLockerPass lockerPassCandidate) {
        outputHandler.askLockerPass(lockerPassCandidate);
        return inputHandler.getLockerSelection();
    }

    public void showSimpleMessage(String message) {
        outputHandler.showSimpleMessage(message);
    }
    public StudyCafeSeatPass askPassSelecting(List<StudyCafeSeatPass> passCandidates) {
        outputHandler.showPassListForSelection(passCandidates);
        return inputHandler.getSelectPass(passCandidates);
    }
    public void showPassOrderSummary(StudyCafePassOrder passOrder) {
        outputHandler.showPassOrderSummary(passOrder);
    }


}
