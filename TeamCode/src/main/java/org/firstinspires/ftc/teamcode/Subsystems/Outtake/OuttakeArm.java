package org.firstinspires.ftc.teamcode.Subsystems.Outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Constants;

public class OuttakeArm extends Subsystem {

    private Servo armOne;

    private Constants constants = new Constants();

    @Override
    public void init(HardwareMap hardwareMap) {
        armOne = hardwareMap.get(Servo.class, "Outtake Arm Right");
    }

    public void startPos(){
        armOne.setPosition(constants.OutakeArmStartPos);
    }

    public void outPos(){
        armOne.setPosition(constants.OutakeArmOutPos);
    }
}
