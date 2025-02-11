package org.firstinspires.ftc.teamcode.ArchivedCode.TeleOpArchived;

public class CommentedHookAutoOp {
//    TrajectorySequence depositInit = drive.trajectorySequenceBuilder(startPose)
//                .addTemporalMarker(0, () -> {
//                    robot.oCloseClaw();
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Ready);
//                    robot.oArmHookup();
////                    robot.iArmStart();
////                    robot.iOpenClaw();
//                })
//                .waitSeconds(0.65)
//
//                //Add code for the first sample
//
//                .lineTo(new Vector2d(9,-34))
//
////                .addTemporalMarker(2.4, () -> {
////                    robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
////                })
////                .addTemporalMarker(3.4, () -> {
////                    robot.oOpenClaw();
////                    robot.oArmHookgrab();
////                })
//
//                .waitSeconds(5)
//
//                .build();
//
//        TrajectorySequence readySample1 = drive.trajectorySequenceBuilder(depositInit.end())
//                .addTemporalMarker(0, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Grab);
//                })
//
////                .lineToLinearHeading(new Pose2d(35, -44, Math.toRadians(270)))
////                .lineToLinearHeading(new Pose2d(35, -7, Math.toRadians(270)))
////
////                .splineToLinearHeading(new Pose2d(45, -13, Math.toRadians(180)), Math.toRadians(-90))
////                .waitSeconds(0.5)
////
////                .lineTo((new Vector2d(45, -53)))
////                .splineToLinearHeading(new Pose2d(55, -13, Math.toRadians(180)), Math.toRadians(0))
////
////                .waitSeconds(0.5)
////
////                .lineTo((new Vector2d(55, -53)))
////                .waitSeconds(0.5)
//
//                .splineToLinearHeading(new Pose2d(42, -58, Math.toRadians(90)), Math.toRadians(-90))
//
//                .waitSeconds(0.125)
//
//                .build();
//
//        TrajectorySequence grabFirst = drive.trajectorySequenceBuilder(readySample1.end())
//
//                .lineTo((new Vector2d(42, -63)))
//                .addTemporalMarker(0.35, () -> {
//                    robot.oCloseClaw();
//                })
//
//                .waitSeconds(0.5)
//
//                .build();
//
//
//
//        TrajectorySequence depositFirst = drive.trajectorySequenceBuilder(grabFirst.end())
//
//                .addTemporalMarker(0.25, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Ready);
//                    robot.oArmHookup();
//                })
//                .waitSeconds(0.15)
//                .splineToLinearHeading(new Pose2d(5, -42, Math.toRadians(90)), Math.toRadians(90))
//                .lineTo(new Vector2d(5,-35))
//                .addDisplacementMarker(() ->{
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
//                })
//                .addDisplacementMarker(() ->{
//                    robot.oOpenClaw();
//                    robot.oArmHookgrab();
//                })
//                .waitSeconds(0.5)
//
//                .build();
//
//
//        TrajectorySequence grabSecond = drive.trajectorySequenceBuilder(depositFirst.end())
//
//                .addTemporalMarker(0, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Grab);
//
//                })
//
//                .splineToLinearHeading(new Pose2d(42, -57, Math.toRadians(90)), Math.toRadians(-90))
//
//                .waitSeconds(0.125)
//                .lineTo((new Vector2d(42, -63)))
//                .addTemporalMarker(4.3, () -> {
//                    robot.oCloseClaw();
//                })
//                .waitSeconds(0.25)
//
//                .build();
//
//        TrajectorySequence depositSecond = drive.trajectorySequenceBuilder(grabSecond.end())
//
//                .addTemporalMarker(0.25, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Ready);
//                })
//
//                .splineToLinearHeading(new Pose2d(3, -40, Math.toRadians(90)), Math.toRadians(90))
//                .lineTo(new Vector2d(3,-31))
//                .addDisplacementMarker(() ->{
//                    robot.oArmHookup();
//                })
//                .lineTo((new Vector2d(3, -45)))
//                .addDisplacementMarker(() ->{
//                    robot.oOpenClaw();
//                    robot.oArmHookgrab();
//                })
//                .waitSeconds(0.5)
//                .build();
//        TrajectorySequence grabThird = drive.trajectorySequenceBuilder(depositSecond.end())
//
//                .addTemporalMarker(0, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Grab);
//                })
//                .splineToLinearHeading(new Pose2d(42, -57, Math.toRadians(90)), Math.toRadians(-90))
//
//                .waitSeconds(0.125)
//                .lineTo((new Vector2d(42, -63)))
//                .addTemporalMarker(4.3, () -> {
//                    robot.oCloseClaw();
//                })
//                .waitSeconds(0.25)
//
//                .build();
//
//        TrajectorySequence depositThird = drive.trajectorySequenceBuilder(grabThird.end())
//
//                .addTemporalMarker(0.25, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Ready);
//                })
//
//                .splineToLinearHeading(new Pose2d(0, -40, Math.toRadians(90)), Math.toRadians(90))
//                .lineTo(new Vector2d(0,-31))
//                .addDisplacementMarker(() ->{
//                    robot.oArmHookup();
//                })
//                .lineTo((new Vector2d(0, -45)))
//                .addDisplacementMarker(() ->{
//                    robot.oOpenClaw();
//                    robot.oArmHookgrab();
//                })
//                .waitSeconds(0.5)
//                .build();
//        TrajectorySequence grabFourth = drive.trajectorySequenceBuilder(depositThird.end())
//
//                .addTemporalMarker(0, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Grab);
//
//                })
//
//                .splineToLinearHeading(new Pose2d(42, -57, Math.toRadians(90)), Math.toRadians(-90))
//
//                .waitSeconds(0.125)
//                .lineTo((new Vector2d(42, -63)))
//                .addTemporalMarker(4.3, () -> {
//                    robot.oCloseClaw();
//                })
//                .waitSeconds(0.25)
//
//                .build();
//
//        TrajectorySequence depositFourth = drive.trajectorySequenceBuilder(grabFourth.end())
//
//                .addTemporalMarker(0.25, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Clip_Ready);
//                })
//
//                .splineToLinearHeading(new Pose2d(-3, -40, Math.toRadians(90)), Math.toRadians(90))
//                .lineTo(new Vector2d(-3,-31))
//                .addDisplacementMarker(() ->{
//                    robot.oArmHookup();
//                })
//                .lineTo((new Vector2d(-3, -45)))
//                .addDisplacementMarker(() ->{
//                    robot.oOpenClaw();
//                    robot.oArmHookgrab();
//                })
//                .waitSeconds(0.5)
//                .build();
//        TrajectorySequence reset = drive.trajectorySequenceBuilder(depositFourth.end())
//
//                .addTemporalMarker(0.25, () -> {
//                    robot.oArmStart();
//                })
//                .addTemporalMarker(0.75, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Ground);
//                })
//                .waitSeconds(3)
//
//                .build();
}
