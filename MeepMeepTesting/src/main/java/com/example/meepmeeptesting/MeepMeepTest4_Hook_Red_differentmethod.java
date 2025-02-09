package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTest4_Hook_Red_differentmethod {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(470), Math.toRadians(360), 15.25)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(9,-60.5, Math.toRadians(90)))
                        //ACTIVE INTAKE
                        //Hang first
                        .lineTo(new Vector2d(9,-34))

                        //Grab first
                        .setReversed(true)
                        .splineToLinearHeading(new Pose2d(40, -43, Math.toRadians(70)), Math.toRadians(0))
                        .setReversed(false)
                        .turn(Math.toRadians(-100))
                        .waitSeconds(0.5)

                        //Grab Second
                        .lineToLinearHeading(new Pose2d(45, -43, Math.toRadians(60)))
                        .turn(Math.toRadians(-90))
                        .waitSeconds(0.5)

                        //Grab Third
                        .lineToLinearHeading(new Pose2d(50, -43, Math.toRadians(50)))
                        .turn(Math.toRadians(-80))
                        .waitSeconds(0.5)

                        //Second Hang
                        .lineToLinearHeading(new Pose2d(40, -58, Math.toRadians(90)))
                        .waitSeconds(0.5)
                        .lineTo(new Vector2d(40,-60.5))
                        .setReversed(false)
                        .splineToLinearHeading(new Pose2d(6,-34, Math.toRadians(90)), Math.toRadians(90))

                        //Third Hang
                        .setReversed(true)
                        .splineToLinearHeading(new Pose2d(40,-58, Math.toRadians(90)), Math.toRadians(-90))
                        .waitSeconds(0.5)
                        .lineTo(new Vector2d(40,-60.5))
                        .setReversed(false)
                        .splineToLinearHeading(new Pose2d(3,-34, Math.toRadians(90)), Math.toRadians(90))

                        //Fourth Hang
                        .setReversed(true)
                        .splineToLinearHeading(new Pose2d(40,-58, Math.toRadians(90)), Math.toRadians(-90))
                        .waitSeconds(0.5)
                        .lineTo(new Vector2d(40,-60.5))
                        .setReversed(false)
                        .splineToLinearHeading(new Pose2d(0,-34, Math.toRadians(90)), Math.toRadians(90))

                        //Fifth Hang
                        .setReversed(true)
                        .splineToLinearHeading(new Pose2d(40,-58, Math.toRadians(90)), Math.toRadians(-90))
                        .waitSeconds(0.5)
                        .lineTo(new Vector2d(40,-60.5))
                        .setReversed(false)
                        .splineToLinearHeading(new Pose2d(-3,-34, Math.toRadians(90)), Math.toRadians(90))

                        .setReversed(true)
                        .splineToLinearHeading(new Pose2d(40,-58, Math.toRadians(90)), Math.toRadians(-90))

                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}