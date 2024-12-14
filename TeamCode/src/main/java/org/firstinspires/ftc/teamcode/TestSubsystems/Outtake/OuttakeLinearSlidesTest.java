package org.firstinspires.ftc.teamcode.TestSubsystems.Outtake;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Outtake Slides Test Op", group = "Tests")
@Config
public class OuttakeLinearSlidesTest extends LinearOpMode {

    //does intake and outtake linear slide use the same motors?
    private DcMotorEx leftOuttakeMotor;
    private DcMotorEx rightOuttakeMotor;

    public static int targetPos = 0;

    public int currentHeight = 0;

    public void runOpMode() {
        leftOuttakeMotor = hardwareMap.get(DcMotorEx.class, "outtakeLinearSlideOne");
        rightOuttakeMotor = hardwareMap.get(DcMotorEx.class, "outtakeLinearSlideTwo");

        leftOuttakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightOuttakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftOuttakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightOuttakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        leftOuttakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightOuttakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        telemetry.addData("Height", currentHeight);

        while(opModeIsActive()) {
//            leftOuttakeMotor.setTargetPosition(targetPos);
//            rightOuttakeMotor.setTargetPosition(targetPos);
//
//
//            leftOuttakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            rightOuttakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            if((right.getCurrentPosition() - left.getCurrentPosition())/2 > targetPos){
//
//            }
//
//            if((right.getCurrentPosition() - left.getCurrentPosition())/2 < targetPos){
//
//            }

            telemetry.addData("Left Motor Position", leftOuttakeMotor.getCurrentPosition());
            telemetry.addData("Right Motor Position", leftOuttakeMotor.getCurrentPosition());
            telemetry.addData("Height", currentHeight);

            telemetry.update();

            if (gamepad1.left_bumper) {
                leftOuttakeMotor.setPower(1);
                rightOuttakeMotor.setPower(1);
            }

            if (gamepad1.right_bumper) {
                leftOuttakeMotor.setPower(-1);
                rightOuttakeMotor.setPower(-1);
            }

            currentHeight = rightOuttakeMotor.getCurrentPosition() - leftOuttakeMotor.getCurrentPosition()/2;
        }
    }
}