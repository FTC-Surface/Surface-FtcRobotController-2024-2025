package org.firstinspires.ftc.teamcode.ArchivedCode;

public class CommentedBucketAutoOp {
//    TrajectorySequence depositInit = drive.trajectorySequenceBuilder(startPose)
//                .addTemporalMarker(0, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Ready);
//                    //robot.iOpenClaw();
//                    robot.oCloseClaw();
//                    robot.oArmStart();
//                })
//
//                .lineToLinearHeading(new Pose2d(-52, -47, Math.toRadians(45)))
//                .waitSeconds(0.25)
//                .lineToLinearHeading(new Pose2d(-58, -53, Math.toRadians(45)))
//
//               .addTemporalMarker(1, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Basket);
//                    robot.oArmDumpReady();
//
//                })
//
//                .waitSeconds(0.75)
//
//                .addTemporalMarker(2.75, () -> {
//                    robot.oOpenClaw();
//                })
//
//                .lineToLinearHeading(new Pose2d(-52, -47, Math.toRadians(45)))
//
//                .build();
//
//        TrajectorySequence grabFirst = drive.trajectorySequenceBuilder(depositInit.end())
//                .lineToLinearHeading(new Pose2d(-48.25,-35.5, Math.toRadians(90)))
//
//                .addTemporalMarker(0, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Ready);
//                    robot.oArmTake();
//                })
//                .addTemporalMarker(2, () -> {
//                    robot.iArmGrab();
//
//                })
//                .addTemporalMarker(2.5, () -> {
//                    //robot.iCloseClaw();
//                })
//
//                .addTemporalMarker(3, () -> {
//                    robot.iArmStart();
//
//                })
//                .addTemporalMarker(3.5, () -> {
//                    //robot.iOpenClaw();
//                    robot.oOpenClaw();
//
//                })
//                .addTemporalMarker(3.8, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Grab);
//                })
//
//                .addTemporalMarker(4.3, () -> {
//                    robot.oCloseClaw();
//                })
//
//                .waitSeconds(4.5)
//
//                .build();
//
//        TrajectorySequence depositFirst = drive.trajectorySequenceBuilder(grabFirst.end())
//                .lineToLinearHeading(new Pose2d(-52, -47, Math.toRadians(45)))
//
//
//                .addTemporalMarker(0, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Basket);
//
//                })
//                .addTemporalMarker(0.75, () -> {
//                    robot.oArmDumpReady();
//
//                })
//                .waitSeconds(0.5)
//                .lineToLinearHeading(new Pose2d(-58, -53, Math.toRadians(45)))
//                .addTemporalMarker(3, () -> {
//                    robot.oOpenClaw();
//
//                })
//
//                .waitSeconds(0.5)
//                .lineToLinearHeading(new Pose2d(-52, -47, Math.toRadians(45)))
//
//                .waitSeconds(0.5)
//
//                .build();
//
////        TrajectorySequence grabSecond = drive.trajectorySequenceBuilder(depositFirst.end())
////                .lineToLinearHeading(new Pose2d(-63,-35, Math.toRadians(90)))
////                .addTemporalMarker(0, () -> {
////                    robot.oElevMove(Constants.eOElevatorState.Ready);
////                    robot.oArmTake();
////                })
////                .addTemporalMarker(2, () -> {
////                    robot.iArmGrab();
////
////                })
////                .addTemporalMarker(2.5, () -> {
////                    robot.iCloseClaw();
////
////                })
////                .addTemporalMarker(3, () -> {
////                    robot.iArmStart();
////
////                })
////                .addTemporalMarker(3.5, () -> {
////                    robot.iOpenClaw();
////                    robot.oOpenClaw();
////
////                })
////                .addTemporalMarker(3.8, () -> {
////                    robot.iArmHover();
////                    robot.oElevMove(Constants.eOElevatorState.Grab);
////
////                })
////                .addTemporalMarker(4.3, () -> {
////                    robot.oCloseClaw();
////                })
////                .waitSeconds(4.5)
////                .build();
////
////        TrajectorySequence depositSecond = drive.trajectorySequenceBuilder(grabSecond.end())
////                .lineToLinearHeading(new Pose2d(-52, -47, Math.toRadians(45)))
////
////
////                .addTemporalMarker(0, () -> {
////                    robot.oElevMove(Constants.eOElevatorState.Basket);
////
////                })
////                .addTemporalMarker(0.75, () -> {
////                    robot.oArmDump();
////
////                })
////                .waitSeconds(0.5)
////                .lineToLinearHeading(new Pose2d(-58, -53, Math.toRadians(45)))
////                .addTemporalMarker(3, () -> {
////                    robot.oOpenClaw();
////
////                })
////
////                .waitSeconds(0.5)
////                .lineToLinearHeading(new Pose2d(-52, -47, Math.toRadians(45)))
////
////                .waitSeconds(0.5)
////
////                .build();
//
////        TrajectorySequence grabThird = drive.trajectorySequenceBuilder(depositSecond.end())
////                .lineToLinearHeading(new Pose2d(-62,-33, Math.toRadians(120)))
//////                .waitSeconds(2)
//////                .lineToLinearHeading(new Pose2d(-53, -52, Math.toRadians(45)))
////                .waitSeconds(5)
////                .build();
////
////        TrajectorySequence depositThird = drive.trajectorySequenceBuilder(grabThird.end())
////                .lineToLinearHeading(new Pose2d(-54, -51, Math.toRadians(45)))
////
////
////                .waitSeconds(2)
////
////                .addTemporalMarker(2, () -> {
////                    robot.oOpenClaw();
////
////                })
////
////                .waitSeconds(0.5)
////
////                .addTemporalMarker(2.5, () -> {
////                    robot.oElevMove(Constants.eOElevatorState.Ready);
////                })
//                //.splineToLinearHeading(new Pose2d(-53, -52, Math.toRadians(45)),Math.toRadians(180))
////                .waitSeconds(5)
////                .build();
//
//        TrajectorySequence reset = drive.trajectorySequenceBuilder(depositFirst.end())
//
//                .addTemporalMarker(0.25, () -> {
//                    robot.oArmStart();
//                })
//                .addTemporalMarker(0.75, () -> {
//                    robot.oElevMove(Constants.eOElevatorState.Ground);
//                })
//
//                .waitSeconds(3)
//
//                .build();
}
