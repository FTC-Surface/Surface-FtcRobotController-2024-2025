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

    private DcMotorEx leftIntakeMotor;
    private DcMotorEx rightIntakeMotor;

    public static int targetPos = 0;

    public int currentPos = 0;

    public void runOpMode() {
        leftIntakeMotor = hardwareMap.get(DcMotorEx.class, "lInLinearSlide");
        rightIntakeMotor = hardwareMap.get(DcMotorEx.class, "rInLinearSlide");

        leftIntakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightIntakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        leftIntakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightIntakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        while(opModeIsActive()){
//            leftIntakeMotor.setTargetPosition(targetPos);
//            rightIntakeMotor.setTargetPosition(targetPos);
//
//            leftIntakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            rightIntakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//            leftIntakeMotor.setPower(1);
//            rightIntakeMotor.setPower(-1);

//            if(getCurrentPos() > targetPos){
//                leftIntakeMotor.setPower(1);
//                rightIntakeMotor.setPower(-1);
//            }
//
//            if(getCurrentPos() < targetPos){
//                leftIntakeMotor.setPower(1);
//                rightIntakeMotor.setPower(-1);
//            }

            telemetry.addData("Left Motor Position", leftIntakeMotor.getCurrentPosition());
            telemetry.addData("Right Motor Position", leftIntakeMotor.getCurrentPosition());

            if(gamepad1.left_bumper){
                leftIntakeMotor.setPower(1);
                rightIntakeMotor.setPower(-1);
            }

            if(gamepad1.right_bumper){
                leftIntakeMotor.setPower(-1);
                rightIntakeMotor.setPower(1);
            }
        }
    }

//    public int getCurrentPos(){
//        return ();
//    }
}