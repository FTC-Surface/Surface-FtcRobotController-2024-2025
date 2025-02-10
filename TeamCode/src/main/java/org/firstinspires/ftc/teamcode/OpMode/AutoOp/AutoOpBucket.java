package org.firstinspires.ftc.teamcode.OpMode.AutoOp;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Newroadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.Robot.RobotAuto;

@Config
@Autonomous(name = "Active Intake Hook", group = "Autonomous")
public class AutoOpBucket extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d initPos = new Pose2d(9,-60.5, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initPos);
        RobotAuto robot = new RobotAuto(hardwareMap);

//***** Trajectories ************************************************************************

        TrajectoryActionBuilder depositInit = drive.actionBuilder(initPos)
                .strafeToLinearHeading(new Vector2d(-55,-53), Math.toRadians(45))
                .waitSeconds(2);

        TrajectoryActionBuilder grabFirst = depositInit.endTrajectory().fresh()
                .strafeToLinearHeading(new Vector2d(-48.25,-45), Math.toRadians(90))
                .waitSeconds(1);

        TrajectoryActionBuilder depositFirst = grabFirst.endTrajectory().fresh()
                .strafeToLinearHeading(new Vector2d(-55,-53), Math.toRadians(45))
                .waitSeconds(2);

        TrajectoryActionBuilder grabSecond = depositFirst.endTrajectory().fresh()
                .strafeToLinearHeading(new Vector2d(-58.25,-45), Math.toRadians(90))
                .waitSeconds(1);

        TrajectoryActionBuilder depositSecond = grabSecond.endTrajectory()
                .strafeToLinearHeading(new Vector2d(-55,-53), Math.toRadians(45))
                .waitSeconds(2);

        TrajectoryActionBuilder grabThird = depositSecond.endTrajectory().fresh()
                .strafeToLinearHeading(new Vector2d(-60,-53), Math.toRadians(100))
                .waitSeconds(1);

        TrajectoryActionBuilder depositThird = grabThird.endTrajectory().fresh()
                .strafeToLinearHeading(new Vector2d(-55,-53), Math.toRadians(45))
                .waitSeconds(2);

        TrajectoryActionBuilder park = depositThird.endTrajectory().fresh()
                .splineToLinearHeading(new Pose2d(-25, 0, Math.toRadians(0)), Math.toRadians(0))
                .waitSeconds(2);

//***** Actions ************************************************************************

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){

            Action depositInitAction, grabFirstAction, depositFirstAction, grabSecondAction, depositSecondAction, grabThirdAction, depositThirdAction, parkAction;

            depositInitAction = depositInit.build();
            grabFirstAction = grabFirst.build();
            depositFirstAction = depositFirst.build();
            grabSecondAction = grabSecond.build();
            depositSecondAction = depositSecond.build();
            grabThirdAction = grabThird.build();
            depositThirdAction = depositThird.build();
            parkAction = park.build();

            Actions.runBlocking(
                    new SequentialAction(
                            depositInitAction,
                            grabFirstAction,
                            depositFirstAction,
                            grabSecondAction,
                            depositSecondAction,
                            grabThirdAction,
                            depositThirdAction,
                            parkAction
                    )
            );
        }
    }

    public void waitForSeconds(double seconds) {
        ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        timer.reset();

        while (timer.time() < seconds) {
        }
    }
}
