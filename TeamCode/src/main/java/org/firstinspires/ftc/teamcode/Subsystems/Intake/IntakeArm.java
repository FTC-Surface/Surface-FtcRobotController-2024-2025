package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class IntakeArm extends Subsystem {

    private Servo armServo;
    private Constants constants = new Constants();

    @Override
    public void init(HardwareMap hardwareMap) {
        armServo = hardwareMap.get(Servo.class,"iArm");
    }

    public void startPos(){
        armServo.setPosition(constants.IntakeArmStartPos);
    }
    public void outPos(){
        armServo.setPosition(constants.IntakeArmOutPos);
    }
}
