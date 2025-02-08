package org.firstinspires.ftc.teamcode.OpMode.AutoOp;

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

//                .addTemporalMarker(0, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Ready);
//                    robot.iArmStart();
//                    robot.oCloseClaw();
//                    robot.oArmTake();
//                })

                .lineToLinearHeading(new Pose2d(-55, -53, Math.toRadians(45)))

//                .addTemporalMarker(1, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Basket);
//                    robot.oArmDumpReady();
//                })
//
//                .addTemporalMarker(2, () -> {
//                   robot.oArmDumpRelease()
//                })

//                .addTemporalMarker(2.25, () -> {
//                    robot.oOpenClaw();
//                })

                .waitSeconds(3)

                .build();

        TrajectorySequence grabFirst = drive.trajectorySequenceBuilder(depositInit.end())

//                .addTemporalMarker(0, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Ready);
//                    robot.oArmTake();
//                })

                .lineToLinearHeading(new Pose2d(-48.25,-45, Math.toRadians(90)))

//                .addTemporalMarker(1.1, () -> {
//                    robot.iElevMove(Constants.eIElevatorState.AutoIntakePos);
//                    robot.iArmGrab();
//                })
//
//                .addTemporalMarker(2, robot::iWheelTakeBlock)
//
//                .addTemporalMarker(2.05, robot::iWheelNoBlock)

//                .addTemporalMarker(2.1, () -> {
//                    robot.iElevMove(Constants.eIElevatorState.ResetIntake);
//                    robot.iArmStart();
//                })

//                .addTemporalMarker(3, robot::iWheelOutBlock)
//
//                .addTemporalMarker(3.2, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Ground);
//                })

                .waitSeconds(4)

                .build();

        TrajectorySequence depositFirst = drive.trajectorySequenceBuilder(grabFirst.end())

//                .addTemporalMarker(0, robot::oCloseClaw)

                .lineToLinearHeading(new Pose2d(-55, -53, Math.toRadians(45)))

//                .addTemporalMarker(0.7, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Basket);
//                    robot.oArmDumpReady();
//                })

//                .addTemporalMarker(1.7, () -> {
//                   robot.oArmDumpRelease()
//                })

//                .addTemporalMarker(1.75, () -> {
//                    robot.oOpenClaw();
//                })

                .waitSeconds(5)

                .build();

        TrajectorySequence grabSecond = drive.trajectorySequenceBuilder(depositFirst.end())

//                .addTemporalMarker(0, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Ready);
//                    robot.oArmTake();
//                })

                .lineToLinearHeading(new Pose2d(-58.5,-45, Math.toRadians(90)))

//                .addTemporalMarker(1.1, () -> {
//                    robot.iElevMove(Constants.eIElevatorState.AutoIntakePos);
//                    robot.iArmGrab();
//                })
//
//                .addTemporalMarker(2, robot::iWheelTakeBlock)
//
//                .addTemporalMarker(2.05, robot::iWheelNoBlock)

//                .addTemporalMarker(2.1, () -> {
//                    robot.iElevMove(Constants.eIElevatorState.ResetIntake);
//                    robot.iArmStart();
//                })

//                .addTemporalMarker(3, robot::iWheelOutBlock)
//
//                .addTemporalMarker(3.2, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Ground);
//                })

                .waitSeconds(5)

                .build();

        TrajectorySequence depositSecond = drive.trajectorySequenceBuilder(grabSecond.end())

//                .addTemporalMarker(0, robot::oCloseClaw)

                .lineToLinearHeading(new Pose2d(-55, -53, Math.toRadians(45)))

//                .addTemporalMarker(0.7, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Basket);
//                    robot.oArmDumpReady();
//                })

//                .addTemporalMarker(1.7, () -> {
//                   robot.oArmDumpRelease()
//                })

//                .addTemporalMarker(1.75, () -> {
//                    robot.oOpenClaw();
//                })

                .waitSeconds(5)

                .build();

        TrajectorySequence grabThird = drive.trajectorySequenceBuilder(depositSecond.end())

//                .addTemporalMarker(0, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Ready);
//                    robot.oArmTake();
//                })

                .lineToLinearHeading(new Pose2d(-60,-50, Math.toRadians(100)))

//                .addTemporalMarker(1.1, () -> {
//                    robot.iElevMove(Constants.eIElevatorState.AutoIntakePos);
//                    robot.iArmGrab();
//                })
//
//                .addTemporalMarker(2, robot::iWheelTakeBlock)
//
//                .addTemporalMarker(2.05, robot::iWheelNoBlock)

//                .addTemporalMarker(2.1, () -> {
//                    robot.iElevMove(Constants.eIElevatorState.ResetIntake);
//                    robot.iArmStart();
//                })

//                .addTemporalMarker(3, robot::iWheelOutBlock)
//
//                .addTemporalMarker(3.2, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Ground);
//                })

                .waitSeconds(5)

                .build();

        TrajectorySequence depositThird = drive.trajectorySequenceBuilder(grabThird.end())

//                .addTemporalMarker(0, robot::oCloseClaw)

                .lineToLinearHeading(new Pose2d(-55, -53, Math.toRadians(45)))

//                .addTemporalMarker(0.7, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Basket);
//                    robot.oArmDumpReady();
//                })

//                .addTemporalMarker(1.7, () -> {
//                   robot.oArmDumpRelease()
//                })

//                .addTemporalMarker(1.75, () -> {
//                    robot.oOpenClaw();
//                })

                .waitSeconds(2)

                .build();

        TrajectorySequence reset = drive.trajectorySequenceBuilder(depositFirst.end())

                .splineToLinearHeading(new Pose2d(-25, 0, Math.toRadians(0)), Math.toRadians(0))
                .waitSeconds(2)

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
                        nextTraj(Constants.AutoState.depositFirst);
                    }

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
                        nextTraj(Constants.AutoState.grabThird);
                    }
                    break;
                case grabThird:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(grabThird);
                        nextTraj(Constants.AutoState.depositThird);
                    }
                    break;
                case depositThird:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(depositThird);
                        nextTraj(Constants.AutoState.park);
                    }
                    break;
                case park:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(reset);
                        nextTraj(Constants.AutoState.idle);
                    }
                    break;
                case idle:
                    break;
            }
        }
    }
}
