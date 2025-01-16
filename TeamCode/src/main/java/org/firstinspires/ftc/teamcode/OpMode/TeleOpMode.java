package org.firstinspires.ftc.teamcode.OpMode;

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
public class TeleOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot(hardwareMap);
        ElapsedTime intakeTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        ElapsedTime outtakeTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        Constants constants = new Constants();

        boolean xOpenClawDone = false;
        boolean yOpenClawDone = false;
        boolean yArmstartDone = false;
        boolean yPressed = false;
        boolean bumperPressed = false;
        long yActionStartTime = 0;

        long OuttakeStartTime1=0;
        long OuttakeStartTime2=0;
        boolean Outtakepressed1 = false;
        boolean Outtakepressed2 = false;
        boolean OuttakeclawDone = false;
        boolean ArmTakeDone = false;
        boolean OpenClawDone = false;
        boolean LinearSlidereadyDone = false;

        Constants.eIntakeState intakeState = Constants.eIntakeState.iIntakeReady;

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();
//        robot.oArmStart();
        robot.oElevMove(Constants.eOElevatorState.Ready);

        robot.iArmHover();
        robot.iOpenClaw();

        robot.oOpenClaw();

        intakeTimer.reset();

        while (opModeIsActive() && !isStopRequested()) {

//            telemetry.addData("Outtake Elev Height", robot.oElevGetHeight());
//            telemetry.addData("Outtake Elev Is Busy", robot.oElevIsBusy());
//
//            telemetry.update();
//
//            robot.oSlideLoop();

//********** Player One Controls ***************************************************

            //Drive
            double drive = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            robot.teleOpDrive(drive * 0.6,strafe * 0.6,rotate * 0.6);

//          Outtake Linear Slides
            if(gamepad1.dpad_up)
                robot.oElevMove(Constants.eOElevatorState.Basket);

            if(gamepad1.dpad_down)
                robot.oElevMove(Constants.eOElevatorState.Ready);

//********** Player Two Controls ***************************************************

//********** Outtake ***************************************************

            //Grab + Claw_close + Up/Arm_dump
            if(gamepad2.right_trigger > 0.5 && !Outtakepressed1){
                robot.oElevMove(Constants.eOElevatorState.Grab);
                Outtakepressed1 = true;
                OuttakeclawDone = false;
                LinearSlidereadyDone = false;
                OuttakeStartTime1 = (long) outtakeTimer.milliseconds();
            }
            if(Outtakepressed1 && !OuttakeclawDone && !LinearSlidereadyDone && outtakeTimer.milliseconds() - OuttakeStartTime1 >= 700){
                robot.oCloseClaw();
                OuttakeclawDone = true;
            }
            if(Outtakepressed1 &&  !LinearSlidereadyDone && OuttakeclawDone && outtakeTimer.milliseconds() - OuttakeStartTime1 >= 1000){
                robot.oElevMove(Constants.eOElevatorState.Ready);
                LinearSlidereadyDone = true;
            }
            if(Outtakepressed1 && OuttakeclawDone && LinearSlidereadyDone && outtakeTimer.milliseconds() - OuttakeStartTime1 >= 1500){
                robot.oArmDump();
                Outtakepressed1 = false;
            }

            //Arm_position
            if(gamepad2.triangle) {
                robot.oArmTake();
                robot.oOpenClaw();
            }
            if(gamepad2.cross)
                robot.oArmDump();

            //Outtake_claw
            if(gamepad2.right_bumper)
                robot.oCloseClaw();
            if(gamepad2.left_bumper)
                robot.oOpenClaw();

            //Hook
            if(gamepad2.right_stick_y <= -0.5)
                robot.oArmHookgrab();
            if(gamepad2.right_stick_y >=1)
                robot.oArmHookstart();
            if(gamepad2.right_stick_y >= 0.5 && gamepad2.right_stick_y <1)
                robot.oArmHookup();

//            if(gamepad2.a)
//                robot.oArmHookstart();
                

//********** Intake ***************************************************

//          Intake Version 2

            if(gamepad2.dpad_up)//Arm_Up
                robot.iArmHover();
            if(gamepad2.dpad_down)//Arm_Down
                robot.iArmGrab();


            if (gamepad2.left_trigger >=0.5 && !bumperPressed) {
                robot.iCloseClaw();
                bumperPressed = true;
                xOpenClawDone = false;
                yArmstartDone=false;
                yActionStartTime = (long) intakeTimer.milliseconds();
            }
            if (bumperPressed && !yArmstartDone && intakeTimer.milliseconds() - yActionStartTime >= 300) {
                robot.iArmStart();
                yArmstartDone=true;
            }
            if (bumperPressed && yArmstartDone && !xOpenClawDone && intakeTimer.milliseconds() - yActionStartTime >= 1000) {
                robot.iOpenClaw();
                xOpenClawDone = true;
            }
            if (bumperPressed && xOpenClawDone && yArmstartDone && intakeTimer.milliseconds() - yActionStartTime >= 1500) {
                robot.iArmHover();
                bumperPressed = false;
            }
        }
    }

    public void waitForSeconds(double seconds){
        ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        timer.reset();

        while(timer.time() < seconds){
        }
    }
}

//
//


////          Intake Version 1 (Down+Up)
//            if (gamepad2.y && !yPressed) {
//                yPressed = true;
//                xOpenClawDone = false;
//                yOpenClawDone=false;
//                yArmstartDone=false;
//                yActionStartTime = (long) intakeTimer.milliseconds();
//                robot.iArmGrab();
//            }
//
//            if (yPressed && !yOpenClawDone && intakeTimer.milliseconds() - yActionStartTime >= 100) {
//                robot.iCloseClaw();
//                yOpenClawDone=true;
//            }
//            if (yPressed && yOpenClawDone && !yArmstartDone && intakeTimer.milliseconds() - yActionStartTime >= 600) {
//                 // Reset flag for the open claw action
//                robot.iArmStart();
//                yArmstartDone=true;
//            }
//            if (yPressed && !xOpenClawDone && yArmstartDone && yOpenClawDone && intakeTimer.milliseconds() - yActionStartTime >= 1000) {
//                robot.iOpenClaw();
//                xOpenClawDone = true;
//            }
//            if (yPressed && xOpenClawDone && yArmstartDone && yOpenClawDone && intakeTimer.milliseconds() - yActionStartTime >= 1500) {
//                robot.iArmHover();
//                yPressed = false;
//            }

//            switch(intakeState){
//                case iIntakeReady:
//                    if (gamepad2.y) {
//                        yPressed = true;
//                        xOpenClawDone = false;
//                        yOpenClawDone=false;
//                        yArmstartDown=false;

//                        yActionStartTime = (long) intakeTimer.milliseconds();

//                        robot.iArmGrab();
//                        intakeState = eIntakeState.iCloseClaw;
//                    }
//                    break;
//
//                case iCloseClaw:
//                    if(intakeTimer.milliseconds() - yActionStartTime >= 100){
//                        robot.iCloseClaw();
//                        intakeState = eIntakeState.iStartArm;
//                    }
//                    break;
//
//                case iStartArm:
//                    if (intakeTimer.milliseconds() - yActionStartTime >= 500) {
//                        // Reset flag for the open claw action
//                        robot.iArmStart();
//                        intakeState = eIntakeState.iOpenClaw;
//                    }
//                    break;
//
//                case iOpenClaw:
//                    if(intakeTimer.milliseconds() - yActionStartTime >= 1000){
//                        robot.iOpenClaw();
//                        xOpenClawDone = true;
//                        intakeState = eIntakeState.iResetArm;
//                    }
//                    break;
//
//                case iResetArm:
//                    if(intakeTimer.milliseconds() - yActionStartTime >= 1500){
//                        robot.iArmHover();
//                        yPressed = false;
//                        intakeState = eIntakeState.iIntakeReady;
//                    }
//                    break;
//            }


//Dump + Claw_Open + Arm_Start + Slide_Ready
//            if(gamepad2.left_trigger > 0.5 && !Outtakepressed2)
//            {
//                robot.oArmDump();
//                OuttakeStartTime2 = (long) outtakeTimer.milliseconds();
//                Outtakepressed2 = true;
//                ArmTakeDone = false;
//                OpenClawDone = false;
//            }
//            if(Outtakepressed2 && !OpenClawDone && outtakeTimer.milliseconds() - OuttakeStartTime2 >= 300){
//                robot.oOpenClaw();
//                OpenClawDone = true;
//            }
//            if(Outtakepressed2 && !ArmTakeDone && OpenClawDone && outtakeTimer.milliseconds() - OuttakeStartTime2 >= 500){
//                robot.oArmStart();
//                ArmTakeDone = true;
//            }
//            if(Outtakepressed2 && ArmTakeDone && OpenClawDone && outtakeTimer.milliseconds() - OuttakeStartTime2 >= 800){
//                //robot.oElevMove(Constants.eOElevatorState.Ready);
//                Outtakepressed2 = false;
//            }
//
