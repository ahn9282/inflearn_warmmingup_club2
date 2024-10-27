package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.pass.StudyCafePass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPasses;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPasses;

public interface PassReader {
      StudyCafeSeatPasses readStudyCafePasses();

      StudyCafeLockerPasses readLockerPasses();
}