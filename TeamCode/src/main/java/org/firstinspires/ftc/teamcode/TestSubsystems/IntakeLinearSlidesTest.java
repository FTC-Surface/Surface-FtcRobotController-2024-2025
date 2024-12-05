package org.firstinspires.ftc.teamcode.TestSubsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake Linear Slides Test")
@Config
public class IntakeLinearSlidesTest extends LinearOpMode {

    private DcMotorEx leftIntakeMotor;
    private DcMotorEx rightIntakeMotor;

    public static double targetPos = 0.15;

    public void runOpMode() {

    }
}