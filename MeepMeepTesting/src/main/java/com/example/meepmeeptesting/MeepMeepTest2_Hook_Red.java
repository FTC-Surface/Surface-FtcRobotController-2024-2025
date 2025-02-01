package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTest2_Hook_Red {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(470), Math.toRadians(180), 15.25)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(9,-60.5, Math.toRadians(90)))

                        //push
//                        .lineTo((new Vector2d(9, -34)))
//
//                        .lineToLinearHeading(new Pose2d(38, -35, Math.toRadians(180)))
//                        .lineToLinearHeading(new Pose2d(38, -7, Math.toRadians(180)))
//
//                        .lineTo(new Vector2d(52, -7))
//
//                        .lineTo(new Vector2d(52, -56))
//
//                        .splineToLinearHeading(new Pose2d(61, -7, Math.toRadians(180)), Math.toRadians(0))
//                        .lineTo(new Vector2d(61, -56))
//
//                        .splineToLinearHeading(new Pose2d(42, -55, Math.toRadians(90)), Math.toRadians(-90))
//
//                        .lineTo(new Vector2d(42,-60.5))
//
//                        .waitSeconds(1)
//
//                        .splineToLinearHeading(new Pose2d(4, -35, Math.toRadians(90)), Math.toRadians(180))
//                        .lineTo(new Vector2d(4, -33))
//
//                        .lineTo(new Vector2d(42,-55))
//                        .lineTo(new Vector2d(42,-60.5))
//
//                        .waitSeconds(1)
//
//                        .splineToLinearHeading(new Pose2d(6, -35, Math.toRadians(90)), Math.toRadians(180))
//                        .lineTo(new Vector2d(6, -33))
//
//                        .lineTo(new Vector2d(42,-55))
//                        .lineTo(new Vector2d(42,-60.5))
//
//                        .waitSeconds(1)
//
//                        .splineToLinearHeading(new Pose2d(8, -35, Math.toRadians(90)), Math.toRadians(180))
//                        .lineTo(new Vector2d(8, -33))
//
//                        .lineTo(new Vector2d(42,-55))
//                        .lineTo(new Vector2d(42,-60.5))
//
//                        .waitSeconds(1)
//
//                        .splineToLinearHeading(new Pose2d(10, -35, Math.toRadians(90)), Math.toRadians(180))
//                        .lineTo(new Vector2d(10, -33))
//
//                        .lineTo(new Vector2d(42,-55))
//                        .lineTo(new Vector2d(42,-60.5))
//
//                        .waitSeconds(1)
//
//                        .splineToLinearHeading(new Pose2d(12, -35, Math.toRadians(90)), Math.toRadians(180))
//                        .lineTo(new Vector2d(12, -33))
//
//                        .lineTo(new Vector2d(42,-55))
//                        .lineTo(new Vector2d(42,-60.5))

                        //Linear Slides

//                        .lineTo(new Vector2d(9,-34))
//                        .lineTo(new Vector2d(9,-43))
//
//                        .lineToLinearHeading(new Pose2d(40, -50, Math.toRadians(75)))
//                        .lineToLinearHeading(new Pose2d(40, -53, Math.toRadians(-40)))
//                        .waitSeconds(0.5)
//
//                        .lineToLinearHeading(new Pose2d(53, -50, Math.toRadians(80)))
//                        .lineToLinearHeading(new Pose2d(50, -53, Math.toRadians(-45)))
//                        .waitSeconds(0.5)
//
//                        .lineToLinearHeading(new Pose2d(60, -50, Math.toRadians(77)))
//                        .lineToLinearHeading(new Pose2d(57, -53, Math.toRadians(-60)))
//                        .waitSeconds(0.5)
//
//                        .lineToLinearHeading(new Pose2d(25, -60.5, Math.toRadians(90)))
//                        .waitSeconds(0.5)
//
//                        .splineToLinearHeading(new Pose2d(11,-34, Math.toRadians(90)), Math.toRadians(90))
//                        .lineTo(new Vector2d(25, -60.5))
//
//                        .waitSeconds(1)
//
//                        .splineToLinearHeading(new Pose2d(7,-34, Math.toRadians(90)), Math.toRadians(90))
//                        .lineTo(new Vector2d(25, -60.5))
//
//                        .waitSeconds(1)
//
//                        .splineToLinearHeading(new Pose2d(5,-34, Math.toRadians(90)), Math.toRadians(90))
//                        .lineTo(new Vector2d(25, -60.5))
//
//                        .waitSeconds(1)
//
//                        .splineToLinearHeading(new Pose2d(3,-34, Math.toRadians(90)), Math.toRadians(90))
//                        .lineTo(new Vector2d(25, -60.5))
//
//                        .waitSeconds(1)
//
//                        .splineToLinearHeading(new Pose2d(1,-34, Math.toRadians(90)), Math.toRadians(90))
//                        .lineTo(new Vector2d(63, -60.5))
//
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}