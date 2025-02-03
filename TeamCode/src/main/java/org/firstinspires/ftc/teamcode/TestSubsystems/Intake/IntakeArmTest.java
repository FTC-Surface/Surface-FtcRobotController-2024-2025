package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake Test", group = "Tests")
@Config
public class IntakeArmTest extends LinearOpMode{

    private Servo armServo;
    private Servo bucketServo;
    private DcMotorEx wheel;

    public static double armPos = 0;
    public static double bucketPos = 0;
    public static double wheelPow = 0;

    public static int activateTestMode = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        armServo = hardwareMap.get(Servo.class,"iArm");

        wheel = hardwareMap.get(DcMotorEx.class, "IntakeWheel");
        wheel.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){

            if(activateTestMode == 0){
                armServo.setPosition(armPos);
                wheel.setPower(wheelPow);
            }

            if(activateTestMode == 1){
                if(gamepad2.cross)//Down
                {
                    armServo.setPosition(0.5);
//                    Wrist.setPosition(0.4);
                }
                if(gamepad2.triangle)//up
                {
                    armServo.setPosition(0);
//                    Wrist.setPosition(1);
                }

                if(gamepad2.right_trigger >= 0.5){
                    wheel.setPower(0.75);
                }
                else if (gamepad2.left_trigger >= 0.5)
                {
                    wheel.setPower(-0.75);
                }
                else{
                    wheel.setPower(0);
                }
            }
        }
    }
}
