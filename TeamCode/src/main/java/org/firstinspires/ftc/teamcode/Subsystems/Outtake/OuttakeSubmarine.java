package org.firstinspires.ftc.teamcode.Subsystems.Outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class OuttakeSubmarine extends Subsystem {

    Servo Osub;

    private Constants constants = new Constants();

    @Override
    public void init(HardwareMap hardwareMap) {
        Osub = hardwareMap.get(Servo.class, "Outtake Sub");
    }
}
