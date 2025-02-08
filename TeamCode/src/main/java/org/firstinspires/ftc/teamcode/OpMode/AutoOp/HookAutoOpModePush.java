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

@Autonomous(name = "Hook Auto Mode Push", group = "Auto Test")
@Config
public class HookAutoOpModePush extends LinearOpMode {

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

        //All times are rough estimates.

        TrajectorySequence depositInit = drive.trajectorySequenceBuilder(startPose)

//                .addTemporalMarker(0, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
//                    robot.oCloseClaw();
//                    robot.oArmHookup();
//
//                    robot.iArmStart();
//                })

                .lineTo((new Vector2d(9, -31)))

//                .addTemporalMarker(1.4, robot::oOpenClaw)

                .waitSeconds(0.5)

                .lineToLinearHeading(new Pose2d(25, -36, Math.toRadians(90)))

//                .addTemporalMarker(2.2, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Grab);
//                    robot.oArmHookgrab();
//                })

                .build();

        TrajectorySequence grabFirst = drive.trajectorySequenceBuilder(depositInit.end())

                .splineToLinearHeading(new Pose2d(45, -7, Math.toRadians(90)),Math.toRadians(0))
                .lineTo(new Vector2d(45, -48))

                .build();

        TrajectorySequence grabSecond = drive.trajectorySequenceBuilder(grabFirst.end())

                .splineToLinearHeading(new Pose2d(53, -7, Math.toRadians(90)), Math.toRadians(0))
                .lineTo(new Vector2d(53, -48))

                .build();

        TrajectorySequence grabThird = drive.trajectorySequenceBuilder(grabSecond.end())

                .splineToLinearHeading(new Pose2d(61, -7, Math.toRadians(90)), Math.toRadians(0))
                .lineTo(new Vector2d(61, -48))
                .waitSeconds(0.2)

                .build();

        TrajectorySequence depositFirst = drive.trajectorySequenceBuilder(grabThird.end())

                .splineToLinearHeading(new Pose2d(40, -58, Math.toRadians(90)),Math.toRadians(0))
                .waitSeconds(0.5)
                .lineTo(new Vector2d(40,-60.5))

//                .addTemporalMarker(1.7, robot::oCloseClaw)

                .waitSeconds(0.5)

//                .addTemporalMarker(2.2, () ->{
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
//                    robot.oArmHookup();
//                })

                .splineToLinearHeading(new Pose2d(11,-31, Math.toRadians(90)), Math.toRadians(90))
                .waitSeconds(0.3)
//
//                .addTemporalMarker(4.6, robot::oOpenClaw)

                .build();

        TrajectorySequence depositSecond = drive.trajectorySequenceBuilder(depositFirst.end())

                .lineTo(new Vector2d(40, -58))

//                .addTemporalMarker(3.3, robot::oCloseClaw)

                .waitSeconds(0.5)

//                .addTemporalMarker(3.8, () ->{
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
//                    robot.oArmHookup();
//                })

                .lineTo(new Vector2d(40,-60.5))
                .waitSeconds(0.3)
                .splineToLinearHeading(new Pose2d(7,-31, Math.toRadians(90)), Math.toRadians(90))
                .waitSeconds(0.3)
//
//                 .addTemporalMarker(6.1, robot::oOpenClaw)

                .build();

        TrajectorySequence depositThird = drive.trajectorySequenceBuilder(depositSecond.end())

                .lineTo(new Vector2d(40, -58))

//                .addTemporalMarker(3.3, robot::oCloseClaw)

                .waitSeconds(0.5)

//                .addTemporalMarker(3.8, () ->{
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
//                    robot.oArmHookup();
//                })

                .lineTo(new Vector2d(40,-60.5))
                .waitSeconds(0.3)
                .splineToLinearHeading(new Pose2d(5,-31, Math.toRadians(90)), Math.toRadians(90))
                .waitSeconds(0.3)

//                 .addTemporalMarker(6.1, robot::oOpenClaw)

                .build();

        TrajectorySequence depositFourth = drive.trajectorySequenceBuilder(depositThird.end())

                .lineTo(new Vector2d(40, -58))

//                .addTemporalMarker(3.3, robot::oCloseClaw)

                .waitSeconds(0.5)

//                .addTemporalMarker(3.8, () ->{
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
//                    robot.oArmHookup();
//                })

                .lineTo(new Vector2d(40,-60.5))
                .waitSeconds(0.3)
                .splineToLinearHeading(new Pose2d(3,-31, Math.toRadians(90)), Math.toRadians(90))
                .waitSeconds(0.3)

//                 .addTemporalMarker(6.1, robot::oOpenClaw)

                .build();

        TrajectorySequence park = drive.trajectorySequenceBuilder(depositFourth.end())

                .lineTo(new Vector2d(40, -58))
                .lineTo(new Vector2d(40,-60.5))

                .build();

        while(opModeIsActive()){
            switch(currentTraj){
                case depositInit:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(depositInit);
                        nextTraj(Constants.AutoState.idle);
                    }

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
