package org.firstinspires.ftc.teamcode.TestSubsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake Linear Slides Test")
@Config
public class IntakeLinearSlidesTest extends LinearOpMode {

    private DcMotorEx leftIntakeMotor;
    private DcMotorEx rightIntakeMotor;

    public static int targetPos = 0;

    public void runOpMode() {
        leftIntakeMotor = hardwareMap.get(DcMotorEx.class, "lInLinearSlide");
        rightIntakeMotor = hardwareMap.get(DcMotorEx.class, "rInLinearSlide");

        leftIntakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        leftIntakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightIntakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        leftIntakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightIntakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        while(opModeIsActive()){
            leftIntakeMotor.setTargetPosition(targetPos);
            rightIntakeMotor.setTargetPosition(targetPos);

            leftIntakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightIntakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftIntakeMotor.setPower(1);
            rightIntakeMotor.setPower(1);
        }
    }
}