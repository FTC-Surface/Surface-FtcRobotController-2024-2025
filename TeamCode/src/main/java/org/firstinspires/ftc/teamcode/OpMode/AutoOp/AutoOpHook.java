package org.firstinspires.ftc.teamcode.OpMode.AutoOp;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Newroadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.Robot.RobotAuto;
import org.firstinspires.ftc.teamcode.Subsystems.Constants;

@Config
@Autonomous(name = "Active Intake Hook", group = "Autonomous")
public class AutoOpHook extends LinearOpMode {
    
    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d initPos = new Pose2d(9,-60.5, Math.toRadians(90));
        RobotAuto robot = new RobotAuto(hardwareMap);
        MecanumDrive drive = new MecanumDrive(hardwareMap, initPos);

        TrajectoryActionBuilder depositInit = drive.actionBuilder(initPos)
                .lineToY(-30)
                .waitSeconds(0.5);

        TrajectoryActionBuilder grabFirst = depositInit.endTrajectory().fresh()
                .lineToY(-38)
                .setReversed(true)
                .strafeToLinearHeading(new Vector2d(40, -43), Math.toRadians(60))
                .turn(Math.toRadians(-100))
                .waitSeconds(0.5);

        TrajectoryActionBuilder grabSecond = grabFirst.endTrajectory().fresh()
                .turn(Math.toRadians(70))
                .turn(Math.toRadians(-80))
                .waitSeconds(0.5);

        TrajectoryActionBuilder grabThird = grabSecond.endTrajectory().fresh()
                .turn(Math.toRadians(80))
                .turn(Math.toRadians(-60))
                .waitSeconds(0.5);

        TrajectoryActionBuilder depositFirst = grabThird.endTrajectory().fresh()
                .strafeToLinearHeading(new Vector2d(40, -58), Math.toRadians(90))
                .waitSeconds(0.5)
                .lineToY(-60.5)
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(6,-30, Math.toRadians(90)), Math.toRadians(90));

        TrajectoryActionBuilder depositSecond = depositFirst.endTrajectory().fresh()
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(40,-56, Math.toRadians(90)), Math.toRadians(-90))
                .waitSeconds(0.5)
                .lineToY(-58.5)
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(3,-30, Math.toRadians(90)), Math.toRadians(90));

        TrajectoryActionBuilder depositThird = depositSecond.endTrajectory().fresh()
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(40,-58, Math.toRadians(90)), Math.toRadians(-90))
                .waitSeconds(0.5)
                .lineToY(-60.5)
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(0,-30, Math.toRadians(90)), Math.toRadians(90));

        TrajectoryActionBuilder depositFourth = depositThird.endTrajectory().fresh()
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(40,-58, Math.toRadians(90)), Math.toRadians(-90))
                .waitSeconds(0.5)
                .lineToY(-60.5)
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(0,-30, Math.toRadians(90)), Math.toRadians(90));

        TrajectoryActionBuilder park = depositFourth.endTrajectory().fresh()
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(40,-58, Math.toRadians(90)), Math.toRadians(-90));

        waitForStart();

        Action depositInitAction, grabFirstAction, depositFirstAction, grabSecondAction, depositSecondAction, grabThirdAction, depositThirdAction, depositFourthAction, parkAction;

        depositInitAction = depositInit.build();
        grabFirstAction = grabFirst.build();
        depositFirstAction = depositFirst.build();
        grabSecondAction = grabSecond.build();
        depositSecondAction = depositSecond.build();
        grabThirdAction = grabThird.build();
        depositThirdAction = depositThird.build();
        depositFourthAction = depositFourth.build();
        parkAction = park.build();

        Actions.runBlocking(
                new SequentialAction(
                        depositInitAction,
                        grabFirstAction,
                        grabSecondAction,
                        grabThirdAction,
                        depositFirstAction,
                        depositSecondAction,
                        depositThirdAction,
                        depositFourthAction,
                        parkAction
                )
        );

    }
}
