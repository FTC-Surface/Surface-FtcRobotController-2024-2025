package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Constants;


public class IntakeWrist extends Subsystem {

    CRServo lWrist;
    CRServo rWrist;

    private double wristPower;

    @Override
    public void init(HardwareMap hardwareMap) {
        lWrist = hardwareMap.get(CRServo.class, "lWrist");
        rWrist = hardwareMap.get(CRServo.class, "rWrist");
    }
}
