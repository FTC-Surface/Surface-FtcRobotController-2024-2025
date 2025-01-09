package org.firstinspires.ftc.teamcode.Subsystems.Outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class OuttakeClaw extends Subsystem {

    private Servo claw;

    private Constants constants;

    @Override
    public void init(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "Claw");
    }

    public void open(){
        claw.setPosition(constants.openClaw);
    }

    public void close(){
        claw.setPosition(constants.closeClaw);
    }
}
