package org.firstinspires.ftc.teamcode.TestSubsystems.Outtake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Outtake Arm", group = "Tests")
@Config
public class OuttakeArmTest extends LinearOpMode {
    Servo armOne;
    Servo armTwo;

    public static double armOneTarget = 0;

    //Start pos is 0
    //Max pos is 1

    @Override
    public void runOpMode() throws InterruptedException {
        armOne = hardwareMap.get(Servo.class, "Outtake Arm Right");
        armTwo = hardwareMap.get(Servo.class, "Outtake Wrist Left");

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            armOne.setPosition(armOneTarget);
        }
    }
}
