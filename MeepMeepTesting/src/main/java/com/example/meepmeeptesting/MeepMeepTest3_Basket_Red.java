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

                        //Deposit Init
                        .lineToLinearHeading(new Pose2d(-52, -47, Math.toRadians(45)))
                        .lineToLinearHeading(new Pose2d(-58, -53, Math.toRadians(45)))
                        .lineToLinearHeading(new Pose2d(-52, -47, Math.toRadians(45)))
                        
                        .waitSeconds(2)

                        //Grab First
                        .lineToLinearHeading(new Pose2d(-48.25,-35.5, Math.toRadians(90)))

                        .waitSeconds(1)

                        //Deposit First
                        .lineToLinearHeading(new Pose2d(-52, -47, Math.toRadians(45)))
                        .lineToLinearHeading(new Pose2d(-58, -53, Math.toRadians(45)))
                        .lineToLinearHeading(new Pose2d(-52, -47, Math.toRadians(45)))

                        .waitSeconds(2)

                        //Grab Second
                        .lineToLinearHeading(new Pose2d(-63,-35, Math.toRadians(90)))

                        .waitSeconds(1)

                        //Deposit Second
                        .lineToLinearHeading(new Pose2d(-52, -47, Math.toRadians(45)))
                        .lineToLinearHeading(new Pose2d(-58, -53, Math.toRadians(45)))
                        .lineToLinearHeading(new Pose2d(-52, -47, Math.toRadians(45)))

                        .waitSeconds(2)

                        //Grab Third
                        .lineToLinearHeading(new Pose2d(-62,-35, Math.toRadians(120)))

                        .waitSeconds(1)

                        //Deposit Third
                        .lineToLinearHeading(new Pose2d(-52, -47, Math.toRadians(45)))
                        .lineToLinearHeading(new Pose2d(-58, -53, Math.toRadians(45)))
                        .lineToLinearHeading(new Pose2d(-52, -47, Math.toRadians(45)))

                        .waitSeconds(2)

                        //Park
                        .lineToLinearHeading(new Pose2d(-20, -13, Math.toRadians(0)))

                        .waitSeconds(2)

                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}