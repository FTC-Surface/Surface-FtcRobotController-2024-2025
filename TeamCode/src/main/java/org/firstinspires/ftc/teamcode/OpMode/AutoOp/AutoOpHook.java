package org.firstinspires.ftc.teamcode.OpMode.AutoOp;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
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

        TrajectoryActionBuilder depositInit = drive.actionBuilder(initPos)
                .lineToY(-26)
                .lineToY(-40);

        TrajectoryActionBuilder grabFirst = drive.actionBuilder(initPos)
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(40, -43, Math.toRadians(65)), Math.toRadians(0))

                .turn(Math.toRadians(-100))
                .waitSeconds(0.5);

        TrajectoryActionBuilder grabSecond = drive.actionBuilder(initPos)
                .strafeToLinearHeading(new Vector2d(45,-43), Math.toRadians(55))
                .turn(Math.toRadians(-90))
                .waitSeconds(0.5);

        TrajectoryActionBuilder grabThird = drive.actionBuilder(initPos)
                .strafeToLinearHeading(new Vector2d(50,-43), Math.toRadians(40))
                .turn(Math.toRadians(-110))
                .waitSeconds(0.5);

        TrajectoryActionBuilder depositFirst = drive.actionBuilder(initPos)
                .strafeToLinearHeading(new Vector2d(40, -58), Math.toRadians(90))
                .waitSeconds(0.5)
                .lineToY(-60.5)
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(6,-34, Math.toRadians(90)), Math.toRadians(90));

        TrajectoryActionBuilder depositSecond = drive.actionBuilder(initPos)
                .lineToY(-34);

        TrajectoryActionBuilder depositThird = drive.actionBuilder(initPos)
                .lineToY(-34);

        TrajectoryActionBuilder depositFourth = drive.actionBuilder(initPos)
                .lineToY(-34);

        TrajectoryActionBuilder park = drive.actionBuilder(initPos)
                .lineToY(-34);

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){

        }
    }
}
