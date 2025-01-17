package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.FtcDashboard;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Intake Linear Slides Test", group = "Tests")
@Config
public class IntakeLinearSlidesTest extends LinearOpMode {

    private DcMotorEx intakeLinearSlide;

    public static int targetPos = 0;
    public static int maxHeight = 1000;
    public static int minHeight = 0;

    public int currentHeight = 0;

    public static double motorPower = 0.3;

    public void runOpMode() {
        intakeLinearSlide = hardwareMap.get(DcMotorEx.class, "InLinearSlide");

        intakeLinearSlide.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        intakeLinearSlide.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        intakeLinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        while(opModeIsActive()){
            telemetry.addData("Left Motor Position", intakeLinearSlide.getCurrentPosition());
            telemetry.addData("Height", currentHeight);
            telemetry.addData("Is Busy", isBusy());

            telemetry.update();

            intakeLinearSlide.setTargetPosition(targetPos);

            intakeLinearSlide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

            if(currentHeight > targetPos){
                intakeLinearSlide.setPower(-motorPower);
            }

            if(currentHeight < targetPos){
                intakeLinearSlide.setPower(motorPower);
            }


            currentHeight = intakeLinearSlide.getCurrentPosition();

            telemetry.addData(("Is Busy"), isBusy());
            telemetry.update();




            if (gamepad2.left_stick_x <= -0.5 && currentHeight < maxHeight) {
                intakeLinearSlide.setPower(0.3);
            } else if (gamepad2.left_stick_x >= 0.5 && currentHeight > minHeight) {
                intakeLinearSlide.setPower(-0.3);
            } else {
                intakeLinearSlide.setPower(0);
            }

            if(targetPos >= maxHeight){
                targetPos = maxHeight;
            } else if(targetPos <= minHeight){
                targetPos = minHeight;
            }
        }
    }

    public boolean isBusy(){return Math.abs(currentHeight-targetPos) > 10;}
}