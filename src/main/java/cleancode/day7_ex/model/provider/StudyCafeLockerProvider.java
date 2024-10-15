package cleancode.day7_ex.model.provider;


import cleancode.day7_ex.model.StudyCafePassType;
import cleancode.day7_ex.model.pass.StudyCafeLockerPass;
import cleancode.day7_ex.model.pass.StudyCafeLockerPasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafeLockerProvider implements LockerPassProvider {
    public static final String PATH_LIST_CSV_LOCKER = "src/main/resources/cleancode/studycafe/locker.csv";

    @Override
    public StudyCafeLockerPasses getLockerPasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(PATH_LIST_CSV_LOCKER));
            List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);

                StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(studyCafePassType, duration, price);
                lockerPasses.add(lockerPass);
            }

            return StudyCafeLockerPasses.of(lockerPasses);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }
}
