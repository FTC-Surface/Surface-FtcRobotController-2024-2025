package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class IntakeArm extends Subsystem {

    private Servo armServo1;
    private Constants constants = new Constants();

    @Override
    public void init(HardwareMap hardwareMap) {
        armServo1 = hardwareMap.get(Servo.class,"iArm1");
    }

    //Constants will need to be changed.

    public void upPos(){
        armServo1.setPosition(constants.IntakeArmStartPos);
    }

    public void downPos(){
        armServo1.setPosition(constants.IntakeArmGrabPos);
    }
}
