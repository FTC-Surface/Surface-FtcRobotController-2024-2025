package org.firstinspires.ftc.teamcode.Subsystems.Outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class OuttakeArm extends Subsystem {

    private Servo armRight;
    private Servo armLeft;

    @Override
    public void init(HardwareMap hardwareMap) {
        armRight = hardwareMap.get(Servo.class, "Outtake Arm Right");
        armLeft = hardwareMap.get(Servo.class, "Outtake Arm Left");
    }
}
