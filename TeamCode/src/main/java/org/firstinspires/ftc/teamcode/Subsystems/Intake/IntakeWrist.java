package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;


public class IntakeWrist extends Subsystem {

    Servo Iclaw;

    private Constants constants = new Constants();

    @Override
    public void init(HardwareMap hardwareMap) {
        Iclaw = hardwareMap.get(Servo.class, "Claw");
    }

    public void OpenClawIn(){Iclaw.setPosition(constants.IOpenClaw); }
    public void CloseClawIn(){Iclaw.setPosition(constants.ICloseClaw); }
}
