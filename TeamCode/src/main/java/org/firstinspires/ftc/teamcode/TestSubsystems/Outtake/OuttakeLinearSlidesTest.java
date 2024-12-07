package org.firstinspires.ftc.teamcode.TestSubsystems.Outtake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Outtake Linear Slides Test")
@Config
public class OuttakeLinearSlidesTest extends LinearOpMode {

    //does intake and outtake linear slide use the same motors?
    private DcMotorEx leftOuttakeMotor;
    private DcMotorEx rightOuttakeMotor;

    public static int targetPos = 0;

    public void runOpMode() {
        leftOuttakeMotor = hardwareMap.get(DcMotorEx.class, "lOutLinearSlide");
        rightOuttakeMotor = hardwareMap.get(DcMotorEx.class, "rOutLinearSlide");

        leftOuttakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        leftOuttakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightOuttakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        leftOuttakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightOuttakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        while(opModeIsActive()){
            leftOuttakeMotor.setTargetPosition(targetPos);
            rightOuttakeMotor.setTargetPosition(targetPos);

            leftOuttakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftOuttakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftOuttakeMotor.setPower(1);
            leftOuttakeMotor.setPower(1);
        }
    }
}