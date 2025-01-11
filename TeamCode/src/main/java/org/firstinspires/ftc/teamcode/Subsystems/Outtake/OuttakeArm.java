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

    public void startPos(){
        armOne.setPosition(constants.OutakeArmStartPos);
        armTwo.setPosition(constants.OutakeArmStartPos);
    }
    public void readyPos(){armOne.setPosition(constants.OutakeArmReadyPos);
        armTwo.setPosition(constants.OutakeArmStartPos);}
    public void outPos(){
        armOne.setPosition(constants.OutakeArmOutPos);
        armTwo.setPosition(constants.OutakeArmOutPos);
    }
}
