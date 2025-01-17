package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTest3_Basket_Red {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(40, 40, Math.toRadians(470), Math.toRadians(180), 15.25)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-35,-62, Math.toRadians(90)))

                        .lineToLinearHeading(new Pose2d(-55, -50, Math.toRadians(45)))
                        //.splineToLinearHeading(new Pose2d(-53, -52, Math.toRadians(45)),Math.toRadians(180))
                        
                        .waitSeconds(2)

                        .lineToLinearHeading(new Pose2d(-48,-35, Math.toRadians(90)))
                        .waitSeconds(1)

                        .lineToLinearHeading(new Pose2d(-55, -50, Math.toRadians(45)))
                        .waitSeconds(2)

                        .lineToLinearHeading(new Pose2d(-58,-35, Math.toRadians(90)))
                        .waitSeconds(1)

                        .lineToLinearHeading(new Pose2d(-55, -50, Math.toRadians(45)))
                        .waitSeconds(2)

                        .lineToLinearHeading(new Pose2d(-62,-35, Math.toRadians(120)))
                        .waitSeconds(1)

                        .lineToLinearHeading(new Pose2d(-55, -50, Math.toRadians(45)))
                        .waitSeconds(2)

                        .lineToLinearHeading(new Pose2d(-20, -13, Math.toRadians(45)))
                        .waitSeconds(2)

                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}