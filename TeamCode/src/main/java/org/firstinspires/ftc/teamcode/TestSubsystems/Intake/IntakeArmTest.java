package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake Arm Test", group = "Tests Intake")
@Config
public class IntakeArmTest extends LinearOpMode{

    private Servo armServo;
    public static double armPos = 0;

    private DcMotorEx wheel;
    public static double motorPow = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        armServo = hardwareMap.get(Servo.class,"iArm");

        wheel = hardwareMap.get(DcMotorEx.class, "IntakeWheel");
        wheel.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){
                armServo.setPosition(armPos);
                wheel.setPower(motorPow);
            }
        }
    }
