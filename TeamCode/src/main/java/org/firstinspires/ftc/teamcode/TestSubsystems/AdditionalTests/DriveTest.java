package org.firstinspires.ftc.teamcode.TestSubsystems.AdditionalTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Drive Test", group = "Tests")
@Config
public class DriveTest extends LinearOpMode {

    DcMotorEx topLeftMotor, topRightMotor, bottomLeftMotor, bottomRightMotor;
    double maxSpeed;

    @Override
    public void runOpMode() throws InterruptedException {
        topLeftMotor = hardwareMap.get(DcMotorEx.class, "topLeft");
        topRightMotor = hardwareMap.get(DcMotorEx.class, "topRight");
        bottomLeftMotor = hardwareMap.get(DcMotorEx.class, "bottomLeft");
        bottomRightMotor = hardwareMap.get(DcMotorEx.class, "bottomRight");

        topLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        topRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bottomLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bottomRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        topRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        bottomRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive() &&!isStopRequested()){
            double drive = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            double[] motorPower = {
                    drive-strafe-rotate,
                    drive+strafe-rotate,
                    drive+strafe+rotate,
                    drive-strafe+rotate};

            maxSpeed = Math.abs(motorPower[0]);

            for (double power : motorPower){
                if(Math.abs(power) > maxSpeed){
                    maxSpeed = Math.abs(power);
                }
            }

            if(maxSpeed > 1){
                for (int i = 0; i < motorPower.length; i++){
                    motorPower[i] /= maxSpeed;
                }
            }

            topLeftMotor.setPower(motorPower[0]);
            bottomLeftMotor.setPower(motorPower[1]);
            topRightMotor.setPower(motorPower[2]);
            bottomRightMotor.setPower(motorPower[3]);
        }
    }
}
