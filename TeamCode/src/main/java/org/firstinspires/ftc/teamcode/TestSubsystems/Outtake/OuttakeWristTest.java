package org.firstinspires.ftc.teamcode.TestSubsystems.Outtake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Outtake Wrist", group = "Tests")
@Config
public class OuttakeWristTest extends LinearOpMode {
    Servo armTwo;

    public static double armTwoTarget = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        armTwo = hardwareMap.get(Servo.class, "Outtake Wrist Left");

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            armTwo.setPosition(armTwoTarget);
        }
    }
}
