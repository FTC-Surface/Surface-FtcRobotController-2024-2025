package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.FtcDashboard;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Intake Linear Slides Test", group = "Tests Intake")
@Config
public class IntakeLinearSlidesTest extends LinearOpMode {

    private DcMotorEx intakeLinearSlideOne;
    private DcMotorEx intakeLinearSlideTwo;

    public static int targetPos = 0;
    public static int maxHeight = 1000;
    public static int minHeight = 0;

    public int currentHeight = 0;
    public static int mode=0;

    public static double motorPower = 0.3;

    public void runOpMode() {
        intakeLinearSlideOne = hardwareMap.get(DcMotorEx.class, "InLinearSlideOne");
        intakeLinearSlideTwo = hardwareMap.get(DcMotorEx.class, "InLinearSlideTwo");

        intakeLinearSlideOne.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        intakeLinearSlideTwo.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        intakeLinearSlideOne.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeLinearSlideTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intakeLinearSlideOne.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        intakeLinearSlideTwo.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        intakeLinearSlideOne.setDirection(DcMotorEx.Direction.REVERSE);
        intakeLinearSlideTwo.setDirection(DcMotorEx.Direction.REVERSE);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        telemetry.addData("Height", currentHeight);

        if (mode==1)
        {
            intakeLinearSlideOne.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            intakeLinearSlideTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        while(opModeIsActive()){
            telemetry.addData("Left Motor Position", intakeLinearSlideTwo.getCurrentPosition());
            telemetry.addData("Right Motor Position", intakeLinearSlideOne.getCurrentPosition());
            telemetry.addData("Height", currentHeight);
            telemetry.addData("Is Busy", isBusy());

            telemetry.update();
            if (mode ==0) {

                intakeLinearSlideOne.setTargetPosition(-targetPos);
                intakeLinearSlideTwo.setTargetPosition(-targetPos);

                intakeLinearSlideOne.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                intakeLinearSlideTwo.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);


                if (currentHeight < -targetPos) {//forward
                    intakeLinearSlideOne.setPower(-motorPower);
                    intakeLinearSlideTwo.setPower(-motorPower);
                }

                if (currentHeight > -targetPos) {//backward
                    intakeLinearSlideOne.setPower(motorPower);
                    intakeLinearSlideTwo.setPower(motorPower);
                }

                if (currentHeight == targetPos) {
                    intakeLinearSlideOne.setPower(0);
                    intakeLinearSlideTwo.setPower(0);
                }
            }

            currentHeight = Math.abs(intakeLinearSlideOne.getCurrentPosition()+intakeLinearSlideTwo.getCurrentPosition())/2;

            if(mode == 1) {
                if (gamepad2.right_stick_y < -0.3 && currentHeight < maxHeight) {//forward
                    intakeLinearSlideOne.setPower(-1);
                    intakeLinearSlideTwo.setPower(-1);
                } else if (gamepad2.right_stick_y > 0.3 && currentHeight > minHeight) {//backward
                    intakeLinearSlideOne.setPower(1);
                    intakeLinearSlideTwo.setPower(1);
                } else {
                    intakeLinearSlideOne.setPower(0);
                    intakeLinearSlideTwo.setPower(0);
                }
            }

        }
    }

    public boolean isBusy(){return Math.abs(currentHeight-targetPos) > 15;}
}