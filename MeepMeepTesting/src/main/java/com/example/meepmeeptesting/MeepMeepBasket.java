package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepBasket {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-35,-62, Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(-53,-53), Math.toRadians(45))
                .waitSeconds(2)

                .strafeToLinearHeading(new Vector2d(-48.25,-45), Math.toRadians(90))
                .waitSeconds(1)

                //Deposit First
                .strafeToLinearHeading(new Vector2d(-53,-53), Math.toRadians(45))
                .waitSeconds(2)

                //Grab Second
                .strafeToLinearHeading(new Vector2d(-58.25,-45), Math.toRadians(90))
                .waitSeconds(1)

                //Deposit Second
                .strafeToLinearHeading(new Vector2d(-53,-53), Math.toRadians(45))
                .waitSeconds(2)

                //Grab Third
                .strafeToLinearHeading(new Vector2d(-60,-53), Math.toRadians(100))
                .waitSeconds(1)

                //Deposit Third
                .strafeToLinearHeading(new Vector2d(-53,-53), Math.toRadians(45))
                .waitSeconds(2)

                //Park
                .splineToLinearHeading(new Pose2d(-25, 0, Math.toRadians(0)), Math.toRadians(0))

                .waitSeconds(2)

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}