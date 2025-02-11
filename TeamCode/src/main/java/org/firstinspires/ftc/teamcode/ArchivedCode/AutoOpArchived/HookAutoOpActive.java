//package org.firstinspires.ftc.teamcode.OpMode.AutoOp;
//import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.geometry.Vector2d;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;
//import org.firstinspires.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;
//import org.firstinspires.ftc.teamcode.Robot.RobotAuto;
//import org.firstinspires.ftc.teamcode.Subsystems.Constants;
//@Autonomous(name = "Hook Auto Mode Active Intake 1", group = "Auto Test")
//@Config
//public class HookAutoOpModeActiveIntake extends LinearOpMode {
//    SampleMecanumDrive drive;
//    Constants.AutoState currentTraj = Constants.AutoState.idle;
//    Pose2d startPose = new Pose2d(9,-60.5, Math.toRadians(90));
//    Pose2d afterdepositInit = new Pose2d(9,-26, Math.toRadians(90));
//    void nextTraj(Constants.AutoState state){
//        currentTraj = state;
//    }
//    @Override
//    public void runOpMode() throws InterruptedException {
//        RobotAuto robot = new RobotAuto(hardwareMap);
//        drive = new SampleMecanumDrive(hardwareMap);
//        drive.setPoseEstimate(startPose);
//        currentTraj = Constants.AutoState.depositInit;
//        waitForStart();
//        robot.oCloseClaw();
//        robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
//        robot.oArmHookup();
//        TrajectorySequence depositInit = drive.trajectorySequenceBuilder(startPose)
//                .addTemporalMarker(0, () -> {
//                    //robot.iArmStart();
//                })
//                .lineTo(new Vector2d(9,-27))
//                .addTemporalMarker(1.8, () -> {
//                    robot.oOpenClaw();
//                })
//                .waitSeconds(2)
//                .build();
//        TrajectorySequence grabFirst = drive.trajectorySequenceBuilder(depositInit.end())
//                .lineTo(new Vector2d(9, -40))
//                .addTemporalMarker(1, () -> {
//                    robot.oArmHookgrab();
//                    robot.oElevMove(Constants.eOElevatorState.Ground);
//                })
//                .waitSeconds(1)
//                .lineToLinearHeading(new Pose2d(40, -45, Math.toRadians(75)))
//                .turn(Math.toRadians(-105))
//                .waitSeconds(0.5)
//                .build();
//        TrajectorySequence grabSecond = drive.trajectorySequenceBuilder(grabFirst.end())
//                .lineToLinearHeading(new Pose2d(45, -45, Math.toRadians(60)))
//                .turn(Math.toRadians(-90))
//                .waitSeconds(0.5)
//                .build();
//        TrajectorySequence grabThird = drive.trajectorySequenceBuilder(grabSecond.end())
//                .lineToLinearHeading(new Pose2d(50, -45, Math.toRadians(50)))
//                .turn(Math.toRadians(-80))
//                .waitSeconds(0.5)
//                .build();
//        TrajectorySequence depositFirst = drive.trajectorySequenceBuilder(grabThird.end())
//                .lineToLinearHeading(new Pose2d(40, -55, Math.toRadians(90)))
//                .waitSeconds(1)
//
//                .lineTo(new Vector2d(40,-60.5))
////              .addTemporalMarker(1.7, () ->{
////                    robot.oOpenClaw
////                })
////              .addTemporalMarker(2, () ->{
////                    robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
////                    robot.oArmHookup();
////                })
//                .waitSeconds(0.1)
//                .splineToLinearHeading(new Pose2d(6,-26, Math.toRadians(90)), Math.toRadians(90))
//                .build();
//        TrajectorySequence depositSecond = drive.trajectorySequenceBuilder(depositFirst.end())
//                .lineTo(new Vector2d(40, -58))
//                .waitSeconds(1)
//                .lineTo(new Vector2d(40,-60.5))
//                .waitSeconds(0.1)
//                .splineToLinearHeading(new Pose2d(3,-26, Math.toRadians(90)), Math.toRadians(90))
//                .build();
//        TrajectorySequence depositThird = drive.trajectorySequenceBuilder(depositSecond.end())
//                .lineTo(new Vector2d(40, -58))
//                .waitSeconds(1)
//                .lineTo(new Vector2d(40,-60.5))
//                .waitSeconds(0.1)
//                .splineToLinearHeading(new Pose2d(0,-26, Math.toRadians(90)), Math.toRadians(90))
//                .build();
//        TrajectorySequence depositFourth = drive.trajectorySequenceBuilder(depositThird.end())
//                .lineTo(new Vector2d(40, -58))
//                .waitSeconds(1)
//                .lineTo(new Vector2d(40,-60.5))
//                .waitSeconds(0.1)
//                .splineToLinearHeading(new Pose2d(-3,-26, Math.toRadians(90)), Math.toRadians(90))
//                .build();
//        TrajectorySequence park = drive.trajectorySequenceBuilder(depositFourth.end())
//                .lineTo(new Vector2d(40, -55))
//                .build();
//        while(opModeIsActive()){
//            switch(currentTraj){
//                case depositInit:
//                    if(!drive.isBusy()){
//                        drive.followTrajectorySequence(depositInit);
//                        nextTraj(Constants.AutoState.grabFirst);
//                    }
//                    break;
//                case grabFirst:
//                    if(!drive.isBusy()){
//                        drive.setPoseEstimate(afterdepositInit);
//                        drive.followTrajectorySequence(grabFirst);
//                        nextTraj(Constants.AutoState.grabSecond);
//                    }
//                    break;
//                case grabSecond:
//                    if(!drive.isBusy()){
//                        drive.followTrajectorySequence(grabSecond);
//                        nextTraj(Constants.AutoState.grabThird);
//                    }
//                    break;
//                case grabThird:
//                    if(!drive.isBusy()){
//                        drive.followTrajectorySequence(grabThird);
//                        nextTraj(Constants.AutoState.depositFirst);
//                    }
//                    break;
//                case depositFirst:
//                    if(!drive.isBusy()){
//                        drive.followTrajectorySequence(depositFirst);
//                        nextTraj(Constants.AutoState.depositSecond);
//                    }
//                    break;
//                case depositSecond:
//                    if(!drive.isBusy()){
//                        drive.followTrajectorySequence(depositFourth);
//                        nextTraj(Constants.AutoState.depositThird);
//                    }
//                    break;
//                case depositThird:
//                    if(!drive.isBusy()){
//                        drive.followTrajectorySequence(depositThird);
//                        nextTraj(Constants.AutoState.depositFourth);
//                    }
//                    break;
//                case depositFourth:
//                    if(!drive.isBusy()){
//                        drive.followTrajectorySequence(depositFourth);
//                        nextTraj(Constants.AutoState.park);
//                    }
//                    break;
//                case park:
//                    if(!drive.isBusy()){
//                        drive.followTrajectorySequence(park);
//                        nextTraj(Constants.AutoState.idle);
//                    }
//                case idle:
//                    break;
//            }
//        }
//    }
//}
//‎TeamCode/src/main/java/org/firstinspires/ftc/teamcode/OpMode