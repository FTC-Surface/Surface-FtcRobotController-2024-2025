package org.firstinspires.ftc.teamcode.OpMode.AutoOp;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.AccelConstraint;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
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
        int t = 65;

        TrajectoryActionBuilder depositInit = drive.actionBuilder(initPos)
                .lineToY(-29);

        TrajectoryActionBuilder depositback = depositInit.endTrajectory().fresh()
                .lineToY(-36);

        TrajectoryActionBuilder push = depositback.endTrajectory().fresh()
                .strafeToLinearHeading(new Vector2d(25, -36), Math.toRadians(90))
                //push first
                .splineToLinearHeading(new Pose2d(50, -10, Math.toRadians(90)),Math.toRadians(0))
                .strafeToLinearHeading(new Vector2d(50, -42), Math.toRadians(90))

                //push second
                .splineToLinearHeading(new Pose2d(50, -10, Math.toRadians(90)), Math.toRadians(0))
                .lineToXConstantHeading(59)
                .strafeToLinearHeading(new Vector2d(59, -42), Math.toRadians(90))
                //.strafeToLinearHeading(new Vector2d(59, -45), Math.toRadians(90))

                //push third
                .splineToLinearHeading(new Pose2d(60, -10, Math.toRadians(90)), Math.toRadians(0))
                .lineToXConstantHeading(65.75)
                .strafeToLinearHeading(new Vector2d(65.75, -52), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(45, -61.5), Math.toRadians(90));

//        TrajectoryActionBuilder grabFirst = depositback.endTrajectory().fresh()
//
//                .setReversed(true)
//                .strafeToLinearHeading(new Vector2d(40, -43), Math.toRadians(60))
//                .turn(Math.toRadians(-100))
//                .waitSeconds(0.5);
//
//        TrajectoryActionBuilder grabSecond = grabFirst.endTrajectory().fresh()
//                .turn(Math.toRadians(70))
//                .turn(Math.toRadians(-80))
//                .waitSeconds(0.5);
//
//        TrajectoryActionBuilder grabThird = grabSecond.endTrajectory().fresh()
//                .turn(Math.toRadians(80))
//                .turn(Math.toRadians(-60))
//                .waitSeconds(0.5);

        TrajectoryActionBuilder depositFirst = push.endTrajectory().fresh()
                .strafeToLinearHeading(new Vector2d(6,-27), Math.toRadians(90));

        TrajectoryActionBuilder depositSecond = depositFirst.endTrajectory().fresh()
                .strafeToLinearHeading(new Vector2d(40,-62), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(5,-27), Math.toRadians(90));

        TrajectoryActionBuilder depositThird = depositSecond.endTrajectory().fresh()
                .strafeToLinearHeading(new Vector2d(40,-61.5), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(4,-27), Math.toRadians(90));

        TrajectoryActionBuilder depositFourth = depositThird.endTrajectory().fresh()
                .strafeToLinearHeading(new Vector2d(40,-62), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(3,-27), Math.toRadians(90));

        TrajectoryActionBuilder park = depositFourth.endTrajectory().fresh()
                .lineToY(-60);



        waitForStart();

        Action depositInitAction, depositbackAction, pushAction, grabFirstAction, depositFirstAction, grabSecondAction, depositSecondAction, grabThirdAction, depositThirdAction, depositFourthAction, parkAction;

        depositInitAction = depositInit.build();
        pushAction = push.build();
        depositbackAction = depositback.build();
        //grabFirstAction = grabFirst.build();
        depositFirstAction = depositFirst.build();
        //grabSecondAction = grabSecond.build();
        depositSecondAction = depositSecond.build();
        //grabThirdAction = grabThird.build();
        depositThirdAction = depositThird.build();
        depositFourthAction = depositFourth.build();
        parkAction = park.build();

        Actions.runBlocking(
                new SequentialAction(

                        //First Hang

                        new ParallelAction(
                                depositInitAction,
                                robot.oArmHookDownAction(),
                                robot.oClawClosewAction()
                        ),

                        robot.oArmHookReadyAction(),

                        new ParallelAction(
                                depositbackAction,
                                new SequentialAction(
                                        new SleepAction(0.25),
                                        robot.oClawOpenAction()
                                )
                        ),

                        //First, Second, Third Push

                        new ParallelAction(
                                pushAction,
                                new SequentialAction(
                                        new SleepAction(0.5),
                                        robot.oArmHookGrab()
                                )
                        ),

                        //Hang First

                        new ParallelAction(
                                new SequentialAction(
                                        new SleepAction(0),
                                        robot.oClawClosewAction(),
                                        new SleepAction(0.25),
                                        robot.oArmHookDownAction()
                                ),

                                depositFirstAction
                        ),

                        robot.oArmHookReadyAction(),

                        //Hang Second

                        new ParallelAction(
                                new SequentialAction(
                                    new SleepAction(0.5), robot.oClawOpenAction(),
                                    robot.oArmHookGrab(),
                                    new SleepAction(1.275),
                                    robot.oClawClosewAction(),
                                    new SleepAction(0.5),
                                    robot.oArmHookDownAction()
                                ),

                                depositSecondAction
                        ),

                        robot.oArmHookReadyAction(),

                        //Hang Third

                        new ParallelAction(
                                new SequentialAction(
                                        new SleepAction(0.5), robot.oClawOpenAction(),
                                        robot.oArmHookGrab(),
                                        new SleepAction(1.275),
                                        robot.oClawClosewAction(),
                                        new SleepAction(0.5),
                                        robot.oArmHookDownAction()),
                                depositThirdAction
                        ),

                        robot.oArmHookReadyAction(),

                        //Hang Fourth

                        new ParallelAction(
                                new SequentialAction(
                                        new SleepAction(0.5), robot.oClawOpenAction(),
                                        robot.oArmHookGrab(),
                                        new SleepAction(1.275),
                                        robot.oClawClosewAction(),
                                        new SleepAction(0.5),
                                        robot.oArmHookDownAction()),
                                depositFourthAction
                        ),

                        robot.oArmHookReadyAction(),

                        //Park

                        new ParallelAction(
                                parkAction,
                                new SequentialAction(
                                        new SleepAction(0.4),
                                        robot.oClawOpenAction(),
                                        robot.oArmHookGrab()
                                )
                        )
                )
        );

    }
}
