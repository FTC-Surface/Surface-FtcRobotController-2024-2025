package org.firstinspires.ftc.teamcode.TestSubsystems.Outtake;

import com.acmerobotics.dashboard.config.Config;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Outtake Claw Test Op", group = "Tests")
@Config
public class OuttakeClawTest extends LinearOpMode {
    Servo claw;

    public static double target = 0;

    public void runOpMode() {

        claw = hardwareMap.get(Servo.class, "Claw");

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            claw.setPosition(target);
        }
    }
}