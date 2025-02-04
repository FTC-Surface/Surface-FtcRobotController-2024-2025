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
        arm = hardwareMap.get(Servo.class, "Outtake Arm Left");
        wrist = hardwareMap.get(Servo.class, "Outtake Wrist Right");
        wrist.setDirection(Servo.Direction.REVERSE);
    }

    public void startPos(){//when it faces down during the beginning
        arm.setPosition(constants.OutakeArmStartPos);
        wrist.setPosition(constants.OutakeWristStartPos);
    }
    public void takePos(){//when it takes the sample from the box
        arm.setPosition(constants.OutakeArmTakePos);
        wrist.setPosition(constants.OutakeWristTakePos);
    }
    public void dumpreadyPos(){//when it gets ready to dump the sample to the basket
        arm.setPosition(constants.OutakeArmDumpReadyPos);
        wrist.setPosition(constants.OutakeWristDumpReleasePos);
    }
    public void dumpreleasePos(){//when it releases the dump of the sample to the basket
        arm.setPosition(constants.OutakeArmDumpReleasePos);
        wrist.setPosition(constants.OutakeWristDumpReleasePos);
    }
    public void hookgrabPos(){//when it is ready to take specimen from the wall
        arm.setPosition(constants.OutakeArmHookgrabPos);
        wrist.setPosition(constants.OutakeWristHookgrabPos);
    }
    public void hookupPos(){// when it is ready to hook the specimen on
        arm.setPosition(constants.OutakeArmHookupPos);
        wrist.setPosition(constants.OutakeWristHookupPos);
    }
}
