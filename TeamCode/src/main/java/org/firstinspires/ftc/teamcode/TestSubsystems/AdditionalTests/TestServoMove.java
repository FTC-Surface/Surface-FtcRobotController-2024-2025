package org.firstinspires.ftc.teamcode.TestSubsystems.AdditionalTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "Servo Move Test", group = "Tests")
@Config
public class TestServoMove extends LinearOpMode {

    private Servo testServo;

    public static double testMove = 0;
    public static double testSensitivity = 0.005;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        testServo = hardwareMap.get(Servo.class, "testServo");

        waitForStart();

        while(opModeIsActive() &&!isStopRequested()){
//
//            if(testMove <= 1 && testMove >= 0){
//                testMove += gamepad1.left_stick_x * testSensitivity;
//            }
//
//            if(testMove > 1){
//                testMove = 1;
//            } else if(testMove < 0){
//                testMove = 0;
//            }

            testServo.setPosition(2);

            telemetry.addData("Gamepad Left Stick", gamepad1.left_stick_x);
            telemetry.addData("Test move", testMove);
            telemetry.update();
        }
    }
}
