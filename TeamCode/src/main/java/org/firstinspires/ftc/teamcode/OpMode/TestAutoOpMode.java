package org.firstinspires.ftc.teamcode.OpMode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.Constants;

@Autonomous(name = "Test", group = "TestOpModes")
@Config
public class TestAutoOpMode extends LinearOpMode {

    Constants.AutoState currentTraj = Constants.AutoState.idle;
    SampleMecanumDrive drive;

    Pose2d startPose = new Pose2d(7.5,-58, 0);

    void nextTraj(Constants.AutoState state){
        currentTraj = state;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(startPose);

        currentTraj = Constants.AutoState.park;

        waitForStart();

        Trajectory park = drive.trajectoryBuilder(startPose)
                .lineToLinearHeading(new Pose2d(60, -58, 0))
                .build();

        while(opModeIsActive()){
            switch(currentTraj){
                case park:
                    if(!drive.isBusy()){
                        drive.followTrajectory(park);
                        nextTraj(Constants.AutoState.idle);
                    }
                    break;
                case idle:
                    break;
            }
        }
    }
}
