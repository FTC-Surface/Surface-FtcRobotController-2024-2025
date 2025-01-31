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

                        //Launching
//                        .lineTo(new Vector2d(9,-34))
//                        .lineTo(new Vector2d(9,-43))
//
//                        .lineToLinearHeading(new Pose2d(40, -35, Math.toRadians(45)))
//                        .lineToLinearHeading(new Pose2d(37, -40, Math.toRadians(-65)))
//                        .waitSeconds(0.5)
//
//                        .lineToLinearHeading(new Pose2d(53, -35, Math.toRadians(45)))
//                        .lineToLinearHeading(new Pose2d(50, -40, Math.toRadians(-75)))
//                        .waitSeconds(0.5)
//
//                        .lineToLinearHeading(new Pose2d(60, -35, Math.toRadians(45)))
//                        .lineToLinearHeading(new Pose2d(57, -40, Math.toRadians(-80)))
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
//                        .lineTo(new Vector2d(63, -60.5))
//
//                        .build());

                        //Linear Slides
//
//                        .lineTo(new Vector2d(9,-34))
//                        .lineTo(new Vector2d(9,-43))
//
//                        .lineToLinearHeading(new Pose2d(40, -45, Math.toRadians(65)))
//                        .lineToLinearHeading(new Pose2d(40, -47, Math.toRadians(-40)))
//                        .waitSeconds(0.5)
//
//                        .lineToLinearHeading(new Pose2d(53, -45, Math.toRadians(65)))
//                        .lineToLinearHeading(new Pose2d(50, -47, Math.toRadians(-45)))
//                        .waitSeconds(0.5)
//
//                        .lineToLinearHeading(new Pose2d(60, -45, Math.toRadians(65)))
//                        .lineToLinearHeading(new Pose2d(57, -47, Math.toRadians(-60)))
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