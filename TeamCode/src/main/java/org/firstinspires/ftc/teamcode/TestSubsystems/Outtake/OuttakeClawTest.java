package org.firstinspires.ftc.teamcode.TestSubsystems.Outtake;

import com.acmerobotics.dashboard.config.Config;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Outtake Claw Test Op", group = "Tests")
@Config
public class OuttakeClawTest extends LinearOpMode {
    Servo clawR;
    Servo clawL;

    public static double targetRight = 0.3;
    public static double targetLeft = 0.415;

    public void runOpMode() {

        clawR = hardwareMap.get(Servo.class, "rightClaw");
        clawL = hardwareMap.get(Servo.class, "leftClaw");

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            clawR.setPosition(targetRight);
            clawL.setPosition(targetLeft);
        }
    }
}