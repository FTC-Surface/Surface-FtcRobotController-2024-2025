package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTest3 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-40,-58, Math.toRadians(90)))
                        .splineToLinearHeading(new Pose2d(-55, -54, Math.toRadians(45)), 180 )

                        .lineToLinearHeading(new Pose2d(-48,-37, Math.toRadians(90)))
                        .lineToLinearHeading(new Pose2d(-55, -54, Math.toRadians(45)))

                        .lineToLinearHeading(new Pose2d(-58,-37, Math.toRadians(90)))
                        .lineToLinearHeading(new Pose2d(-55, -54, Math.toRadians(45)))

                        .lineToLinearHeading(new Pose2d(-62,-37, Math.toRadians(120)))
                        .lineToLinearHeading(new Pose2d(-55, -54, Math.toRadians(45)))

                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}