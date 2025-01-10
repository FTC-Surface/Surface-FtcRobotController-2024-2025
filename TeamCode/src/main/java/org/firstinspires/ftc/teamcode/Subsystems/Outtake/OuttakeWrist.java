package org.firstinspires.ftc.teamcode.Subsystems.Outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class OuttakeWrist extends Subsystem {

    private Servo OuttakeWrist;

    private Constants constants = new Constants();

    @Override
    public void init(HardwareMap hardwareMap) {
        OuttakeWrist = hardwareMap.get(Servo.class, "Outtake Wrist Left");
    }

    public void startPos(){
        OuttakeWrist.setPosition(constants.OutakeWristStartPos);
    }
    public void raisingPos(){OuttakeWrist.setPosition(constants.OutakeWristRaisePos);}
    public void outPos(){OuttakeWrist.setPosition(constants.OutakeWristOutPos);}
}
