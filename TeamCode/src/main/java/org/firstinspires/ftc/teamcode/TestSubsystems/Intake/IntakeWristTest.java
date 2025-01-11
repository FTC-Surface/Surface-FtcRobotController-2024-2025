package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake Wrists Test", group = "Tests")
@Config
public class IntakeWristTest extends LinearOpMode {

    Servo wrist;

    public static double pos;

    @Override
    public void runOpMode() throws InterruptedException {
        wrist = hardwareMap.get(Servo.class, "iWrist");

        wrist.setDirection(Servo.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){
            wrist.setPosition(pos);
            wrist.setPosition(pos);
        }
    }
}
