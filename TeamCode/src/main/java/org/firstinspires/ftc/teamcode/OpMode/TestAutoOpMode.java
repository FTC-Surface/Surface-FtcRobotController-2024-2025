package org.firstinspires.ftc.teamcode.OpMode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.Constants;

@Autonomous(name = "Test Auto", group = "Auto Test")
@Config
public class TestAutoOpMode extends LinearOpMode {

    SampleMecanumDrive drive;
    Constants.AutoState currentTraj = Constants.AutoState.idle;
    Pose2d startPose = new Pose2d(-40,-58, Math.toRadians(90));

    void nextTraj(Constants.AutoState state){
        currentTraj = state;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(startPose);

        Robot robot = new Robot(hardwareMap);

        currentTraj = Constants.AutoState.depositInit;

        waitForStart();

        robot.iArmHover();
        robot.iOpenClaw();
        robot.oElevMove(Constants.eOElevatorState.Ground,0);

        TrajectorySequence depositInit = drive.trajectorySequenceBuilder(startPose)
                .splineToLinearHeading(new Pose2d(-55, -54, Math.toRadians(45)), 180 )
                .build();

        TrajectorySequence grabFirst = drive.trajectorySequenceBuilder(depositInit.end())
                .lineToLinearHeading(new Pose2d(-48,-37, Math.toRadians(90)))
                .build();

        TrajectorySequence depositFirst = drive.trajectorySequenceBuilder(grabFirst.end())
                .lineToLinearHeading(new Pose2d(-55, -54, Math.toRadians(45)))
                .build();

        TrajectorySequence grabSecond = drive.trajectorySequenceBuilder(depositInit.end())
                .lineToLinearHeading(new Pose2d(-58,-37, Math.toRadians(90)))
                .build();

        TrajectorySequence depositSecond = drive.trajectorySequenceBuilder(grabFirst.end())
                .lineToLinearHeading(new Pose2d(-55, -54, Math.toRadians(45)))
                .build();

        TrajectorySequence grabThird = drive.trajectorySequenceBuilder(depositInit.end())
                .lineToLinearHeading(new Pose2d(-58,-37, Math.toRadians(90)))
                .build();

        TrajectorySequence depositThird = drive.trajectorySequenceBuilder(grabFirst.end())
                .lineToLinearHeading(new Pose2d(-55, -54, Math.toRadians(45)))
                .build();

        TrajectorySequence park = drive.trajectorySequenceBuilder(startPose)
                .lineToLinearHeading(new Pose2d(60, -58, 0))
                .build();

        while(opModeIsActive()){
            switch(currentTraj){
                case depositInit:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(depositInit);
                        nextTraj(Constants.AutoState.idle);
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
                        nextTraj(Constants.AutoState.idle);
                    }
                    break;
                case idle:
                    break;
            }
        }
    }
}
