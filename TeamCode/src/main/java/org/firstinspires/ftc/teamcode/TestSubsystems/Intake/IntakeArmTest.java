package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake Arm Test", group = "Tests")
@Config
public class IntakeArmTest extends LinearOpMode{

    private Servo armServo, bucketServo;

    public static double armPos = 0;

    public static int activateTestMode = 0;
    public static int bucketPos = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        armServo = hardwareMap.get(Servo.class,"iArm");

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){

            if(activateTestMode == 0){
                armServo.setPosition(armPos);
                bucketServo.setPosition(bucketPos);
            }

            if(activateTestMode == 1){
                if(gamepad2.cross)//Down
                {
                    armServo.setPosition(0.5);
                    bucketServo.setPosition(0);
                }
                if(gamepad2.triangle)//up
                {
                    armServo.setPosition(0);
                    bucketServo.setPosition(0);
                }
            }
        }
    }
}
