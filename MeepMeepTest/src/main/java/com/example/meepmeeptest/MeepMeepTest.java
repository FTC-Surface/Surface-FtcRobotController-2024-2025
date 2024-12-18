package com.example.meepmeeptest;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTest {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(7.5,-58, 3.14159268979/2))
                        .lineTo(new Vector2d(7.5,-34))

                        .lineTo(new Vector2d(48,-37))
                        .lineToLinearHeading(new Pose2d(48, -48, Math.toRadians(270)))

                        .lineToLinearHeading(new Pose2d(58, -37, Math.toRadians(90)))
                        .lineToLinearHeading(new Pose2d(58, -48, Math.toRadians(270)))

                        .lineToLinearHeading(new Pose2d(69, -37, Math.toRadians(90)))
                        .lineToLinearHeading(new Pose2d(69, -48, Math.toRadians(270)))

                        .lineToLinearHeading(new Pose2d(45, -58, Math.toRadians(270)))
                        .lineToLinearHeading(new Pose2d(7.5, -34, Math.toRadians(90)))

                        .lineToLinearHeading(new Pose2d(45, -58, Math.toRadians(270)))
                        .lineToLinearHeading(new Pose2d(9.5, -34, Math.toRadians(90)))

                        .lineToLinearHeading(new Pose2d(45, -58, Math.toRadians(270)))
                        .lineToLinearHeading(new Pose2d(11.5, -34, Math.toRadians(90)))

                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}