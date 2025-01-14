package org.firstinspires.ftc.teamcode.Subsystems.Outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Constants;

public class OuttakeArm extends Subsystem {

    private Servo arm;
    private Servo wrist;

    private Constants constants = new Constants();

    @Override
    public void init(HardwareMap hardwareMap) {
        arm = hardwareMap.get(Servo.class, "Outtake Arm Right");
        wrist = hardwareMap.get(Servo.class, "Outtake Wrist Left");
    }

    public void startPos(){//when it faces up during the beginning
        arm.setPosition(constants.OutakeArmStartPos);
        wrist.setPosition(constants.OutakeWristStartPos);
    }
    public void takePos(){//when it takes the sample from the box
        arm.setPosition(constants.OutakeArmTakePos);
        wrist.setPosition(constants.OutakeWristTakePos);
    }
    public void dumpPos(){//when it dumps the sample to the basket
        arm.setPosition(constants.OutakeArmDumpPos);
        wrist.setPosition(constants.OutakeWristDumpPos);
    }
    public void hookstartPos(){//when it goes below the hook bar
        arm.setPosition(constants.OutakeArmHookstartPos);
        wrist.setPosition(constants.OutakeWristHookstartPos);
    }
    public void hookupPos(){// when it is ready to hook the specimen on
        arm.setPosition(constants.OutakeArmHookupPos);
        wrist.setPosition(constants.OutakeWristHookupPos);
    }
}
