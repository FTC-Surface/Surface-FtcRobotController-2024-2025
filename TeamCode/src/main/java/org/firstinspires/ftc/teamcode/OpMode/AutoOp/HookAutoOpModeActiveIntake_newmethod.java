package org.firstinspires.ftc.teamcode.OpMode.AutoOp;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Robot.RobotAuto;
import org.firstinspires.ftc.teamcode.Subsystems.Constants;

@Autonomous(name = "Hook Auto Mode Active Intake 2", group = "Auto Test")
@Config
public class HookAutoOpModeActiveIntake_newmethod extends LinearOpMode {

    SampleMecanumDrive drive;
    Constants.AutoState currentTraj = Constants.AutoState.idle;
    Pose2d startPose = new Pose2d(9,-60.5, Math.toRadians(90));

    void nextTraj(Constants.AutoState state){
        currentTraj = state;
    }

    @Override
    public void runOpMode() throws InterruptedException {

        RobotAuto robot = new RobotAuto(hardwareMap);

        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(startPose);

        currentTraj = Constants.AutoState.depositInit;

        waitForStart();
//        robot.oCloseClaw();
//        robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
//        robot.oArmHookdown();
        TrajectorySequence depositInit = drive.trajectorySequenceBuilder(startPose)
                .addTemporalMarker(0, () -> {
                    //robot.iArmStart();
                })
                .lineTo(new Vector2d(9,-34))
//                .addTemporalMarker(1.5, () -> {
//                    robot.oArmHookready();
//                })
//                .addTemporalMarker(1.8, () -> {
//                    robot.oOpenClaw();
//                })
                .waitSeconds(2)
                .build();

        TrajectorySequence grabFirst = drive.trajectorySequenceBuilder(depositInit.end())

                .setReversed(true)
                .splineToLinearHeading(new Pose2d(40, -43, Math.toRadians(70)), Math.toRadians(0))
//                .addTemporalMarker(0, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Ground);
//                    robot.oArmHookgrab();
//                })
                .setReversed(false)
                .turn(Math.toRadians(-100))
                .waitSeconds(0.5)

                .build();

        TrajectorySequence grabSecond = drive.trajectorySequenceBuilder(grabFirst.end())

                .lineToLinearHeading(new Pose2d(45, -43, Math.toRadians(60)))
                .turn(Math.toRadians(-90))
                .waitSeconds(0.5)

                .build();

        TrajectorySequence grabThird = drive.trajectorySequenceBuilder(grabSecond.end())

                .lineToLinearHeading(new Pose2d(50, -43, Math.toRadians(50)))
                .turn(Math.toRadians(-80))
                .waitSeconds(0.5)

                .build();

        TrajectorySequence depositFirst = drive.trajectorySequenceBuilder(grabThird.end())

                .lineToLinearHeading(new Pose2d(40, -58, Math.toRadians(90)))
                .waitSeconds(0.5)
                .lineTo(new Vector2d(40,-60.5))
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(6,-34, Math.toRadians(90)), Math.toRadians(90))

                .build();

        TrajectorySequence depositSecond = drive.trajectorySequenceBuilder(depositFirst.end())

                .setReversed(true)
                .splineToLinearHeading(new Pose2d(40,-58, Math.toRadians(90)), Math.toRadians(-90))
                .waitSeconds(0.5)
                .lineTo(new Vector2d(40,-60.5))
                .addTemporalMarker(1.5, () -> {
                    robot.oCloseClaw();
                })
                .addTemporalMarker(1.8, () -> {
                    robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
                    robot.oArmHookdown();
                })
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(3,-34, Math.toRadians(90)), Math.toRadians(90))

                .build();

        TrajectorySequence depositThird = drive.trajectorySequenceBuilder(depositSecond.end())

                .setReversed(true)
                .splineToLinearHeading(new Pose2d(40,-58, Math.toRadians(90)), Math.toRadians(-90))
                .waitSeconds(0.5)
                .lineTo(new Vector2d(40,-60.5))
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(0,-34, Math.toRadians(90)), Math.toRadians(90))
                .build();

        TrajectorySequence depositFourth = drive.trajectorySequenceBuilder(depositThird.end())

                .setReversed(true)
                .splineToLinearHeading(new Pose2d(40,-58, Math.toRadians(90)), Math.toRadians(-90))
                .waitSeconds(0.5)
                .lineTo(new Vector2d(40,-60.5))
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(-3,-34, Math.toRadians(90)), Math.toRadians(90))
                .build();

        TrajectorySequence park = drive.trajectorySequenceBuilder(depositFourth.end())

                .setReversed(true)
                .splineToLinearHeading(new Pose2d(40,-58, Math.toRadians(90)), Math.toRadians(-90))
                .build();

        while(opModeIsActive()){
            switch(currentTraj){
                case depositInit:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(depositInit);
                        nextTraj(Constants.AutoState.grabFirst);
                    }
                    break;
                case grabFirst:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(grabFirst);
                        nextTraj(Constants.AutoState.grabSecond);
                    }
                    break;

                case grabSecond:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(grabSecond);
                        nextTraj(Constants.AutoState.grabThird);
                    }
                    break;

                case grabThird:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(grabThird);
                        nextTraj(Constants.AutoState.depositFirst);
                    }
                    break;

                case depositFirst:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(depositFirst);
                        nextTraj(Constants.AutoState.depositSecond);
                    }
                    break;
                case depositSecond:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(depositFourth);
                        nextTraj(Constants.AutoState.depositThird);
                    }
                    break;
                case depositThird:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(depositThird);
                        nextTraj(Constants.AutoState.depositFourth);
                    }
                    break;
                case depositFourth:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(depositFourth);
                        nextTraj(Constants.AutoState.park);
                    }
                    break;
                case park:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(park);
                        nextTraj(Constants.AutoState.idle);
                    }
                case idle:
                    break;
            }
        }
    }
}
