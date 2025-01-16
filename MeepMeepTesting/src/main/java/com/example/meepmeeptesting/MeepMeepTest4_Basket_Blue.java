package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTest4_Basket_Blue {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(470), Math.toRadians(0), 15.25)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35,60, Math.toRadians(90)))

                        .lineToLinearHeading(new Pose2d(53, 52, Math.toRadians(225)))
                        .waitSeconds(2)

                        .lineToLinearHeading(new Pose2d(48,37, Math.toRadians(270)))
                        .waitSeconds(1)
                        .lineToLinearHeading(new Pose2d(53, 52, Math.toRadians(225)))
                        .waitSeconds(2)

                        .lineToLinearHeading(new Pose2d(58,37, Math.toRadians(270)))
                        .waitSeconds(1)
                        .lineToLinearHeading(new Pose2d(53, 52, Math.toRadians(225)))
                        .waitSeconds(2)

                        .lineToLinearHeading(new Pose2d(62,37, Math.toRadians(300)))
                        .waitSeconds(1)
                        .lineToLinearHeading(new Pose2d(53, 52, Math.toRadians(225)))
                        .waitSeconds(2)

                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}