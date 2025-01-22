package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake Wheel Test", group = "Tests")
@Config
public class IntakeWheelTest extends LinearOpMode{

    private DcMotorEx wheel;

    public static double motorPow = 0;

    public static int activateTestMode = 0;

    public static int multiplier = 1;

    @Override
    public void runOpMode() throws InterruptedException {

        wheel = hardwareMap.get(DcMotorEx.class, "IntakeWheel");
        wheel.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){
            if(activateTestMode == 0){
                wheel.setPower(motorPow * multiplier);

                if(gamepad1.circle){
                    wheel.setPower(0);
                }
            }
        }
    }
}
