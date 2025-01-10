package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.FtcDashboard;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Intake Linear Slides Test", group = "Tests")
@Config
public class IntakeLinearSlidesTest extends LinearOpMode {

    private DcMotorEx intakeLinearSlideOne;
    private DcMotorEx intakeLinearSlideTwo;

    public static int targetPos = 0;
    public static int maxHeight = 2350;
    public static int minHeight = 0;

    public int currentHeight = 0;

    public static double motorPower = 0.75;

    public void runOpMode() {
        intakeLinearSlideOne = hardwareMap.get(DcMotorEx.class, "lInLinearSlide");
        intakeLinearSlideTwo = hardwareMap.get(DcMotorEx.class, "rInLinearSlide");

        intakeLinearSlideOne.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        intakeLinearSlideTwo.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        intakeLinearSlideOne.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        intakeLinearSlideTwo.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        while(opModeIsActive()){
            telemetry.addData("Left Motor Position", intakeLinearSlideOne.getCurrentPosition());
            telemetry.addData("Right Motor Position", intakeLinearSlideTwo.getCurrentPosition());
            telemetry.addData("Height", currentHeight);
            telemetry.addData("Is Busy", isBusy());

            telemetry.update();

//            intakeLinearSlideOne.setTargetPosition(targetPos);
//            intakeLinearSlideTwo.setTargetPosition(targetPos);
//
//            intakeLinearSlideOne.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            intakeLinearSlideTwo.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            if(currentHeight > targetPos){
//                intakeLinearSlideOne.setPower(-motorPower);
//                intakeLinearSlideTwo.setPower(-motorPower);
//            }
//
//            if(currentHeight < targetPos){
//                intakeLinearSlideOne.setPower(motorPower);
//                intakeLinearSlideTwo.setPower(motorPower);
//            }
//
//            if(currentHeight <= targetPos + 1.5 && currentHeight >= targetPos - 1.5){
//                intakeLinearSlideOne.setPower(0.1);
//                intakeLinearSlideTwo.setPower(0.1);
//            }
//
//            currentHeight = (intakeLinearSlideTwo.getCurrentPosition() + intakeLinearSlideOne.getCurrentPosition())/2;
//
//            telemetry.addData(("Is Busy"), isBusy());
//            telemetry.update();

//            if (gamepad1.a) {
//                targetPos = 2200;
//            }
//
//            if (gamepad1.b) {
//                targetPos = 0;
//            }

//            Max height = 3550;

            if (gamepad1.a && currentHeight < maxHeight) {
                intakeLinearSlideOne.setPower(0.3);
                intakeLinearSlideTwo.setPower(0.3);
            } else if (gamepad1.b && currentHeight > minHeight) {
                intakeLinearSlideOne.setPower(-0.3);
                intakeLinearSlideTwo.setPower(-0.3);
            } else {
                intakeLinearSlideOne.setPower(0);
                intakeLinearSlideTwo.setPower(0);
            }
        }
    }

    public boolean isBusy(){return Math.abs(currentHeight-targetPos) > 10;}
}