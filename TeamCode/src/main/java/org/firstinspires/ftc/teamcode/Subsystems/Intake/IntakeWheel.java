package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class IntakeWheel extends Subsystem {
    private DcMotorEx wheel;

    Constants constants = new Constants();

    public void init(HardwareMap hardwareMap) {
        wheel = hardwareMap.get(DcMotorEx.class, "IntakeWheel");
        wheel.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void takeBlock() {wheel.setPower(constants.IntakeWheelPowIn);}

    public void noBlock() {wheel.setPower(0);}

    public void outBlock() {wheel.setPower(constants.IntakeWheelPowOut);}

    public void TransferBlock() {wheel.setPower(constants.IntakeWheelPowTransfer);}
}
