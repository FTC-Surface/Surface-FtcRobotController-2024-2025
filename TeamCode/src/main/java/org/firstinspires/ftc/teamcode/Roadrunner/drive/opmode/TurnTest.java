package org.firstinspires.ftc.teamcode.Roadrunner.drive.opmode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeArm;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeClaw;

/*
 * This is a simple routine to test turning capabilities.
 */
@Config
@Autonomous(group = "drive")
public class TurnTest extends LinearOpMode {
    public static double ANGLE = 90; // deg

    IntakeArm iArm = new IntakeArm();
    IntakeClaw iClaw = new IntakeClaw();

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

//        iArm.init(hardwareMap);
//        iClaw.init(hardwareMap);

        waitForStart();

//        iArm.upPos();
//        iClaw.closeClawIn();

        if (isStopRequested()) return;

        drive.turn(Math.toRadians(ANGLE));
    }
}
