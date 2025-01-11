package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;


public class IntakeWrist extends Subsystem {

    Servo lWrist;
    Servo rWrist;

    private double wristPower;

    private Constants constants = new Constants();

    @Override
    public void init(HardwareMap hardwareMap) {
        lWrist = hardwareMap.get(Servo.class, "lWrist");
        rWrist = hardwareMap.get(Servo.class, "rWrist");
    }

    public void startPos(){
        lWrist.setPosition(constants.IntakeWristStart);
        rWrist.setPosition(constants.IntakeWristStart);
    }

    public void inPos(){
        lWrist.setPosition(constants.IntakeWristIntake);
        rWrist.setPosition(constants.IntakeWristIntake);
    }

    public void outPos(){
        lWrist.setPosition(constants.IntakeWristOuttake);
        rWrist.setPosition(constants.IntakeWristOuttake);
    }
}
