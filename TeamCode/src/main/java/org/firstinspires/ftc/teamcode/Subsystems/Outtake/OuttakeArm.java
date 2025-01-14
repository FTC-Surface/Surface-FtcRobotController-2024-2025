package org.firstinspires.ftc.teamcode.Subsystems.Outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Constants;

public class OuttakeArm extends Subsystem {

    private Servo armOne;
    private Servo armTwo;

    private Constants constants = new Constants();

    @Override
    public void init(HardwareMap hardwareMap) {
        armOne = hardwareMap.get(Servo.class, "Outtake Arm Right");
        armTwo = hardwareMap.get(Servo.class, "Outtake Wrist Left");
    }

    public void startPos(){//when it faces up during the beginning
        armOne.setPosition(constants.OutakeArmStartPos);
        armTwo.setPosition(constants.OutakeWristStartPos);
    }
    public void takePos(){//when it takes the sample from the box
        armOne.setPosition(constants.OutakeArmTakePos);
        armTwo.setPosition(constants.OutakeWristTakePos);
    }
    public void dumpPos(){//when it dumps the sample to the basket
        armOne.setPosition(constants.OutakeArmDumpPos);
        armTwo.setPosition(constants.OutakeWristDumpPos);
    }
    public void hookstartPos(){//when it goes below the hook bar
        armOne.setPosition(constants.OutakeArmHookstartPos);
        armTwo.setPosition(constants.OutakeWristHookstartPos);
    }
    public void hookupPos(){// when it is ready to hook the specimen on
        armOne.setPosition(constants.OutakeArmHookupPos);
        armTwo.setPosition(constants.OutakeWristHookupPos);
    }
}
