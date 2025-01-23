package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class IntakeBucket extends Subsystem {

    private Servo bucketServo;

    @Override
    public void init(HardwareMap hardwareMap) {bucketServo = hardwareMap.get(Servo.class, "BucketServo");}
}
