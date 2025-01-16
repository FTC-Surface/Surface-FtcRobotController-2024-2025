package org.firstinspires.ftc.teamcode.TestSubsystems.Outtake;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Outtake Slides Test Op", group = "Tests")
@Config
public class OuttakeLinearSlidesTest extends LinearOpMode {

    //does intake and outtake linear slide use the same motors?
    private DcMotorEx leftOuttakeMotor;
    private DcMotorEx rightOuttakeMotor;

    public static int targetPos = 0;
    public static int maxHeight = 3200;
    public static int minHeight = 0;

    public int currentHeight = 0;

    public double motorPower = 0.75;

    public void runOpMode() {
        leftOuttakeMotor = hardwareMap.get(DcMotorEx.class, "outtakeLinearSlideOne");
        rightOuttakeMotor = hardwareMap.get(DcMotorEx.class, "outtakeLinearSlideTwo");

        leftOuttakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightOuttakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftOuttakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightOuttakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftOuttakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightOuttakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        telemetry.addData("Height", currentHeight);

        while(opModeIsActive()) {
            if(targetPos>=maxHeight)
                targetPos=maxHeight;
            else if(targetPos<=minHeight)
                targetPos=minHeight;

            telemetry.addData("Left Motor Position", leftOuttakeMotor.getCurrentPosition());
            telemetry.addData("Right Motor Position", rightOuttakeMotor.getCurrentPosition());
            telemetry.addData("Height", currentHeight);
            telemetry.addData("Is Busy", isBusy());
            telemetry.addData("Get Power", rightOuttakeMotor.getPower());

            telemetry.update();

            leftOuttakeMotor.setTargetPosition(targetPos);
            rightOuttakeMotor.setTargetPosition(targetPos);

            leftOuttakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightOuttakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            if(currentHeight > targetPos){
                leftOuttakeMotor.setPower(-motorPower);
                rightOuttakeMotor.setPower(-motorPower);
            }

            if(currentHeight < targetPos){
                leftOuttakeMotor.setPower(motorPower);
                rightOuttakeMotor.setPower(motorPower);
            }

            if(currentHeight == targetPos - 10){
                setPowerZero();
            }

            currentHeight = (rightOuttakeMotor.getCurrentPosition() + leftOuttakeMotor.getCurrentPosition())/2;


//            Max height = 3000

//            if (gamepad1.a && currentHeight < maxHeight) {
//                leftOuttakeMotor.setPower(0.3);
//                rightOuttakeMotor.setPower(0.3);
//            } else if (gamepad1.b && currentHeight > minHeight) {
//                leftOuttakeMotor.setPower(-0.3);
//                rightOuttakeMotor.setPower(-0.3);
//            } else {
//                leftOuttakeMotor.setPower(0);
//                rightOuttakeMotor.setPower(0);
//            }
        }
    }

    public boolean isBusy(){return Math.abs(currentHeight-targetPos) > 10;}

    public void setPowerZero(){
        leftOuttakeMotor.setPower(0);
        rightOuttakeMotor.setPower(0);
    }
}