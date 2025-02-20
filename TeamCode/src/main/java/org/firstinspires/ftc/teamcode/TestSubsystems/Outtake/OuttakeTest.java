package org.firstinspires.ftc.teamcode.TestSubsystems.Outtake;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Outtake", group = "Tests Outtake")
@Config
public class OuttakeTest extends LinearOpMode {
    Servo Arm;
    Servo Wrist;
    Servo Claw;
    Servo Sub;
    Servo SpeciArm;

    public static double ArmTarget = 0;
    public static double WristTarget = 0;
    public static double ClawTarget = 0.3;
    public static double SubTarget = 0;
    public static double SpeciArmTarget = 0;

    private DcMotorEx leftOuttakeMotor;

    public static int targetPos = 0;
    public static int maxHeight = 2100;
    public static int minHeight = 0;

    public int currentHeight = 0;

    public double motorPower = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {
        Arm = hardwareMap.get(Servo.class, "Outtake Arm Left");
        Wrist = hardwareMap.get(Servo.class, "Outtake Wrist Right");
        Wrist.setDirection(Servo.Direction.REVERSE);

        leftOuttakeMotor = hardwareMap.get(DcMotorEx.class, "outtakeLinearSlideOne");
        Claw = hardwareMap.get(Servo.class, "OClaw");
//
//        Sub = hardwareMap.get(Servo.class, "Outtake Sub");
//        SpeciArm = hardwareMap.get(Servo.class, "Speci Arm");


        leftOuttakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftOuttakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftOuttakeMotor.setDirection(DcMotorEx.Direction.REVERSE);

        leftOuttakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {

            telemetry.addData("Left Motor Position", leftOuttakeMotor.getCurrentPosition());
            //telemetry.addData("Right Motor Position", rightOuttakeMotor.getCurrentPosition());
            telemetry.addData("Height", currentHeight);
            telemetry.addData("Is Busy", isBusy());


            Arm.setPosition(ArmTarget);
            Wrist.setPosition(WristTarget);
            Claw.setPosition(ClawTarget);
//            Sub.setPosition(SubTarget);
//            SpeciArm.setPosition(SpeciArmTarget);

            leftOuttakeMotor.setTargetPosition(targetPos);

            leftOuttakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            if(currentHeight > targetPos){
                leftOuttakeMotor.setPower(-motorPower);
            }

            if(currentHeight < targetPos){
                leftOuttakeMotor.setPower(motorPower);
            }

            if(currentHeight == targetPos - 10){
                setPowerZero();
            }

            currentHeight =leftOuttakeMotor.getCurrentPosition();
        }
    }

    public boolean isBusy(){return Math.abs(currentHeight-targetPos) > 10;}

    public void setPowerZero(){
        leftOuttakeMotor.setPower(0);
    }
}
