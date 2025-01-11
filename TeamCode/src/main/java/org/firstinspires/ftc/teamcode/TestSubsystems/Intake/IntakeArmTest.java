package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake Arm Test", group = "Tests")
@Config
public class IntakeArmTest extends LinearOpMode{

    private Servo armServo;
    private Servo wrist;

    public static double positionOne;
    public static double positionTwo;

    @Override
    public void runOpMode() throws InterruptedException {
        armServo = hardwareMap.get(Servo.class,"iArm");
        wrist = hardwareMap.get(Servo.class, "iWrist");

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){
            armServo.setPosition(positionOne);
            wrist.setPosition(positionTwo);
        }
    }
}
