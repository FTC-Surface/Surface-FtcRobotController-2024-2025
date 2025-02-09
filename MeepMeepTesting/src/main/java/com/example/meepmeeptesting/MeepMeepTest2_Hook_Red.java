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
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(9,-60.5, Math.toRadians(90)))
//
//                        //PUSH
//                        .lineTo((new Vector2d(9, -31)))
//                        .waitSeconds(0.5)
//                        .lineToLinearHeading(new Pose2d(25, -36, Math.toRadians(90)))
//
//                        //push first
//                        .splineToLinearHeading(new Pose2d(45, -7, Math.toRadians(90)),Math.toRadians(0))
//                        .lineTo(new Vector2d(45, -48))
//
//                        //push second
//                        .splineToLinearHeading(new Pose2d(54, -7, Math.toRadians(90)), Math.toRadians(0))
//                        .lineTo(new Vector2d(54, -48))
//
//                        //push third
//                        .splineToLinearHeading(new Pose2d(61, -7, Math.toRadians(90)), Math.toRadians(0))
//                        .lineTo(new Vector2d(61, -48))
//                        .waitSeconds(0.3)
//
//
//                        //Second Hang
//                        .splineToLinearHeading(new Pose2d(40, -58, Math.toRadians(90)),Math.toRadians(0))
//                        .waitSeconds(0.5)
//                        .lineTo(new Vector2d(40,-60.5))
//                        .waitSeconds(0.3)
//                        .splineToLinearHeading(new Pose2d(11,-31, Math.toRadians(90)), Math.toRadians(90))
//
//                        //Third Hang
//                        .lineTo(new Vector2d(40, -58))
//                        .waitSeconds(0.5)
//                        .lineTo(new Vector2d(40,-60.5))
//                        .waitSeconds(0.3)
//                        .splineToLinearHeading(new Pose2d(7,-31, Math.toRadians(90)), Math.toRadians(90))
//
//                        //Fourth Hang
//                        .lineTo(new Vector2d(40, -58))
//                        .waitSeconds(0.5)
//                        .lineTo(new Vector2d(40,-60.5))
//                        .waitSeconds(0.3)
//                        .splineToLinearHeading(new Pose2d(5,-31, Math.toRadians(90)), Math.toRadians(90))
//
//                        //Fifth Hang
//                        .lineTo(new Vector2d(40, -58))
//                        .waitSeconds(0.5)
//                        .lineTo(new Vector2d(40,-60.5))
//                        .waitSeconds(0.3)
//                        .splineToLinearHeading(new Pose2d(3,-31, Math.toRadians(90)), Math.toRadians(90))
//
//                        //Park
//                        .lineTo(new Vector2d(40, -55))

                        //ACTIVE INTAKE
                        //Hang first
                        .lineTo(new Vector2d(9,-28))
                        .lineTo(new Vector2d(9,-40))

                        //Grab first
                        .lineToLinearHeading(new Pose2d(40, -43, Math.toRadians(80)))
                        .turn(Math.toRadians(-105))
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
                        .waitSeconds(0.1)
                        .splineToLinearHeading(new Pose2d(6,-28, Math.toRadians(90)), Math.toRadians(90))

                        //Third Hang
                        .lineTo(new Vector2d(40, -58))
                        .waitSeconds(0.5)
                        .lineTo(new Vector2d(40,-60.5))
                        .waitSeconds(0.1)
                        .splineToLinearHeading(new Pose2d(3,-28, Math.toRadians(90)), Math.toRadians(90))

                        //Fourth Hang
                        .lineTo(new Vector2d(40, -58))
                        .waitSeconds(0.5)
                        .lineTo(new Vector2d(40,-60.5))
                        .waitSeconds(0.1)
                        .splineToLinearHeading(new Pose2d(0,-28, Math.toRadians(90)), Math.toRadians(90))

                        //Fifth Hang
                        .lineTo(new Vector2d(40, -58))
                        .waitSeconds(0.5)
                        .lineTo(new Vector2d(40,-60.5))
                        .waitSeconds(0.1)
                        .splineToLinearHeading(new Pose2d(-3,-28, Math.toRadians(90)), Math.toRadians(90))

                        //Park
                        .lineTo(new Vector2d(40, -55))

                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}