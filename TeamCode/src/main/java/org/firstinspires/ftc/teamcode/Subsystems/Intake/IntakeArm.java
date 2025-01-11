package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class IntakeArm extends Subsystem {

    private Servo armServo;
    private Servo wrist;
    private Constants constants = new Constants();

    @Override
    public void init(HardwareMap hardwareMap) {
        armServo = hardwareMap.get(Servo.class,"iArm");
        wrist = hardwareMap.get(Servo.class, "iWrist");
    }

    public void startPos(){
        armServo.setPosition(constants.IntakeArmStartPos);
        wrist.setPosition(constants.IntakeArmStartPos);
    }

    public void moveArm(double pos){
        armServo.setPosition(pos);
        wrist.setPosition(pos);
    }
}
