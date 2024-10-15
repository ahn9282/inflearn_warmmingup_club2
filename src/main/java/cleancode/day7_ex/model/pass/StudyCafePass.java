package cleancode.day7_ex.model.pass;


import cleancode.day7_ex.model.StudyCafePassType;

public interface StudyCafePass {

    int getDuration();

    int getPrice();

    StudyCafePassType getPassType();

}