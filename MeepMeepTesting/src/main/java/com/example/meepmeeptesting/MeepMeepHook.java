package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepHook {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 18)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(9,-60.5, Math.toRadians(90)))
                .lineToY(-26)
                .lineToY(-40)

                //Grab first
                .setReversed(true)
                .strafeToLinearHeading(new Vector2d(40, -43), Math.toRadians(65))
                .turn(Math.toRadians(-100))
                .waitSeconds(0.5)

//                //Grab Second
                .turn(Math.toRadians(70))
                .turn(Math.toRadians(-80))
                .waitSeconds(0.5)
//
//                //Grab Third
                .turn(Math.toRadians(80))
                .turn(Math.toRadians(-60))
                .waitSeconds(0.5)
//
//                //Second Hang
                .strafeToLinearHeading(new Vector2d(40, -58), Math.toRadians(90))
                .waitSeconds(0.5)
                .lineToY(-60.5)
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(6,-26, Math.toRadians(90)), Math.toRadians(90))
//
//                //Third Hang
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(40,-58, Math.toRadians(90)), Math.toRadians(-90))
                .waitSeconds(0.5)
                .lineToY(-60.5)
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(3,-26, Math.toRadians(90)), Math.toRadians(90))
//
//                //Fourth Hang
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(40,-58, Math.toRadians(90)), Math.toRadians(-90))
                .waitSeconds(0.5)
                .lineToY(-60.5)
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(0,-26, Math.toRadians(90)), Math.toRadians(90))
//
//                //Fifth Hang
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(40,-58, Math.toRadians(90)), Math.toRadians(-90))
                .waitSeconds(0.5)
                .lineToY(-60.5)
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(0,-26, Math.toRadians(90)), Math.toRadians(90))
//
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(40,-58, Math.toRadians(90)), Math.toRadians(-90))

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}