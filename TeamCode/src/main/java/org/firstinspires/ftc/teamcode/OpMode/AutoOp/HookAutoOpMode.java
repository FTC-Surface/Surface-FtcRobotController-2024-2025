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

@Autonomous(name = "Hook Auto Mode", group = "Auto Test")
@Config
public class HookAutoOpMode extends LinearOpMode {

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

        TrajectorySequence depositInit = drive.trajectorySequenceBuilder(startPose)
                .lineTo(new Vector2d(9,-31))
                .build();

        TrajectorySequence grabFirst = drive.trajectorySequenceBuilder(depositInit.end())

                .lineToLinearHeading(new Pose2d(40, -45, Math.toRadians(75)))
                .lineToLinearHeading(new Pose2d(40, -46, Math.toRadians(-30)))
                .waitSeconds(0.5)

                .build();

        TrajectorySequence grabSecond = drive.trajectorySequenceBuilder(grabFirst.end())

                .lineToLinearHeading(new Pose2d(45, -45, Math.toRadians(60)))
                .lineToLinearHeading(new Pose2d(40, -46, Math.toRadians(-30)))
                .waitSeconds(0.5)

                .build();

        TrajectorySequence grabThird = drive.trajectorySequenceBuilder(grabSecond.end())

                .lineToLinearHeading(new Pose2d(50, -45, Math.toRadians(50)))
                .lineToLinearHeading(new Pose2d(40, -46, Math.toRadians(-30)))
                .waitSeconds(0.5)

                .build();

        TrajectorySequence depositFirst = drive.trajectorySequenceBuilder(grabThird.end())

                .lineToLinearHeading(new Pose2d(40, -58, Math.toRadians(90)))
                .waitSeconds(1)
                .lineTo(new Vector2d(40,-60.5))
                .waitSeconds(0.1)
                .splineToLinearHeading(new Pose2d(11,-31, Math.toRadians(90)), Math.toRadians(90))

                .build();

        TrajectorySequence depositSecond = drive.trajectorySequenceBuilder(depositFirst.end())

                .lineTo(new Vector2d(40, -58))
                .waitSeconds(1)
                .lineTo(new Vector2d(40,-60.5))
                .waitSeconds(0.1)
                .splineToLinearHeading(new Pose2d(7,-31, Math.toRadians(90)), Math.toRadians(90))

                .build();

        TrajectorySequence depositThird = drive.trajectorySequenceBuilder(depositSecond.end())

                .lineTo(new Vector2d(40, -58))
                .waitSeconds(1)
                .lineTo(new Vector2d(40,-60.5))
                .waitSeconds(0.1)
                .splineToLinearHeading(new Pose2d(5,-31, Math.toRadians(90)), Math.toRadians(90))

                .build();

        TrajectorySequence depositFourth = drive.trajectorySequenceBuilder(depositThird.end())

                .lineTo(new Vector2d(40, -58))
                .waitSeconds(1)
                .lineTo(new Vector2d(40,-60.5))
                .waitSeconds(0.1)
                .splineToLinearHeading(new Pose2d(3,-31, Math.toRadians(90)), Math.toRadians(90))

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
                        nextTraj(Constants.AutoState.idle);
                    }
                    break;
                case idle:
                    break;
            }
        }
    }
}
