package org.firstinspires.ftc.teamcode.TestSubsystems.Outtake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Outtake Arm", group = "Tests")
@Config
public class OuttakeArmTest extends LinearOpMode {
    Servo arm;
    Servo wrist;

    public static double Target1 = 0;
    public static double Target2 = 0;

    //Start pos is 0
    //Max pos is 1

    @Override
    public void runOpMode() throws InterruptedException {
        arm = hardwareMap.get(Servo.class, "Outtake Arm Right");
        wrist = hardwareMap.get(Servo.class, "Outtake Wrist Left");
        arm.setDirection(Servo.Direction.REVERSE);
        wrist.setDirection(Servo.Direction.REVERSE);
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            arm.setPosition(Target1);
            wrist.setPosition(Target2);
        }
    }
}
