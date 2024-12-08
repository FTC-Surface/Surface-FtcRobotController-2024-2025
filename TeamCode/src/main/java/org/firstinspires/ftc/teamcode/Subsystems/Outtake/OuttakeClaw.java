package org.firstinspires.ftc.teamcode.Subsystems.Outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class OuttakeClaw extends Subsystem {

    private Servo rClaw;
    private Servo lClaw;

    private Constants constants;

    @Override
    public void init(HardwareMap hardwareMap) {
        rClaw = hardwareMap.get(Servo.class, "Right Claw");
        lClaw = hardwareMap.get(Servo.class, "Left Claw");
    }

    public void open(){
        rClaw.setPosition(constants.openClawR);
        lClaw.setPosition(constants.openClawL);
    }

    public void close(){
        rClaw.setPosition(constants.closeClawR);
        lClaw.setPosition(constants.closeClawL);
    }
}
