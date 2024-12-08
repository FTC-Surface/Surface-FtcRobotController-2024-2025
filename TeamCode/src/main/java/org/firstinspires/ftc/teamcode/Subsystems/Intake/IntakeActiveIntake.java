package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class IntakeActiveIntake extends Subsystem {

    Servo lIntake;
    Servo rIntake;

    @Override
    public void init(HardwareMap hardwareMap) {
        lIntake = hardwareMap.get(Servo.class, "lIntake");
        rIntake = hardwareMap.get((Servo.class), "rIntake");
    }

    public void intake(){

    }

    public void outTake(){

    }
}
