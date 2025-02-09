package org.firstinspires.ftc.teamcode.OpMode.AutoOp;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Newroadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotAuto;

@Config
@Autonomous(name = "Active Intake Hook", group = "Autonomous")
public class AutoOpHook extends LinearOpMode {

    RobotAuto robot = new RobotAuto(hardwareMap);

    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d initPos = new Pose2d(9,-60.5, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initPos);

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){

        }
    }
}
