package org.firstinspires.ftc.teamcode.Subsystems.Outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;


public class OuttakeClaw extends Subsystem {

    Servo Oclaw;

    private Constants constants = new Constants();

    @Override
    public void init(HardwareMap hardwareMap) {
        Oclaw = hardwareMap.get(Servo.class, "OClaw");
    }

    public void openClawOut(){Oclaw.setPosition(constants.OOpenClaw); }
    public void closeClawOut(){Oclaw.setPosition(constants.OCloseClaw); }
}
