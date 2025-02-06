package org.firstinspires.ftc.teamcode.TestSubsystems.Outtake;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Outtake Slides Test Op", group = "Tests Outtake")
@Config
public class OuttakeLinearSlidesTest extends LinearOpMode {

    private DcMotorEx leftOuttakeMotor;

    public static int targetPos = 0;
    public static int maxHeight = 2100;
    public static int minHeight = 0;

    public int currentHeight = 0;

    public static double motorPower = 1;

    public void runOpMode() {
        leftOuttakeMotor = hardwareMap.get(DcMotorEx.class, "outtakeLinearSlideOne");

        leftOuttakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftOuttakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        leftOuttakeMotor.setDirection(DcMotorEx.Direction.REVERSE);

        leftOuttakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        telemetry.addData("Height", currentHeight);

        while (opModeIsActive()) {
            if (targetPos >= maxHeight)
                targetPos = maxHeight;
            else if (targetPos <= minHeight)
                targetPos = minHeight;

            telemetry.addData("Left Motor Position", leftOuttakeMotor.getCurrentPosition());
            telemetry.addData("Height", currentHeight);
            telemetry.addData("Is Busy", isBusy());

            telemetry.update();

            leftOuttakeMotor.setTargetPosition(targetPos);

            leftOuttakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            if (currentHeight > targetPos) {
                leftOuttakeMotor.setPower(-motorPower);
            }

            if (currentHeight < targetPos) {
                leftOuttakeMotor.setPower(motorPower);
            }

            if (currentHeight == targetPos - 10) {
                setPowerZero();
            }

            currentHeight = leftOuttakeMotor.getCurrentPosition();
        }
    }

    public boolean isBusy(){return Math.abs(currentHeight-targetPos) > 10;}

    public void setPowerZero(){
        leftOuttakeMotor.setPower(0);
    }
}