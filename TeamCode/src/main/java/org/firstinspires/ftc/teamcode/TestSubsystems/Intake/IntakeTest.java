package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake Test", group = "Tests Intake")
@Config
public class IntakeTest extends LinearOpMode{

    private Servo armServo, bucketServo;

    public static double armPos = 0;
    public static double bucketPos = 0;

    private DcMotorEx wheel;
    public static double wheelPow = 0;

    private DcMotorEx intakeLinearSlideOne;
    private DcMotorEx intakeLinearSlideTwo;

    public static int targetPos = 0;
    public static int maxHeight = 3000;
    public static int minHeight = 0;

    public static double motorPower = 0.3;

    public int currentHeight = 0;

    public static int activateTestMode = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        armServo = hardwareMap.get(Servo.class,"iArm");

        wheel = hardwareMap.get(DcMotorEx.class, "IntakeWheel");
        wheel.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        intakeLinearSlideOne = hardwareMap.get(DcMotorEx.class, "InLinearSlideOne");
        intakeLinearSlideTwo = hardwareMap.get(DcMotorEx.class, "InLinearSlideTwo");

        intakeLinearSlideOne.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        intakeLinearSlideTwo.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        intakeLinearSlideOne.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeLinearSlideTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intakeLinearSlideOne.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        intakeLinearSlideTwo.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        intakeLinearSlideOne.setDirection(DcMotorEx.Direction.REVERSE);
        intakeLinearSlideTwo.setDirection(DcMotorEx.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){

            if(activateTestMode == 0){
                //Without Slides

                armServo.setPosition(armPos);
                bucketServo.setPosition(bucketPos);
                wheel.setPower(wheelPow);
            }

            if(activateTestMode == 1){
                //With Slides (Manual)

                armServo.setPosition(armPos);
                bucketServo.setPosition(bucketPos);
                wheel.setPower(wheelPow);

                if (gamepad2.right_stick_y < -0.5 && currentHeight <= maxHeight) {
                    intakeLinearSlideOne.setPower(1);
                    intakeLinearSlideTwo.setPower(1);
                } else if (gamepad2.right_stick_y > 0.5 && currentHeight >= minHeight) {
                    intakeLinearSlideOne.setPower(-1);
                    intakeLinearSlideTwo.setPower(-1);
                } else {
                    intakeLinearSlideOne.setPower(0);
                    intakeLinearSlideTwo.setPower(0);
                }
            }

            if(activateTestMode == 2){
                //With Slides (Automatic)

                armServo.setPosition(armPos);
                wheel.setPower(wheelPow);

                intakeLinearSlideOne.setTargetPosition(-targetPos);
                intakeLinearSlideTwo.setTargetPosition(-targetPos);

                intakeLinearSlideOne.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                intakeLinearSlideTwo.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

                if (currentHeight > targetPos) {
                    intakeLinearSlideOne.setPower(-motorPower);
                    intakeLinearSlideTwo.setPower(-motorPower);
                }

                if (currentHeight < targetPos) {
                    intakeLinearSlideOne.setPower(motorPower);
                    intakeLinearSlideTwo.setPower(motorPower);
                }
            }
        }
    }
}
