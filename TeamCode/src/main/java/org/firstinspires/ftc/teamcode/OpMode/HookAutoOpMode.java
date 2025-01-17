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
    Pose2d startPose = new Pose2d(-35,-60, Math.toRadians(90));

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

                //Add code for the first sample

                .lineTo(new Vector2d(10,-30))
                .lineTo(new Vector2d(10,-40))

                .waitSeconds(3)

                .build();

        TrajectorySequence readySample = drive.trajectorySequenceBuilder(depositInit.end())

                .splineToLinearHeading(new Pose2d(48, -13, Math.toRadians(90)), Math.toRadians(270))

                .waitSeconds(1)

                .lineTo((new Vector2d(48, -49)))
                .splineToLinearHeading(new Pose2d(59, -13, Math.toRadians(90)), Math.toRadians(0))

                .waitSeconds(1)

                .lineTo((new Vector2d(59, -49)))
                .splineToLinearHeading(new Pose2d(62, -13, Math.toRadians(90)), Math.toRadians(0))

                .waitSeconds(1)

                .lineTo((new Vector2d(62, -49)))

                .waitSeconds(3)

                .build();

        TrajectorySequence grabFirst = drive.trajectorySequenceBuilder(readySample.end())

                .splineToLinearHeading(new Pose2d(35, -58, Math.toRadians(90)), Math.toRadians(-90))
                .lineTo((new Vector2d(35, -60)))

                .waitSeconds(3)

                .build();

        TrajectorySequence depositFirst = drive.trajectorySequenceBuilder(grabFirst.end())

                .splineToLinearHeading(new Pose2d(7, -30, Math.toRadians(270)), Math.toRadians(180))
                .lineTo((new Vector2d(7, -35)))

                .waitSeconds(3)

                .build();

        TrajectorySequence grabSecond = drive.trajectorySequenceBuilder(depositFirst.end())

                .splineToLinearHeading(new Pose2d(35, -58, Math.toRadians(90)), Math.toRadians(-90))
                .lineTo((new Vector2d(35, -60)))

                .waitSeconds(3)

                .build();

        TrajectorySequence depositSecond = drive.trajectorySequenceBuilder(grabSecond.end())

                .splineToLinearHeading(new Pose2d(4, -30, Math.toRadians(270)), Math.toRadians(180))
                .lineTo((new Vector2d(4, -35)))

                .waitSeconds(3)
                .build();

        TrajectorySequence grabThird = drive.trajectorySequenceBuilder(depositSecond.end())

                .splineToLinearHeading(new Pose2d(35, -58, Math.toRadians(90)), Math.toRadians(-90))
                .lineTo((new Vector2d(35, -60)))

                .waitSeconds(3)
                .build();

        TrajectorySequence depositThird = drive.trajectorySequenceBuilder(grabThird.end())

                .splineToLinearHeading(new Pose2d(1, -30, Math.toRadians(270)), Math.toRadians(180))
                .lineTo((new Vector2d(1, -35)))

                .waitSeconds(3)
                .build();

        TrajectorySequence grabFourth = drive.trajectorySequenceBuilder(depositThird.end())

                .splineToLinearHeading(new Pose2d(35, -58, Math.toRadians(90)), Math.toRadians(-90))
                .lineTo((new Vector2d(35, -60)))

                .waitSeconds(3)
                .build();

        TrajectorySequence depositFourth = drive.trajectorySequenceBuilder(grabFourth.end())

                .splineToLinearHeading(new Pose2d(-2, -30, Math.toRadians(270)), Math.toRadians(180))
                .lineTo((new Vector2d(-2, -35)))

                .waitSeconds(3)
                .build();


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
                case grabThird:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(grabThird);
                        nextTraj(Constants.AutoState.depositThird);
                    }
                    break;
                case depositThird:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(depositThird);
                        nextTraj(Constants.AutoState.grabFourth);
                    }
                    break;
                case grabFourth:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(grabFourth);
                        nextTraj(Constants.AutoState.depositFourth);
                    }
                    break;
                case depositFourth:
                    if(!drive.isBusy()){
                        drive.followTrajectorySequence(depositFourth);
                        nextTraj(Constants.AutoState.idle);
                    }
                    break;
                case idle:
                    break;
            }
        }
    }
}
