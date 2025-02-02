package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake Bucket Test", group = "Tests")
@Config
public class IntakeBucketTest extends LinearOpMode {
    private Servo bucketServo;

    public static double pos = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        bucketServo = hardwareMap.get(Servo.class, "Intake Arm");

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){
            bucketServo.setPosition(pos);
        }
    }
}
