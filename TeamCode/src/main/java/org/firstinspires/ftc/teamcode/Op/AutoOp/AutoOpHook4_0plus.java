package org.firstinspires.ftc.teamcode.Op.AutoOp;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Newroadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.Robot.RobotAuto;

@Config
@Autonomous(name = "Auto 4+0 Plus", group = "Autonomous")
public class AutoOpHook4_0plus extends LinearOpMode {
    
    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d initPos = new Pose2d(9,-60.5, Math.toRadians(90));
        RobotAuto robot = new RobotAuto(hardwareMap);
        MecanumDrive drive = new MecanumDrive(hardwareMap, initPos);
        int t = 65;

        TrajectoryActionBuilder depositInit = drive.actionBuilder(initPos)
                .lineToY(-28);

        TrajectoryActionBuilder depositback = depositInit.endTrajectory().fresh()
                .lineToY(-36);

        TrajectoryActionBuilder push = depositback.endTrajectory().fresh()
                .strafeToConstantHeading(new Vector2d(25, -36))
                //push first
                .strafeToConstantHeading(new Vector2d(40, -36))
                .strafeToConstantHeading(new Vector2d(40, -12))
                .strafeToConstantHeading(new Vector2d(52, -12))
                .strafeToConstantHeading(new Vector2d(52, -50))

                //push second
                .strafeToConstantHeading(new Vector2d(52, -12))
                .strafeToConstantHeading(new Vector2d(60, -12))
                .strafeToConstantHeading(new Vector2d(60, -50))
                .strafeToConstantHeading(new Vector2d(44, -50))
                //.strafeToConstantHeading(new Vector2d(59, -45), Math.toRadians(90))

                //push third
//                .splineToConstantHeading(new Pose2d(60, -12, Math.toRadians(90)), Math.toRadians(0))
//                .lineToXConstantHeading(66.5)
//                .strafeToConstantHeading(new Vector2d(66.5, -52), Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(44, -62));

//        TrajectoryActionBuilder grabFirst = depositback.endTrajectory().fresh()
//
//                .setReversed(true)
//                .strafeToConstantHeading(new Vector2d(40, -43), Math.toRadians(60))
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
                .strafeToConstantHeading(new Vector2d(1,-26));

        TrajectoryActionBuilder depositSecond = depositFirst.endTrajectory().fresh()
                .strafeToConstantHeading(new Vector2d(43,-58))
                .strafeToConstantHeading(new Vector2d(43,-62))
                .strafeToConstantHeading(new Vector2d(3,-25));

        TrajectoryActionBuilder depositThird = depositSecond.endTrajectory().fresh()
                .strafeToConstantHeading(new Vector2d(43,-58))
                .strafeToConstantHeading(new Vector2d(43,-62))
                .strafeToConstantHeading(new Vector2d(5,-25));

        TrajectoryActionBuilder depositFourth = depositThird.endTrajectory().fresh()
                .strafeToConstantHeading(new Vector2d(43,-58))
                .strafeToConstantHeading(new Vector2d(43,-62))
                .strafeToConstantHeading(new Vector2d(7,-25));

        TrajectoryActionBuilder park = depositThird.endTrajectory().fresh()
                .strafeToConstantHeading(new Vector2d(43,-60));




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
                                        new SleepAction(0.4),
                                        robot.oClawOpenAction()
                                )
                        ),

                        //First, Second, Third Push

                        new ParallelAction(
                                pushAction,
                                new SequentialAction(
                                        new SleepAction(0.4),
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
                                    new SleepAction(0.6), robot.oClawOpenAction(),
                                    robot.oArmHookGrab(),
                                    new SleepAction(2.2),
                                    robot.oClawClosewAction(),
                                    new SleepAction(0.2),
                                    robot.oArmHookDownAction()
                                ),

                                depositSecondAction
                        ),

                        robot.oArmHookReadyAction(),

                        //Hang Third

                        new ParallelAction(
                                new SequentialAction(
                                        new SleepAction(0.6), robot.oClawOpenAction(),
                                        robot.oArmHookGrab(),
                                        new SleepAction(2.2),
                                        robot.oClawClosewAction(),
                                        new SleepAction(0.2),
                                        robot.oArmHookDownAction()),
                                depositThirdAction
                        ),

                        robot.oArmHookReadyAction(),

                        //Hang Fourth

//                        new ParallelAction(
//                                new SequentialAction(
//                                        new SleepAction(0.6), robot.oClawOpenAction(),
//                                        robot.oArmHookGrab(),
//                                        new SleepAction(2.2),
//                                        robot.oClawClosewAction(),
//                                        new SleepAction(0.2),
//                                        robot.oArmHookDownAction()),
//                                depositFourthAction
//                        ),
//
//                        robot.oArmHookReadyAction(),

                        //Park

                        new ParallelAction(
                                parkAction,
                                new SequentialAction(
                                        new SleepAction(0.6),
                                        robot.oClawOpenAction(),
                                        robot.oArmHookGrab()
                                )
                        )
                )
        );

    }
}
