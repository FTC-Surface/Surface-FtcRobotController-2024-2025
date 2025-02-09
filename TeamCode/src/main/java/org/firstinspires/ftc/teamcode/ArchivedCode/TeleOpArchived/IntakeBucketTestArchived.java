package org.firstinspires.ftc.teamcode.ArchivedCode.TeleOpArchived;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.Servo;

public class IntakeBucketTestArchived extends LinearOpMode {
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
