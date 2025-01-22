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
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(9,-60.5, Math.toRadians(270)))

                        //Deposit Init
                        .lineTo(new Vector2d(9,-34))
                        .lineTo(new Vector2d(9,-32))
                        .lineTo((new Vector2d(9, -34)))

//                        .lineToLinearHeading(new Pose2d(35, -35, Math.toRadians(270)))
//                        .lineToLinearHeading(new Pose2d(35, -7, Math.toRadians(270)))
//
//                        .lineToLinearHeading(new Pose2d(45, -13, Math.toRadians(180)))
//
//                        .lineTo((new Vector2d(45, -51)))
//                        .splineToLinearHeading(new Pose2d(55, -13, Math.toRadians(180)), Math.toRadians(0))
//
//                        .lineTo((new Vector2d(55, -51)))
//                        .splineToLinearHeading(new Pose2d(62, -13, Math.toRadians(180)), Math.toRadians(0))
//
//                        .lineTo((new Vector2d(62, -51)))
//                        .splineToLinearHeading(new Pose2d(42, -55, Math.toRadians(90)), Math.toRadians(-90))

                        .lineToLinearHeading((new Pose2d(42, -58, Math.toRadians(90))))

                        .waitSeconds(0.75)

                        .splineToLinearHeading(new Pose2d(7, -35, Math.toRadians(270)), Math.toRadians(90))
                        .lineTo(new Vector2d(7,-33))
                        .lineTo((new Vector2d(7, -35)))

                        .splineToLinearHeading(new Pose2d(42, -55, Math.toRadians(90)), Math.toRadians(-90))
                        .lineTo((new Vector2d(37, -58)))
                        .waitSeconds(0.75)

//                        .splineToLinearHeading(new Pose2d(4, -35, Math.toRadians(270)), Math.toRadians(90))
//                        .lineTo(new Vector2d(4,-33))
//                        .lineTo((new Vector2d(4, -35)))

//                        .splineToLinearHeading(new Pose2d(4, -30, Math.toRadians(270)), Math.toRadians(180))
//                        .lineTo((new Vector2d(4, -35)))
//
//                        .splineToLinearHeading(new Pose2d(35, -58, Math.toRadians(90)), Math.toRadians(-90))
//                        .lineTo((new Vector2d(35, -60)))
//                        .waitSeconds(0.75)
//
//                        .splineToLinearHeading(new Pose2d(1, -30, Math.toRadians(270)), Math.toRadians(180))
//                        .lineTo((new Vector2d(1, -35)))
//
//                        .splineToLinearHeading(new Pose2d(35, -58, Math.toRadians(90)), Math.toRadians(-90))
//                        .lineTo((new Vector2d(35, -60)))
//                        .waitSeconds(0.75)
//
//                        .splineToLinearHeading(new Pose2d(-2, -30, Math.toRadians(270)), Math.toRadians(180))
//                        .lineTo((new Vector2d(-2, -35)))

                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}