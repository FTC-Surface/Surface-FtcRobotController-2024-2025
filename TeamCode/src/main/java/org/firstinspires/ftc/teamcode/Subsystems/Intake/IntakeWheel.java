package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class IntakeWheel extends Subsystem {
    DcMotorEx wheel;

    public void init(HardwareMap hardwareMap) {
        wheel = hardwareMap.get(DcMotorEx.class, "IntakeWheel");
    }

    public void takeBlock() {
        wheel.setPower(0.5);
    }

    public void noBlock() {
        wheel.setPower(0);
    }

    public void outBlock() {
        wheel.setDirection(DcMotorSimple.Direction.REVERSE);
        wheel.setPower(0.5);
    }
}
