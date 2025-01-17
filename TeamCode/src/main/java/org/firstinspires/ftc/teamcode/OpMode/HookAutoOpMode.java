package org.firstinspires.ftc.teamcode.OpMode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Robot.RobotAuto;
import org.firstinspires.ftc.teamcode.Subsystems.Constants;

@Autonomous(name = "Hook Auto Mode", group = "Auto Test")
@Config
public class HookAutoOpMode extends LinearOpMode {

    SampleMecanumDrive drive;
    Constants.AutoState currentTraj = Constants.AutoState.idle;
    Pose2d startPose = new Pose2d(10,-58, Math.toRadians(270));

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

        TrajectorySequence depositInit = drive.trajectorySequenceBuilder(startPose)
//                .addTemporalMarker(0, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
//                    robot.oCloseClaw();
//                    robot.oArmHookstart();
//                    robot.iArmStart();
//                    robot.iOpenClaw();
//                })

                //Add code for the first sample

                .lineTo(new Vector2d(10,-30))
//                .addDisplacementMarker(() ->{
//                    robot.oArmHookup();
//                })
                .lineTo(new Vector2d(10,-35))
//                .addDisplacementMarker(() ->{
//                    robot.oOpenClaw();
//                })

                .waitSeconds(3)

                .build();

        TrajectorySequence readySample = drive.trajectorySequenceBuilder(depositInit.end())
//                .addTemporalMarker(0, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Ready);
//                    robot.oArmTake();
//                })

                .lineToLinearHeading(new Pose2d(35, -35, Math.toRadians(270)))
                .lineToLinearHeading(new Pose2d(35, -7, Math.toRadians(270)))

                .splineToLinearHeading(new Pose2d(45, -13, Math.toRadians(180)), Math.toRadians(-90))
                .waitSeconds(2)

                .lineTo((new Vector2d(45, -51)))
                .splineToLinearHeading(new Pose2d(55, -13, Math.toRadians(180)), Math.toRadians(0))

                .waitSeconds(1)

                .lineTo((new Vector2d(55, -51)))
                .splineToLinearHeading(new Pose2d(62, -13, Math.toRadians(180)), Math.toRadians(0))

                .waitSeconds(1)

                .lineTo((new Vector2d(62, -51)))
//                .addDisplacementMarker(() ->{
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Grab);
//                    robot.oArmHookgrab();
//                })

                .splineToLinearHeading(new Pose2d(37, -55, Math.toRadians(90)), Math.toRadians(-90))

                .waitSeconds(3)

                .build();

        TrajectorySequence grabFirst = drive.trajectorySequenceBuilder(readySample.end())

                .lineTo((new Vector2d(35, -58)))
//                .addDisplacementMarker(() ->{
//                    robot.oCloseClaw();
//                })

                .waitSeconds(3)

                .build();

        TrajectorySequence depositFirst = drive.trajectorySequenceBuilder(grabFirst.end())

//                .addTemporalMarker(0.5, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
//                    robot.oArmHookstart();
//                })
                .splineToLinearHeading(new Pose2d(7, -35, Math.toRadians(270)), Math.toRadians(90))
                .lineTo(new Vector2d(7,-30))
//                .addDisplacementMarker(() ->{
//                    robot.oArmHookup();
//                })
                .lineTo((new Vector2d(7, -35)))
//                .addDisplacementMarker(() ->{
//                    robot.oOpenClaw();
//                    robot.oArmHookgrab();
//                })

                .waitSeconds(3)

                .build();

        TrajectorySequence grabSecond = drive.trajectorySequenceBuilder(depositFirst.end())

//                .addTemporalMarker(0, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Grab);
//
//                })

                .splineToLinearHeading(new Pose2d(37, -55, Math.toRadians(90)), Math.toRadians(-90))
                .lineTo((new Vector2d(37, -58)))
//                .addDisplacementMarker(() ->{
//                    robot.oCloseClaw();
//                })
                .waitSeconds(3)

                .build();

        TrajectorySequence depositSecond = drive.trajectorySequenceBuilder(grabSecond.end())

//                .addTemporalMarker(0.5, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
//                    robot.oArmHookstart();
//                })

                .splineToLinearHeading(new Pose2d(4, -30, Math.toRadians(270)), Math.toRadians(90))
//                .addDisplacementMarker(() ->{
//                    robot.oArmHookup();
//                })
                .lineTo((new Vector2d(4, -35)))
//                .addDisplacementMarker(() ->{
//                    robot.oOpenClaw();
//                    robot.oArmHookgrab();
//                })
                .waitSeconds(3)
                .build();

//        TrajectorySequence grabThird = drive.trajectorySequenceBuilder(depositSecond.end())
//
//                .splineToLinearHeading(new Pose2d(35, -58, Math.toRadians(90)), Math.toRadians(-90))
//                .lineTo((new Vector2d(35, -60)))
//
//                .waitSeconds(3)
//                .build();
//
//        TrajectorySequence depositThird = drive.trajectorySequenceBuilder(grabThird.end())
//
//                .splineToLinearHeading(new Pose2d(1, -30, Math.toRadians(270)), Math.toRadians(180))
//                .lineTo((new Vector2d(1, -35)))
//
//                .waitSeconds(3)
//                .build();
//
//        TrajectorySequence grabFourth = drive.trajectorySequenceBuilder(depositThird.end())
//
//                .splineToLinearHeading(new Pose2d(35, -58, Math.toRadians(90)), Math.toRadians(-90))
//                .lineTo((new Vector2d(35, -60)))
//
//                .waitSeconds(3)
//                .build();
//
//        TrajectorySequence depositFourth = drive.trajectorySequenceBuilder(grabFourth.end())
//
//                .splineToLinearHeading(new Pose2d(-2, -30, Math.toRadians(270)), Math.toRadians(180))
//                .lineTo((new Vector2d(-2, -35)))
//
//                .waitSeconds(3)
//                .build();


        while(opModeIsActive()){
            switch(currentTraj){
                case depositInit:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(depositInit);
                        nextTraj(Constants.AutoState.readySamples);
                    }
                    break;
                case readySamples:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(readySample);
                        nextTraj(Constants.AutoState.grabFirst);
                    }
                    break;
                case grabFirst:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(grabFirst);
                        nextTraj(Constants.AutoState.depositFirst);
                    }
                    break;
                case depositFirst:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(depositFirst);
                        nextTraj(Constants.AutoState.grabSecond);
                    }
                    break;
                case grabSecond:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(grabSecond);
                        nextTraj(Constants.AutoState.depositSecond);
                    }
                    break;
                case depositSecond:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(depositSecond);
                        nextTraj(Constants.AutoState.idle);
                    }
                    break;
//                case grabThird:
//                    if(!drive.isBusy()){
//                        drive.followTrajectorySequence(grabThird);
//                        nextTraj(Constants.AutoState.depositThird);
//                    }
//                    break;
//                case depositThird:
//                    if(!drive.isBusy()){
//                        drive.followTrajectorySequence(depositThird);
//                        nextTraj(Constants.AutoState.grabFourth);
//                    }
//                    break;
//                case grabFourth:
//                    if(!drive.isBusy()){
//                        drive.followTrajectorySequence(grabFourth);
//                        nextTraj(Constants.AutoState.depositFourth);
//                    }
//                    break;
//                case depositFourth:
//                    if(!drive.isBusy()){
//                        drive.followTrajectorySequence(depositFourth);
//                        nextTraj(Constants.AutoState.idle);
//                    }
//                    break;
                case idle:
                    break;
            }
        }
    }
}
