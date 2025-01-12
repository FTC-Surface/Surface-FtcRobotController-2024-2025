package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake Arm Test", group = "Tests")
@Config
public class IntakeArmTest extends LinearOpMode{

    private Servo armServo1;
    private Servo armServo2;
    private Servo Wrist;
    private Servo Claw;

    public static double positionOne = 0.5;
    public static double positionTwo = 0.5;
    public static double positionWrist = 0.45;
    public static double positionClaw = 0.6;

    public static int activateTestMode;

    @Override
    public void runOpMode() throws InterruptedException {
        armServo1 = hardwareMap.get(Servo.class,"iArm1");
        //armServo2 = hardwareMap.get(Servo.class, "iArm2");
        Wrist = hardwareMap.get(Servo.class, "Wrist");
        Claw = hardwareMap.get(Servo.class, "Claw");

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){

            if(activateTestMode == 0){
                armServo1.setPosition(positionOne);
                //armServo2.setPosition(positionTwo);
                Wrist.setPosition(positionWrist);
                Claw.setPosition(positionClaw);
            }

            if(activateTestMode == 1){
                if(gamepad2.x)//Down
                {
                    armServo1.setPosition(0.5);
                    //armServo2.setPosition(0.5);
                    Wrist.setPosition(0.45);
                }
                if(gamepad2.y)//up
                {
                    armServo1.setPosition(1);
                    //armServo2.setPosition(1);
                    Wrist.setPosition(1);
                }
                if(gamepad2.left_bumper)//Open
                {
                    Claw.setPosition(0.6);
                }
                if(gamepad2.right_bumper)//Close
                {
                    Claw.setPosition(0);
                }
            }
        }
    }
}
