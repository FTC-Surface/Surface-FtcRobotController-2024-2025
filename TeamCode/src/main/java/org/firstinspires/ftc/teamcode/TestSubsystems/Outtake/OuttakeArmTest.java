package org.firstinspires.ftc.teamcode.TestSubsystems.Outtake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Outtake Arm", group = "Tests")
@Config
public class OuttakeArmTest extends LinearOpMode {
    Servo armOne;

    public static double armOneTarget = 0;
    public static double armTwoTarget = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        armOne = hardwareMap.get(Servo.class, "Outtake Arm Right");

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            armOne.setPosition(armOneTarget);
        }
    }
}
