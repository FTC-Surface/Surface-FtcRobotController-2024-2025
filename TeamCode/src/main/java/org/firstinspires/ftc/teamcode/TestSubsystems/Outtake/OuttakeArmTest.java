package org.firstinspires.ftc.teamcode.TestSubsystems.Outtake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Outtake Arm", group = "Tests Outtake")
@Config
public class OuttakeArmTest extends LinearOpMode {
    Servo arm;
    Servo wrist;

    public static double armTarget = 0;
    public static double wristTarget = 0;

    //Start pos is 0
    //Max pos is 1

    @Override
    public void runOpMode() throws InterruptedException {
        wrist = hardwareMap.get(Servo.class, "Outtake Wrist Right");
        arm = hardwareMap.get(Servo.class, "Outtake Arm Left");
        wrist.setDirection(Servo.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            arm.setPosition(armTarget);
            wrist.setPosition(wristTarget);
        }
    }
}
