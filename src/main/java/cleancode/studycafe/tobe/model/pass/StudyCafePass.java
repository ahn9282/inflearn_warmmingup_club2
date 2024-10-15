package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.StudyCafePassType;

public interface StudyCafePass {

    int getDuration();

    int getPrice();

    StudyCafePassType getPassType();

}
