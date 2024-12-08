package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class IntakeWrist extends Subsystem {

    Servo lWrist;
    Servo rWrist;

    @Override
    public void init(HardwareMap hardwareMap) {
        lWrist = hardwareMap.get(Servo.class, "lWrist");
        rWrist = hardwareMap.get((Servo.class), "rWrist");
    }
}
