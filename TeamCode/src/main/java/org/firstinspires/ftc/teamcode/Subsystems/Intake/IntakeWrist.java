package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;


public class IntakeWrist extends Subsystem {

    Servo wrist;

    private Constants constants = new Constants();

    @Override
    public void init(HardwareMap hardwareMap) {
        wrist = hardwareMap.get(Servo.class, "iWrist");
    }

    public void startPos(){wrist.setPosition(constants.IntakeWristStart);}
    public void outPos(){wrist.setPosition(constants.IntakeWristOuttake);}
    public void inPos(){wrist.setPosition(constants.IntakeWristIntake);}
}
