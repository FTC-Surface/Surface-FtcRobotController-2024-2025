package org.firstinspires.ftc.teamcode.ArchivedCode.TeleOpArchived;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.Servo;

public class IntakeArmTestArchived extends LinearOpMode{

    private Servo armServo1;
    private Servo armServo2;
    private Servo Wrist;
    private Servo Claw;

    public static double positionOne = 1;
    public static double positionTwo = 1;
    public static double positionWrist = 1;
    public static double positionClaw = 0;


    public static int activateTestMode = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        armServo1 = hardwareMap.get(Servo.class,"iArm1");
        armServo2 = hardwareMap.get(Servo.class, "iArm2");
        Wrist = hardwareMap.get(Servo.class, "Wrist");
        Claw = hardwareMap.get(Servo.class, "Claw");
        armServo2.setDirection(Servo.Direction.REVERSE);
        waitForStart();

        while(opModeIsActive() && !isStopRequested()){

            if(activateTestMode == 0){
                armServo1.setPosition(positionOne);
                armServo2.setPosition(positionOne);
                Wrist.setPosition(positionWrist);
                Claw.setPosition(positionClaw);
            }

            if(activateTestMode == 1){
                if(gamepad2.x)//Down
                {
                    armServo1.setPosition(0.5);
                    //armServo2.setPosition(0.5);
                    Wrist.setPosition(0.4);
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
