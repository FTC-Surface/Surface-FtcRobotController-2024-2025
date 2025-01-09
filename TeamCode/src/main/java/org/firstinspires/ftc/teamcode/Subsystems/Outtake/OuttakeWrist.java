package org.firstinspires.ftc.teamcode.Subsystems.Outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class OuttakeWrist extends Subsystem {

    Servo OuttakeWrist;

    @Override
    public void init(HardwareMap hardwareMap) {
        OuttakeWrist = hardwareMap.get(Servo.class, "Outtake Wrist Left");
    }
}
