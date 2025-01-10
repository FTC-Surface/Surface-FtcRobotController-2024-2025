package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake", group = "Tests")
@Config
public class IntakeTest extends LinearOpMode {

    CRServo lWrist;
    CRServo rWrist;

    private DcMotorEx intakeLinearSlideOne;
    private DcMotorEx intakeLinearSlideTwo;

    private Servo armServo;

    private CRServo rIntake;
    private CRServo lIntake;

    public static double targetRight = 0;
    public static double targetLeft = 0;

    public static double position;

    public static double lPower = 0;
    public static double rPower = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        lIntake = hardwareMap.get(CRServo.class, "lIntake");
        rIntake = hardwareMap.get(CRServo.class, "rIntake");

        lIntake.setDirection(CRServo.Direction.REVERSE);

        armServo = hardwareMap.get(Servo.class,"testServo");

        lWrist = hardwareMap.get(CRServo.class, "lWrist");
        rWrist = hardwareMap.get(CRServo.class, "rWrist");

        intakeLinearSlideOne = hardwareMap.get(DcMotorEx.class, "lInLinearSlide");
        intakeLinearSlideTwo = hardwareMap.get(DcMotorEx.class, "rInLinearSlide");

        intakeLinearSlideOne.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        intakeLinearSlideTwo.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        intakeLinearSlideOne.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        intakeLinearSlideTwo.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        if(opModeIsActive() && !isStopRequested()){
            rIntake.setPower(targetRight);
            lIntake.setPower(targetLeft);

            if(gamepad1.a){
                targetRight = 1;
                targetLeft = -1;
            }

            if(gamepad1.b){
                targetRight = -1;
                targetLeft = 1;
            }

            if(gamepad1.y){
                targetRight = 0;
                targetLeft = 0;
            }

            if(gamepad1.dpad_down){
                lWrist.setPower(0.2);
                rWrist.setPower(0.2);
            }

            if(gamepad1.dpad_up){
                lWrist.setPower(-0.2);
                rWrist.setPower(-0.2);
            }

            if(gamepad1.dpad_left){
                lWrist.setPower(-0.2);
                rWrist.setPower(0.2);
            }

            if(gamepad1.dpad_right){
                lWrist.setPower(0.2);
                rWrist.setPower(-0.2);
            }

            armServo.setPosition(position);
        }
    }
}
