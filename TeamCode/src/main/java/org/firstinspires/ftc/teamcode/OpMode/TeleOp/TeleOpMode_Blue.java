package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.Constants;

@TeleOp(name = "Mecanum Drive", group = "OpModes")
@Config
public class TeleOpMode_Blue extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot(hardwareMap);

        ElapsedTime intakeTimer, outtakeTimer;
        intakeTimer = outtakeTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

        boolean xOpenClawDone, yOpenClawDone, yArmstartDone, yPressed, bumperPressed;
        xOpenClawDone = yOpenClawDone = yArmstartDone = yPressed = bumperPressed = false;

        boolean Outtakepressed1, Triggerpressed, stickmoved2, OuttakeclawDone, ArmTakeDone,
                OpenClawDone, LinearSlidereadyDone, OpenClawDone2, Arm_Start_position, IslidesIn;

        Outtakepressed1 = Triggerpressed = stickmoved2 = OuttakeclawDone = ArmTakeDone =
                OpenClawDone = LinearSlidereadyDone = OpenClawDone2 = false;

        long OuttakeStartTime1 = 0;
        long OuttakeStartTime2 = 0;
        long OuttakeStartTime3 = 0;

        long IntakeStartTime1 = 0;

        long yActionStartTime = 0;

        boolean stickmoved1 = false;
        boolean hasBlock = false;

        //Change depending on alliance color
        double r, g, b;

        //Change depending on alliance color
        Constants.eColorSensed allianceColor = Constants.eColorSensed.blue;
        Constants.eColorSensed enemyColor = Constants.eColorSensed.red;

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        robot.oElevMove(Constants.eOElevatorState.Ready);

        robot.iArmStart();

        robot.oOpenClaw();
        robot.oArmStart();
        Arm_Start_position = true;
        IslidesIn = true;

        outtakeTimer.reset();
        intakeTimer.reset();

        while (opModeIsActive() && !isStopRequested()) {
//********** Telemetry **********************************************************

//            telemetry.addData("Intake Slide Pos: ", robot.iElevGetHeight());
////            telemetry.addData("Outtake Slide Pos: ", robot.oElevGetHeight());
//
////            telemetry.addData("Block Color: ", robot.getColorBlock());
            telemetry.addData("Intake Slides In: ", IslidesIn);
            telemetry.addData("Intake Arm at Start: ", Arm_Start_position);
//
//
//            telemetry.update();
//
//            robot.iSlideLoop();
//            robot.oSlideLoop();

//********** Player One Controls ***************************************************

            //Drive
            double drive = -gamepad1.left_stick_y;
            double strafe = -gamepad1.left_stick_x;
            double rotate = -gamepad1.right_stick_x;
            
            robot.teleOpDrive(drive * 0.8, strafe * 0.8, rotate * 0.6);

//********** Player Two Controls ***************************************************

            //Outtake

            //Slide_down + Claw_close + Arm_dump + Slide_up (4 If statement together)
            if (gamepad2.cross && !Outtakepressed1) {
                Outtakepressed1 = true;
                OuttakeclawDone = false;
                LinearSlidereadyDone = false;
                OuttakeStartTime1 = (long) outtakeTimer.milliseconds();
                robot.oElevMove(Constants.eOElevatorState.Ground);
            }
            if (Outtakepressed1 && !OuttakeclawDone && !LinearSlidereadyDone && outtakeTimer.milliseconds() - OuttakeStartTime1 >= 300) {//400
                robot.oCloseClaw();
                OuttakeclawDone = true;
            }
            if (Outtakepressed1 && !LinearSlidereadyDone && OuttakeclawDone && outtakeTimer.milliseconds() - OuttakeStartTime1 >= 600) {//700
                robot.oElevMove(Constants.eOElevatorState.Basket);
                LinearSlidereadyDone = true;
            }
            if (Outtakepressed1 && OuttakeclawDone && LinearSlidereadyDone && outtakeTimer.milliseconds() - OuttakeStartTime1 >= 1600) {//1200
                robot.oArmDumpRelease();
                Outtakepressed1 = false;
            }

            //Arm Positiong to grab readied sample including Dropping sample motion
            if (gamepad2.triangle) {
                robot.oOpenClaw();
                waitForSeconds(0.1);
                robot.oElevMove(Constants.eOElevatorState.Ready);
                robot.oArmTake();
            }

//            //Outtake_claw
//            if (gamepad2.right_bumper) {
//                robot.oCloseClaw();
//            }
//            if (gamepad2.left_bumper)
//                robot.oOpenClaw();
//
//            //Hook
            //Prepare for robot to score by clipping
            if (gamepad2.left_stick_y <= -0.3 && !stickmoved1) {
                robot.oCloseClaw();
                waitForSeconds(0.3);
                robot.oArmHookup();
                robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
            }
//
//            //Prepare to grab specimen for scoring
            if (gamepad2.left_stick_y >= 0.3) {
                robot.oOpenClaw();
                waitForSeconds(0.2);
                robot.oElevMove(Constants.eOElevatorState.Ground);
                robot.oArmHookgrab();
            }
//
//
////            if(gamepad2.square && !stickmoved1) {
////                robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
////                OuttakeStartTime3 = (long) outtakeTimer.milliseconds();
////                stickmoved1 = true;
////                OpenClawDone2 = false;
////            }
////            if(stickmoved1 && !OpenClawDone2 && outtakeTimer.milliseconds() - OuttakeStartTime3 >= 700) {
////                robot.oOpenClaw();
////                OpenClawDone2 = true;
////            }
////            if(stickmoved1 && OpenClawDone2 && outtakeTimer.milliseconds() - OuttakeStartTime3 >= 1000) {
////                robot.oArmHookgrab();
////                robot.oElevMove(Constants.eOElevatorState.Clip_Grab);
////                stickmoved1 = false;
////            }
//
//
////********** Intake ***************************************************
//
//
//
//
            if (gamepad2.dpad_down) {
                robot.iArmStart();
                ArmTakeDone=false;
                robot.iElevMove(Constants.eIElevatorState.InIntake);
                IslidesIn = true;
            }
            if (gamepad2.dpad_left) {
                robot.iArmStart();
                ArmTakeDone=false;
                IntakeStartTime1 = (long) intakeTimer.milliseconds();
                robot.iElevMove(Constants.eIElevatorState.ShortRange);
                IslidesIn = false;
            }
            if (gamepad2.dpad_up) {
                robot.iArmStart();
                ArmTakeDone=false;
                IntakeStartTime1 = (long) intakeTimer.milliseconds();
                robot.iElevMove(Constants.eIElevatorState.MidRange);
                IslidesIn = false;
            }
            if (gamepad2.dpad_right) {
                robot.iArmStart();
                ArmTakeDone=false;
                IntakeStartTime1 = (long) intakeTimer.milliseconds();
                robot.iElevMove(Constants.eIElevatorState.LongRange);
                IslidesIn = false;
            }
//
            IslidesIn = (robot.iElevGetHeight() <=5);

            if(!ArmTakeDone && intakeTimer.milliseconds()-IntakeStartTime1>=100)
            {
                Arm_Start_position = true;
                ArmTakeDone=true;
            }


//            if (gamepad2.right_stick_y <= -0.3) {
//                robot.iElevMove(Constants.eIElevatorState.ManualForward);
//                IslidesIn = false;
//            } else if (gamepad2.right_stick_y >= 0.3) {
//                robot.iElevMove(Constants.eIElevatorState.ManualBackward);
//                IslidesIn = false;
//            } else {
//                robot.iElevMove(Constants.eIElevatorState.ManualStop);
//                IslidesIn = false;
//            }


            if (gamepad2.right_trigger >= 0.3 && robot.getColorResult() != allianceColor && robot.getColorResult() != Constants.eColorSensed.yellow) {//specifically for red
                if(robot.getColorResult() == enemyColor) robot.iArmMiddle(); else robot.iArmGrab();
                robot.iWheelTakeBlock();
                Arm_Start_position = false;
            } else if (gamepad2.left_trigger >= 0.3) {
                robot.iWheelOutBlock();
            } else {
                robot.iWheelStopBlock();
            }



            if (robot.getColorResult() == allianceColor || robot.getColorResult() == Constants.eColorSensed.yellow) {
                robot.iArmStart();
                IntakeStartTime1 = (long) intakeTimer.milliseconds();
                ArmTakeDone=false;
                robot.iElevMove(Constants.eIElevatorState.InIntake);
            }

            if (Arm_Start_position && robot.getColorResult() == Constants.eColorSensed.yellow && IslidesIn) {
                robot.iWheelTakeBlock();
            }

//********** Controller Color ***************************************************

            //Change color on controller depending on the block we have
//            if (robot.getColorResult() == Constants.eColorSensed.red) {
//                r = 250;
//                g = b = 0;
//            } else if (robot.getColorResult() == Constants.eColorSensed.blue) {
//                b = 250;
//                r = g = 0;
//            } else if (robot.getColorResult() == Constants.eColorSensed.yellow) {
//                r = g = 250;
//                b = 0;
//            } else {
//                //set the color to green if there is no block
//                r = 255;
//                g = 255;
//                b = 255;
//            }

//            gamepad1.setLedColor(r, g, b, 500);
//            gamepad2.setLedColor(r, g, b, 500);
        }
    }

    public void waitForSeconds(double seconds) {
        ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        timer.reset();

        while (timer.time() < seconds) {
        }
    }
}
