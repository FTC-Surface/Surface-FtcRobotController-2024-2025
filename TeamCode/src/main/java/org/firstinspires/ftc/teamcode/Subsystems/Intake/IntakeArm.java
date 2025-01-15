package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class IntakeArm extends Subsystem {

    private Servo armServo1;
    private Servo armServo2;
    private Servo Wrist;
    private Constants constants = new Constants();

    @Override
    public void init(HardwareMap hardwareMap) {
        armServo1 = hardwareMap.get(Servo.class,"iArm1");
        armServo2 = hardwareMap.get(Servo.class, "iArm2");
        Wrist = hardwareMap.get(Servo.class, "Wrist");
        armServo2.setDirection(Servo.Direction.REVERSE);
    }

    public void upPos(){
        armServo1.setPosition(constants.IntakeArmStartPos);
        armServo2.setPosition(constants.IntakeArmStartPos);
        Wrist.setPosition(constants.IntakeWristStart);
    }

    public void downPos(){
        armServo1.setPosition(constants.IntakeArmGrabPos);
        armServo2.setPosition(constants.IntakeArmGrabPos);
        Wrist.setPosition(constants.IntakeWristGrab);
    }

    public void hoverPos(){
        armServo1.setPosition(constants.IntakeArmHoverPos);
        armServo2.setPosition(constants.IntakeArmHoverPos);
        Wrist.setPosition(constants.IntakeWristHoverPos);
    }
}
