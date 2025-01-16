package org.firstinspires.ftc.teamcode.OpMode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotAuto;
import org.firstinspires.ftc.teamcode.Subsystems.Constants;

@Autonomous(name = "Test Auto", group = "Auto Test")
@Config
public class RedBucketAutoOpMode extends LinearOpMode {

    SampleMecanumDrive drive;
    Constants.AutoState currentTraj = Constants.AutoState.idle;
    Pose2d startPose = new Pose2d(-40,-58, Math.toRadians(90));

    void nextTraj(Constants.AutoState state){
        currentTraj = state;
    }

    @Override
    public void runOpMode() throws InterruptedException {

        RobotAuto robot = new RobotAuto(hardwareMap);

        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(startPose);

        currentTraj = Constants.AutoState.depositInit;

        while(opModeInInit()){
            robot.oElevMove(Constants.eOElevatorState.Ready);

            robot.iArmHover();
            robot.iOpenClaw();

            robot.oOpenClaw();
        }

        waitForStart();

        robot.oElevMove(Constants.eOElevatorState.Ready);

        TrajectorySequence depositInit = drive.trajectorySequenceBuilder(startPose)
                .lineToLinearHeading(new Pose2d(-53, -52, Math.toRadians(45)))
//                .addDisplacementMarker(()->{
//                    robot.oElevMove(Constants.eOElevatorState.Basket);
//
//                })
                //.splineToLinearHeading(new Pose2d(-53, -52, Math.toRadians(45)),Math.toRadians(180))
                .waitSeconds(4)
                .build();

        TrajectorySequence grabFirst = drive.trajectorySequenceBuilder(depositInit.end())
                .lineToLinearHeading(new Pose2d(-48,-37, Math.toRadians(90)))
                .waitSeconds(2)
                .lineToLinearHeading(new Pose2d(-53, -52, Math.toRadians(45)))
                .waitSeconds(4)
                .build();

        TrajectorySequence grabSecond = drive.trajectorySequenceBuilder(depositInit.end())
                .lineToLinearHeading(new Pose2d(-58,-37, Math.toRadians(90)))
                .waitSeconds(2)
                .lineToLinearHeading(new Pose2d(-53, -52, Math.toRadians(45)))
                .waitSeconds(4)
                .build();

        TrajectorySequence grabThird = drive.trajectorySequenceBuilder(depositInit.end())
                .lineToLinearHeading(new Pose2d(-62,-37, Math.toRadians(120)))
                .waitSeconds(2)
                .lineToLinearHeading(new Pose2d(-53, -52, Math.toRadians(45)))
                .waitSeconds(4)
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
                case grabSecond:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(grabSecond);
                        nextTraj(Constants.AutoState.depositSecond);
                    }
                    break;
                case grabThird:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(grabThird);
                        nextTraj(Constants.AutoState.depositThird);
                    }
                    break;
                case idle:
                    break;
            }
        }
    }
}
