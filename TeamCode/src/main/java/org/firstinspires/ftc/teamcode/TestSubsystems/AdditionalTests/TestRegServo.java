package org.firstinspires.ftc.teamcode.TestSubsystems.AdditionalTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "Servo Set Position Test", group = "Tests")
@Config
public class TestRegServo extends LinearOpMode {

    private Servo testServo;

    public static double testSetPos = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        testServo = hardwareMap.get(Servo.class, "testServo");

        waitForStart();

        while(opModeIsActive() &&!isStopRequested()){

            testServo.setPosition(testSetPos);

            telemetry.addData("Gamepad Left Stick", gamepad1.left_stick_x);
            telemetry.addData("Test Pos", testSetPos);
            telemetry.update();
        }
    }
}
