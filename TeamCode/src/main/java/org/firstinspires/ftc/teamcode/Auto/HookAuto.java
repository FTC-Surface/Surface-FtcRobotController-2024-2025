//package org.firstinspires.ftc.teamcode.Auto;
//
//import com.pedropathing.follower.Follower;
//import com.pedropathing.localization.Pose;
//import com.pedropathing.pathgen.BezierCurve;
//import com.pedropathing.pathgen.BezierLine;
//import com.pedropathing.pathgen.Path;
//import com.pedropathing.pathgen.PathChain;
//import com.pedropathing.pathgen.Point;
//import com.pedropathing.util.Constants;
//import com.pedropathing.util.Timer;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//
//import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
//import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;
//
///**
// * This is an example auto that showcases movement and control of two servos autonomously.
// * It is a 0+4 (Specimen + Sample) bucket auto. It scores a neutral preload and then pickups 3 samples from the ground and scores them before parking.
// * There are examples of different ways to build paths.
// * A path progression method has been created and can advance based on time, position, or other factors.
// *
// * @author Baron Henderson - 20077 The Indubitables
// * @version 2.0, 11/28/2024
// */
//public class GeneratedPath {
//
//    public GeneratedPath() {
//        PathBuilder builder = new PathBuilder();
//
//        builder
//                .addPath(
//                        // Line 1
//                        new BezierLine(
//                                new Point(0, 72, Point.CARTESIAN),
//                                new Point(33, 72, Point.CARTESIAN)
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .addPath(
//                        // Line 2
//                        new BezierLine(
//                                new Point(36.610, 72.282, Point.CARTESIAN),
//                                new Point(23.468, 72.469, Point.CARTESIAN)
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .addPath(
//                        // Line 3
//                        new BezierCurve(
//                                new Point(23.468, 72.469, Point.CARTESIAN),
//                                new Point(20.464, 42.618, Point.CARTESIAN),
//                                new Point(46.561, 42.618, Point.CARTESIAN)
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .addPath(
//                        // Line 4
//                        new BezierCurve(
//                                new Point(46.561, 42.618, Point.CARTESIAN),
//                                new Point(63.833, 29.288, Point.CARTESIAN),
//                                new Point(11.077, 31.541, Point.CARTESIAN)
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .addPath(
//                        // Line 5
//                        new BezierCurve(
//                                new Point(11.077, 31.541, Point.CARTESIAN),
//                                new Point(63.833, 37.924, Point.CARTESIAN),
//                                new Point(50.691, 20.276, Point.CARTESIAN)
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .addPath(
//                        // Line 6
//                        new BezierLine(
//                                new Point(50.691, 20.276, Point.CARTESIAN),
//                                new Point(10.889, 20.089, Point.CARTESIAN)
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .addPath(
//                        // Line 7
//                        new BezierCurve(
//                                new Point(10.889, 20.089, Point.CARTESIAN),
//                                new Point(68.527, 27.786, Point.CARTESIAN),
//                                new Point(50.316, 12.016, Point.CARTESIAN)
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .addPath(
//                        // Line 8
//                        new BezierCurve(
//                                new Point(50.316, 12.016, Point.CARTESIAN),
//                                new Point(5.820, 9.387, Point.CARTESIAN),
//                                new Point(0.188, 17.460, Point.CARTESIAN)
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .addPath(
//                        // Line 9
//                        new BezierCurve(
//                                new Point(0.188, 17.460, Point.CARTESIAN),
//                                new Point(4.506, 77.726, Point.CARTESIAN),
//                                new Point(37.361, 74.347, Point.CARTESIAN)
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .addPath(
//                        // Line 10
//                        new BezierCurve(
//                                new Point(37.361, 74.347, Point.CARTESIAN),
//                                new Point(0.563, 79.604, Point.CARTESIAN),
//                                new Point(36.610, 44.683, Point.CARTESIAN),
//                                new Point(-0.188, 44.683, Point.CARTESIAN)
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .addPath(
//                        // Line 11
//                        new BezierCurve(
//                                new Point(-0.188, 44.683, Point.CARTESIAN),
//                                new Point(27.411, 45.059, Point.CARTESIAN),
//                                new Point(2.816, 78.665, Point.CARTESIAN),
//                                new Point(37.549, 76.224, Point.CARTESIAN)
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0));
//    }
//}
//
//
//@Autonomous(name = "Hook Auto", group = "Auto")
//public class HookAuto extends OpMode {
//
//    private Follower follower;
//    private Timer pathTimer, actionTimer, opmodeTimer;
//
//    /** This is the variable where we store the state of our auto.
//     * It is used by the pathUpdate method. */
//    private int pathState;
//
//    /* Create and Define Poses + Paths
//     * Poses are built with three constructors: x, y, and heading (in Radians).
//     * Pedro uses 0 - 144 for x and y, with 0, 0 being on the bottom left.
//     * (For Into the Deep, this would be Blue Observation Zone (0,0) to Red Observation Zone (144,144).)
//     * Even though Pedro uses a different coordinate system than RR, you can convert any roadrunner pose by adding +72 both the x and y.
//     * This visualizer is very easy to use to find and create paths/pathchains/poses: <https://pedro-path-generator.vercel.app/>
//     * Lets assume our robot is 18 by 18 inches
//     * Lets assume the Robot is facing the human player and we want to score in the bucket */
//
//    /** Start Pose of our robot */
//    private final Pose startPose = new Pose(0, 72, Math.toRadians(0));
//
//    private final Pose scorePose = new Pose(33, 77, Math.toRadians(0));
//
//    private final Pose pickupPose = new Pose(0, 121, Math.toRadians(0));
//
//    private final Pose push1Pose = new Pose(43, 130, Math.toRadians(0));
//    private final Pose push2Pose = new Pose(49, 135, Math.toRadians(0));
//    private final Pose push3Pose = new Pose(49, 135, Math.toRadians(0));
//
//
//    /* These are our Paths and PathChains that we will define in buildPaths() */
//    private PathChain scorePreload, Push1, Push2, Push3, Score2, Score3, Score4, Score5;
//
//    /** Build the paths for the auto (adds, for example, constant/linear headings while doing paths)
//     * It is necessary to do this so that all the paths are built before the auto starts. **/
//    public void buildPaths() {
//
//        /* There are two major types of paths components: BezierCurves and BezierLines.
//         *    * BezierCurves are curved, and require >= 3 points. There are the start and end points, and the control points.
//         *    - Control points manipulate the curve between the start and end points.
//         *    - A good visualizer for this is [this](https://pedro-path-generator.vercel.app/).
//         *    * BezierLines are straight, and require 2 points. There are the start and end points.
//         * Paths have can have heading interpolation: Constant, Linear, or Tangential
//         *    * Linear heading interpolation:
//         *    - Pedro will slowly change the heading of the robot from the startHeading to the endHeading over the course of the entire path.
//         *    * Constant Heading Interpolation:
//         *    - Pedro will maintain one heading throughout the entire path.
//         *    * Tangential Heading Interpolation:
//         *    - Pedro will follows the angle of the path such that the robot is always driving forward when it follows the path.
//         * PathChains hold Path(s) within it and are able to hold their end point, meaning that they will holdPoint until another path is followed.
//         * Here is a explanation of the difference between Paths and PathChains <https://pedropathing.com/commonissues/pathtopathchain.html> */
//
//        /* This is our scorePreload path. We are using a BezierLine, which is a straight line. */
//        scorePreload = follower.pathBuilder()
//                .addPath(new BezierLine(
//                    new Point(0, 72, Point.CARTESIAN),
//                    new Point(36, 72, Point.CARTESIAN)
//                ))
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .build();
//
//        /* Here is an example for Constant Interpolation
//        scorePreload.setConstantInterpolation(startPose.getHeading()); */
//
//        /* This is our grabPickup1 PathChain. We are using a single path with a BezierLine, which is a straight line. */
//        Push1 = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(scorePose), new Point(pickup1Pose)))
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .build();
//
//        /* This is our scorePickup1 PathChain. We are using a single path with a BezierLine, which is a straight line. */
//        Push2 = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(pickup1Pose), new Point(scorePose)))
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .build();
//
//        /* This is our grabPickup2 PathChain. We are using a single path with a BezierLine, which is a straight line. */
//        Push3 = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(scorePose), new Point(pickup2Pose)))
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .build();
//
//        /* This is our scorePickup2 PathChain. We are using a single path with a BezierLine, which is a straight line. */
//        Score2 = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(pickup2Pose), new Point(scorePose)))
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .build();
//
//        /* This is our grabPickup3 PathChain. We are using a single path with a BezierLine, which is a straight line. */
//        Score3 = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(scorePose), new Point(pickup3Pose)))
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .build();
//
//        /* This is our scorePickup3 PathChain. We are using a single path with a BezierLine, which is a straight line. */
//        Score4 = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(pickup3Pose), new Point(scorePose)))
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .build();
//
//        /* This is our park path. We are using a BezierCurve with 3 points, which is a curved line that is curved based off of the control point */
//        park = new Path(new BezierCurve(new Point(scorePose), /* Control Point */ new Point(parkControlPose), new Point(parkPose)));
//        park.setLinearHeadingInterpolation(scorePose.getHeading(), parkPose.getHeading());
//    }
//
//    /** This switch is called continuously and runs the pathing, at certain points, it triggers the action state.
//     * Everytime the switch changes case, it will reset the timer. (This is because of the setPathState() method)
//     * The followPath() function sets the follower to run the specific path, but does NOT wait for it to finish before moving on. */
//    public void autonomousPathUpdate() {
//        switch (pathState) {
//            case 0:
//                follower.followPath(scorePreload);
//                setPathState(1);
//                break;
//            case 1:
//
//                /* You could check for
//                - Follower State: "if(!follower.isBusy() {}"
//                - Time: "if(pathTimer.getElapsedTimeSeconds() > 1) {}"
//                - Robot Position: "if(follower.getPose().getX() > 36) {}"
//                */
//
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
//                if(!follower.isBusy()) {
//                    /* Score Preload */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.followPath(scorePreload,true);
//                    setPathState(2);
//                }
//                break;
//            case 2:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup1Pose's position */
//                if(!follower.isBusy()) {
//                    /* Grab Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
//                    follower.followPath(Push1,true);
//                    setPathState(3);
//                }
//                break;
//            case 3:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
//                if(!follower.isBusy()) {
//                    /* Score Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.followPath(Push2,true);
//                    setPathState(4);
//                }
//                break;
//            case 4:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup2Pose's position */
//                if(!follower.isBusy()) {
//                    /* Grab Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
//                    follower.followPath(Push3),true);
//                    setPathState(5);
//                }
//                break;
//            case 5:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
//                if(!follower.isBusy()) {
//                    /* Score Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.followPath(grabPickup3,true);
//                    setPathState(6);
//                }
//                break;
//            case 6:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
//                if(!follower.isBusy()) {
//                    /* Grab Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
//                    follower.followPath(scorePickup3, true);
//                    setPathState(7);
//                }
//                break;
//            case 7:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
//                if(!follower.isBusy()) {
//                    /* Score Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are parked */
//                    follower.followPath(park,true);
//                    setPathState(8);
//                }
//                break;
//            case 8:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
//                if(!follower.isBusy()) {
//                    /* Level 1 Ascent */
//
//                    /* Set the state to a Case we won't use or define, so it just stops running an new paths */
//                    setPathState(-1);
//                }
//                break;
//        }
//    }
//
//    /** These change the states of the paths and actions
//     * It will also reset the timers of the individual switches **/
//    public void setPathState(int pState) {
//        pathState = pState;
//        pathTimer.resetTimer();
//    }
//
//    /** This is the main loop of the OpMode, it will run repeatedly after clicking "Play". **/
//    @Override
//    public void loop() {
//
//        // These loop the movements of the robot
//        follower.update();
//        autonomousPathUpdate();
//
//        // Feedback to Driver Hub
//        telemetry.addData("path state", pathState);
//        telemetry.addData("x", follower.getPose().getX());
//        telemetry.addData("y", follower.getPose().getY());
//        telemetry.addData("heading", follower.getPose().getHeading());
//        telemetry.update();
//    }
//
//    /** This method is called once at the init of the OpMode. **/
//    @Override
//    public void init() {
//        pathTimer = new Timer();
//        opmodeTimer = new Timer();
//        opmodeTimer.resetTimer();
//
//        Constants.setConstants(FConstants.class, LConstants.class);
//        follower = new Follower(hardwareMap);
//        follower.setStartingPose(startPose);
//        buildPaths();
//    }
//
//    /** This method is called continuously after Init while waiting for "play". **/
//    @Override
//    public void init_loop() {}
//
//    /** This method is called once at the start of the OpMode.
//     * It runs all the setup actions, including building paths and starting the path system **/
//    @Override
//    public void start() {
//        opmodeTimer.resetTimer();
//        setPathState(0);
//    }
//
//    /** We do not use this because everything should automatically disable **/
//    @Override
//    public void stop() {
//    }
//}
//
