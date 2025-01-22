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


    private DcMotorEx intakeLinearSlideOne;
    private DcMotorEx intakeLinearSlideTwo;

    public static int targetPos = 0;
    public static int maxHeight = 2000;
    public static int minHeight = 0;

    public int currentHeight = 0;

    public static double motorPower = 0.3;

    public void runOpMode() {
        intakeLinearSlideOne = hardwareMap.get(DcMotorEx.class, "InLinearSlideOne");
        intakeLinearSlideTwo = hardwareMap.get(DcMotorEx.class, "InLinearSlideTwo");

        intakeLinearSlideOne.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        intakeLinearSlideTwo.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        intakeLinearSlideOne.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        intakeLinearSlideTwo.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        intakeLinearSlideOne.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeLinearSlideTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intakeLinearSlideOne.setDirection(DcMotorEx.Direction.REVERSE);
        intakeLinearSlideTwo.setDirection(DcMotorEx.Direction.REVERSE);
        

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        while(opModeIsActive()){
            telemetry.addData("Left Motor Position", intakeLinearSlideTwo.getCurrentPosition());
            telemetry.addData("Height", currentHeight);
            telemetry.addData("Is Busy", isBusy());

            telemetry.update();

            intakeLinearSlideOne.setTargetPosition(-targetPos);
            intakeLinearSlideTwo.setTargetPosition(-targetPos);

            intakeLinearSlideOne.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            intakeLinearSlideTwo.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

            if(currentHeight > targetPos){
                intakeLinearSlideOne.setPower(-motorPower);
                intakeLinearSlideTwo.setPower(-motorPower);
            }

            if(currentHeight < targetPos){
                intakeLinearSlideOne.setPower(motorPower);
                intakeLinearSlideTwo.setPower(motorPower);
            }
            if(currentHeight == targetPos - 10){
                intakeLinearSlideTwo.setPower(0);
            }


            currentHeight = intakeLinearSlideTwo.getCurrentPosition();

            telemetry.addData(("Is Busy"), isBusy());
            telemetry.update();




            if (gamepad2.right_stick_y < -0.5 && currentHeight <= maxHeight) {
                intakeLinearSlideOne.setPower(1);
                intakeLinearSlideTwo.setPower(1);
            } else if (gamepad2.right_stick_y > 0.5 && currentHeight >= minHeight) {
                intakeLinearSlideOne.setPower(-1);
                intakeLinearSlideTwo.setPower(-1);
            } else {
                intakeLinearSlideOne.setPower(0);
                intakeLinearSlideTwo.setPower(0);
            }

//            if(targetPos >= maxHeight){
//                targetPos = maxHeight;
//            } else if(targetPos <= minHeight){
//                targetPos = minHeight;
//            }
        }
    }

    public boolean isBusy(){return Math.abs(currentHeight-targetPos) > 10;}
}