package org.firstinspires.ftc.teamcode.Subsystems.Outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class OuttakeArm extends Subsystem {

    Servo armOne;

    @Override
    public void init(HardwareMap hardwareMap) {
        armOne = hardwareMap.get(Servo.class, "Outtake Arm Right");
    }
}
