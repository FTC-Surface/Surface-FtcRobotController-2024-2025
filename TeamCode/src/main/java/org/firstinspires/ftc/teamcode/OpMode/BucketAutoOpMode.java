package org.firstinspires.ftc.teamcode.OpMode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Robot.RobotAuto;
import org.firstinspires.ftc.teamcode.Subsystems.Constants;

@Autonomous(name = "Bucket Auto Mode", group = "Auto Test")
@Config
public class BucketAutoOpMode extends LinearOpMode {

    SampleMecanumDrive drive;
    Constants.AutoState currentTraj = Constants.AutoState.idle;
    Pose2d startPose = new Pose2d(-35,-62, Math.toRadians(90));

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
                    robot.oElevMove(Constants.eOElevatorState.Ready);
                    robot.iArmHover();
                    robot.iOpenClaw();
                    robot.oCloseClaw();
                    robot.oArmStart();
                })
                .lineToLinearHeading(new Pose2d(-55, -50, Math.toRadians(45)))
               .addTemporalMarker(1, () -> {

                    robot.oElevMove(Constants.eOElevatorState.Basket);
                    robot.oArmDump();

                })

                .waitSeconds(1)
                .addTemporalMarker(2.5, () -> {
                    robot.oOpenClaw();
                })
                .waitSeconds(0.25)

                .lineToLinearHeading(new Pose2d(-52, -47, Math.toRadians(45)))
                .build();

        TrajectorySequence grabFirst = drive.trajectorySequenceBuilder(depositInit.end())
                .lineToLinearHeading(new Pose2d(-49,-34, Math.toRadians(90)))
                .addTemporalMarker(0, () -> {
                    robot.oElevMove(Constants.eOElevatorState.Ready);
                    robot.oArmTake();
                })
                .addTemporalMarker(3, () -> {
                    robot.iArmGrab();

                })
                .addTemporalMarker(3.5, () -> {
                    robot.iCloseClaw();

                })
                .addTemporalMarker(4, () -> {
                    robot.iArmStart();

                })
                .addTemporalMarker(4.5, () -> {
                    robot.iOpenClaw();

                })
                .waitSeconds(5)

                .build();

        TrajectorySequence depositFirst = drive.trajectorySequenceBuilder(grabFirst.end())
                .lineToLinearHeading(new Pose2d(-52, -47, Math.toRadians(45)))

               .addTemporalMarker(0, () -> {

                    robot.oElevMove(Constants.eOElevatorState.Grab);

                })
                .addTemporalMarker(0.5, () -> {
                    robot.oCloseClaw();
                })
                .addTemporalMarker(1, () -> {
                    robot.oElevMove(Constants.eOElevatorState.Basket);

                })
                .addTemporalMarker(1.25, () -> {
                    robot.oArmDump();

                })
                
                .addTemporalMarker(3, () -> {
                    robot.oOpenClaw();

                })
                .waitSeconds(5)

                .build();

        TrajectorySequence grabSecond = drive.trajectorySequenceBuilder(depositFirst.end())
                .lineToLinearHeading(new Pose2d(-62,-34, Math.toRadians(90)))
                .addTemporalMarker(0, () -> {
                    robot.oElevMove(Constants.eOElevatorState.Ready);
                    robot.oArmTake();
                })
                .addTemporalMarker(3, () -> {
                    robot.iArmGrab();

                })
                .addTemporalMarker(3.5, () -> {
                    robot.iCloseClaw();

                })
                .addTemporalMarker(4, () -> {
                    robot.iArmStart();

                })
                .addTemporalMarker(4.5, () -> {
                    robot.iOpenClaw();

                })
                .waitSeconds(5)
                .build();

        TrajectorySequence depositSecond = drive.trajectorySequenceBuilder(grabSecond.end())
                .lineToLinearHeading(new Pose2d(-55, -50, Math.toRadians(45)))
                .addTemporalMarker(0, () -> {

                    robot.oElevMove(Constants.eOElevatorState.Grab);

                })
                .addTemporalMarker(0.5, () -> {
                    robot.oCloseClaw();
                })
                .addTemporalMarker(1, () -> {
                    robot.oElevMove(Constants.eOElevatorState.Basket);

                })
                .addTemporalMarker(1.25, () -> {
                    robot.oArmDump();

                })

                .addTemporalMarker(3, () -> {
                    robot.oOpenClaw();

                })
                .waitSeconds(5)
                .lineToLinearHeading(new Pose2d(-52, -47, Math.toRadians(45)))
                .addTemporalMarker(5, () -> {
                    robot.oElevMove(Constants.eOElevatorState.Ready);
                    robot.oArmTake();

                })
                .build();

//        TrajectorySequence grabThird = drive.trajectorySequenceBuilder(depositSecond.end())
//                .lineToLinearHeading(new Pose2d(-62,-33, Math.toRadians(120)))
////                .waitSeconds(2)
////                .lineToLinearHeading(new Pose2d(-53, -52, Math.toRadians(45)))
//                .waitSeconds(5)
//                .build();
//
//        TrajectorySequence depositThird = drive.trajectorySequenceBuilder(grabThird.end())
//                .lineToLinearHeading(new Pose2d(-54, -51, Math.toRadians(45)))
//
//
//                .waitSeconds(2)
//
//                .addTemporalMarker(2, () -> {
//                    robot.oOpenClaw();
//
//                })
//
//                .waitSeconds(0.5)
//
//                .addTemporalMarker(2.5, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Ready);
//                })
                //.splineToLinearHeading(new Pose2d(-53, -52, Math.toRadians(45)),Math.toRadians(180))
//                .waitSeconds(5)
//                .build();

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
//                        nextTraj(Constants.AutoState.idle);
//                    }
//                    break;
                case idle:
                    break;
            }
        }
    }
}
