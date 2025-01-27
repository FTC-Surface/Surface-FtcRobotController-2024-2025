package org.firstinspires.ftc.teamcode.TestSubsystems.Outtake;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Outtake", group = "Tests")
@Config
public class OuttakeTest extends LinearOpMode {
    Servo Arm;
    Servo Wrist;
    Servo claw;
    private DcMotorEx wheel;

    public static double ArmTarget = 0.9;
    public static double WristTarget = 1;

    //does intake and outtake linear slide use the same motors?
    private DcMotorEx leftOuttakeMotor;
    private DcMotorEx rightOuttakeMotor;

    public static int targetPos = 0;
    public static double ClawTarget= 0.3;
    public static int maxHeight = 3000;
    public static int minHeight = 0;

    public int currentHeight = 0;

    public double motorPower = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {
        Arm = hardwareMap.get(Servo.class, "Outtake Wrist Left");
        Wrist = hardwareMap.get(Servo.class, "Outtake Arm Right");

        wheel = hardwareMap.get(DcMotorEx.class, "IntakeWheel");
        wheel.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        leftOuttakeMotor = hardwareMap.get(DcMotorEx.class, "outtakeLinearSlideOne");
        //rightOuttakeMotor = hardwareMap.get(DcMotorEx.class, "outtakeLinearSlideTwo");
        claw = hardwareMap.get(Servo.class, "OClaw");

        leftOuttakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //rightOuttakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftOuttakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightOuttakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        leftOuttakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        //rightOuttakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {

//            telemetry.addData("Left Motor Position", leftOuttakeMotor.getCurrentPosition());
//            //telemetry.addData("Right Motor Position", rightOuttakeMotor.getCurrentPosition());
//            telemetry.addData("Height", currentHeight);
//            telemetry.addData("Is Busy", isBusy());

//            telemetry.update();
            if(gamepad2.triangle)
            {
                Arm.setPosition(0.01);
                Wrist.setPosition(0.2);
                claw.setPosition(0.5);
            }
            if(gamepad2.right_bumper)
            {
                claw.setPosition(0.2);
            }
            if(gamepad2.left_bumper)
            {
                claw.setPosition(0.5);
            }
            if(gamepad2.cross)
            {
                Arm.setPosition(0.2);
                Wrist.setPosition(0.2);

            }
            if(gamepad2.circle){
                wheel.setPower(0.7);
            }
            else if (gamepad2.square) {
                wheel.setPower(-0.7);
            }
            else{
                wheel.setPower(0);
            }

//            Arm.setPosition(ArmTarget);
//            Wrist.setPosition(WristTarget);
//            claw.setPosition(ClawTarget);

//            leftOuttakeMotor.setTargetPosition(targetPos);
//            //rightOuttakeMotor.setTargetPosition(targetPos);
//
//            leftOuttakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            //rightOuttakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            if(currentHeight > targetPos){
//                leftOuttakeMotor.setPower(-motorPower);
//                //rightOuttakeMotor.setPower(-motorPower);
//            }
//
//            if(currentHeight < targetPos){
//                leftOuttakeMotor.setPower(motorPower);
//                //rightOuttakeMotor.setPower(motorPower);
//            }
//
//            if(currentHeight == targetPos - 10){
//                setPowerZero();
//            }

            //currentHeight = (rightOuttakeMotor.getCurrentPosition() + leftOuttakeMotor.getCurrentPosition())/2;
        }
    }

//    public boolean isBusy(){return Math.abs(currentHeight-targetPos) > 10;}
//
//    public void setPowerZero(){
//        leftOuttakeMotor.setPower(0);
//        //rightOuttakeMotor.setPower(0);
//    }
}
