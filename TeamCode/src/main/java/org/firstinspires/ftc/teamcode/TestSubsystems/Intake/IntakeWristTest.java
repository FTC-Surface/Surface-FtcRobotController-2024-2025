package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "Intake Wrists Test", group = "Tests")
@Config
public class IntakeWristTest extends LinearOpMode {

    CRServo lWrist;
    CRServo rWrist;

    public static double lPower = 0;
    public static double rPower = 0;

    public static int mode = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        lWrist = hardwareMap.get(CRServo.class, "lWrist");
        rWrist = hardwareMap.get(CRServo.class, "rWrist");

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){
            lWrist.setPower(lPower);
            rWrist.setPower(rPower);

            if(mode == 1){
                lWrist.setPower(lPower);
                rWrist.setPower(rPower);
            }

            if(gamepad1.left_stick_y > 0){
                lWrist.setPower(0.2);
                rWrist.setPower(0.2);
            }

            if(gamepad1.left_stick_y < 0){
                lWrist.setPower(-0.2);
                rWrist.setPower(-0.2);
            }

            if(gamepad1.left_stick_x > 0){
                lWrist.setPower(0.2);
                rWrist.setPower(-0.2);
            }

            if(gamepad1.left_stick_x < 0){
                lWrist.setPower(-0.2);
                rWrist.setPower(0.2);
            }
        }
    }
}
