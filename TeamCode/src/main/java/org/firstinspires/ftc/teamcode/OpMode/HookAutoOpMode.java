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
    Pose2d startPose = new Pose2d(0,-60, Math.toRadians(270));

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
                .addTemporalMarker(0, () -> {
                    robot.oCloseClaw();
                    robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
                    robot.oArmHookstart();
                    robot.iArmStart();
                    robot.iOpenClaw();
                })
                .waitSeconds(0.5)

                //Add code for the first sample

                .lineTo(new Vector2d(0,-31))
                .addDisplacementMarker(() ->{
                    robot.oArmHookup();
                })
                .waitSeconds(0.125)
                .lineTo(new Vector2d(0,-45))
                .addTemporalMarker(3.5, () -> {
                    robot.oOpenClaw();
                })
                .waitSeconds(0.5)

                .build();



//        TrajectorySequence readySample1 = drive.trajectorySequenceBuilder(depositInit.end())
//                .addTemporalMarker(0.25, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Ready);
//                    robot.oArmTake();
//                })
//
//                .lineToLinearHeading(new Pose2d(35, -45, Math.toRadians(270)))
//                .lineToLinearHeading(new Pose2d(35, -7, Math.toRadians(270)))
//
//                .splineToLinearHeading(new Pose2d(45, -13, Math.toRadians(180)), Math.toRadians(-90))
//                .waitSeconds(2)
//
//                .lineTo((new Vector2d(45, -51)))
//                .splineToLinearHeading(new Pose2d(55, -13, Math.toRadians(180)), Math.toRadians(0))
//
//                .waitSeconds(1)
//
//                .lineTo((new Vector2d(55, -51)))
//                .waitSeconds(1)
//
//                .addDisplacementMarker(() ->{
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Grab);
//                    robot.oArmHookgrab();
//                })
//
//                .splineToLinearHeading(new Pose2d(42, -55, Math.toRadians(90)), Math.toRadians(-90))
//
//                .waitSeconds(3)
//
//                .build();
        TrajectorySequence readySample2 = drive.trajectorySequenceBuilder(depositInit.end())
                .addTemporalMarker(0.25, () -> {

                    robot.oArmHookgrab();
                })
                .addTemporalMarker(0.75, () -> {

                    robot.oElevMove(Constants.eOElevatorState.Clip_Grab);
                })


                .splineToLinearHeading(new Pose2d(42, -55, Math.toRadians(90)), Math.toRadians(-90))

                .waitSeconds(2)

                .build();
        TrajectorySequence reset = drive.trajectorySequenceBuilder(readySample2.end())

                .addTemporalMarker(0.25, () -> {
                    robot.oArmStart();
                })
                .addTemporalMarker(0.75, () -> {
                    robot.oElevMove(Constants.eOElevatorState.Ground);
                })


                .waitSeconds(3)

                .build();



        TrajectorySequence grabFirst = drive.trajectorySequenceBuilder(readySample2.end())

                .lineTo((new Vector2d(42, -58)))
                .addDisplacementMarker(() ->{
                    robot.oCloseClaw();
                })

                .waitSeconds(1)

                .build();

        TrajectorySequence depositFirst = drive.trajectorySequenceBuilder(grabFirst.end())

                .addTemporalMarker(0.5, () -> {
                    robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
                    robot.oArmHookstart();
                })
                .splineToLinearHeading(new Pose2d(4, -42, Math.toRadians(270)), Math.toRadians(90))
                .lineTo(new Vector2d(4,-33))
//                .addDisplacementMarker(() ->{
//                    robot.oArmHookup();
//                })
                .lineTo((new Vector2d(7, -42)))
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

                .splineToLinearHeading(new Pose2d(4, -35, Math.toRadians(270)), Math.toRadians(90))
                .lineTo(new Vector2d(4,-32))
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
                case reset:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(reset);
                        nextTraj(Constants.AutoState.idle);
                    }
                    break;
                case readySamples:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(readySample2);
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
