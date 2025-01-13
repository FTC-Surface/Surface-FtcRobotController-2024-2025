package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTest2 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(7.5,-58, 0))
                        .lineTo(new Vector2d(7.5,-34))
                        .lineTo(new Vector2d(7.5,-50))

                        .splineToLinearHeading(new Pose2d(48, -13, Math.toRadians(90)), Math.toRadians(270))

                        .lineTo((new Vector2d(48, -53)))

                        .splineToLinearHeading(new Pose2d(59, -13, Math.toRadians(90)), Math.toRadians(0))

                        .lineTo((new Vector2d(59, -53)))

                        .splineToLinearHeading(new Pose2d(62, -13, Math.toRadians(90)), Math.toRadians(0))

                        .lineTo((new Vector2d(62, -53)))

                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}