package cleancode.day7_ex.model.provider;

import cleancode.day7_ex.model.StudyCafePassType;
import cleancode.day7_ex.model.pass.StudyCafeSeatPass;
import cleancode.day7_ex.model.pass.StudyCafeSeatPasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafeSeatProvider implements SeatPassProvider {
    public static final String PATH_LIST_CSV_SEAT = "src/main/resources/cleancode/studycafe/pass-list.csv";
    @Override
    public StudyCafeSeatPasses getSeatPasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(PATH_LIST_CSV_SEAT));
            List<StudyCafeSeatPass> studyCafePasses = new ArrayList<>();

            for (String line : lines) {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                double discountRate = Double.parseDouble(values[3]);

                StudyCafeSeatPass studyCafePass = StudyCafeSeatPass.of(studyCafePassType, duration, price, discountRate);
                studyCafePasses.add(studyCafePass);
            }

            return StudyCafeSeatPasses.of(studyCafePasses);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }
}
