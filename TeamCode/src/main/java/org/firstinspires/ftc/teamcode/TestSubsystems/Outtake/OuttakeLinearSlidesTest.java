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
    private DcMotorEx leftIntakeMotor;
    private DcMotorEx rightIntakeMotor;

    public static int targetPos = 0;

    public void runOpMode() {
        leftIntakeMotor = hardwareMap.get(DcMotorEx.class, "lOutLinearSlide");
        rightIntakeMotor = hardwareMap.get(DcMotorEx.class, "rOutLinearSlide");

        leftIntakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        leftIntakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightIntakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        leftIntakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightIntakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        while(opModeIsActive()){
            leftIntakeMotor.setTargetPosition(targetPos);
            rightIntakeMotor.setTargetPosition(targetPos);

            leftIntakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightIntakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftIntakeMotor.setPower(1);
            rightIntakeMotor.setPower(1);
        }
    }
}