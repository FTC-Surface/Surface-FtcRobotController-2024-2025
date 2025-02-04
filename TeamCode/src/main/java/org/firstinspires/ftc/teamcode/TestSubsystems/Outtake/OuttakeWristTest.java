package org.firstinspires.ftc.teamcode.TestSubsystems.Outtake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Outtake Wrist", group = "Tests")
@Config
public class OuttakeWristTest extends LinearOpMode {
    Servo armTwo;

    public static double armWrist = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        armTwo = hardwareMap.get(Servo.class, "OClaw");

        //start pos is 0
        //Max pos is 0.9

        //0.62 when raising

        //Drop odd 0.775

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            armTwo.setPosition(armWrist);
        }
    }
}
