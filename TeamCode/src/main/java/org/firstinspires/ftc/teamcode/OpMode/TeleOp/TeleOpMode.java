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
public class TeleOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot(hardwareMap);
        ElapsedTime intakeTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        ElapsedTime outtakeTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

        Constants constants = new Constants();

//        boolean xOpenClawDone = false;
//        boolean yOpenClawDone = false;
//        boolean yArmstartDone = false;
//        boolean yPressed = false;
//        boolean bumperPressed = false;
//        long yActionStartTime = 0;

        long OuttakeStartTime1=0;
//        long IntakeStartTime2=0;
        long OuttakeStartTime3=0;
//        long OuttakeStartTime4=0;

//        boolean Outtakepressed1 = false;
//        boolean Triggerpressed = false;
        boolean stickmoved1 = false;
//        boolean stickmoved2 = false;
//        boolean OuttakeclawDone = false;
//        boolean ArmTakeDone = false;
//        boolean OpenClawDone = false;
//        boolean LinearSlidereadyDone = false;
//        boolean OpenClawDone2 = false;

        Constants.eIntakeState intakeState = Constants.eIntakeState.iIntakeReady;
        Constants.eOuttakeStateClipHang outtakeStateClipping = Constants.eOuttakeStateClipHang.oOuttakeReady;
        Constants.eOuttakeStateGrabDump outtakeStateGrabbing = Constants.eOuttakeStateGrabDump.oOuttakeReady;

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        robot.oElevMove(Constants.eOElevatorState.Ready);

//        robot.iArmHover();
//        robot.iOpenClaw();

        robot.oOpenClaw();
        robot.oArmStart();

        intakeTimer.reset();

        while (opModeIsActive() && !isStopRequested()) {

//********** Player One Controls ***************************************************

            //Drive
            double drive = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            robot.teleOpDrive(drive * 0.8,strafe * 0.8,rotate * 0.7);

//          Outtake Linear Slides
            if(gamepad1.dpad_up) robot.oElevMove(Constants.eOElevatorState.Basket);

            //Jack why has this been commented out?
//            if(gamepad1.dpad_down) robot.oElevMove(Constants.eOElevatorState.Ready);

//********** Player Two Controls ***************************************************

//********** Outtake ***************************************************

            switch (outtakeStateGrabbing){
                case oOuttakeReady:
                    if(gamepad2.circle){
                        //Insert Code Here

                        OuttakeStartTime1 = (long) outtakeTimer.milliseconds();

                        outtakeStateGrabbing = Constants.eOuttakeStateGrabDump.oOuttakeCloseClaw;
                    }
                case oOuttakeCloseClaw:
                    if(outtakeTimer.milliseconds() - OuttakeStartTime1 >= 0){
                        outtakeStateGrabbing = Constants.eOuttakeStateGrabDump.oOuttakeOuttakeSlides;
                    }
                case oOuttakeOuttakeSlides:
                    if(outtakeTimer.milliseconds() - OuttakeStartTime1 >= 0){
                        robot.oCloseClaw();

                        outtakeStateGrabbing = Constants.eOuttakeStateGrabDump.oOuttakeArmDump;
                    }
                case oOuttakeArmDump:
                    if(outtakeTimer.milliseconds() - OuttakeStartTime1 >= 400){
                        robot.oArmDumpReady();

                        outtakeStateGrabbing = Constants.eOuttakeStateGrabDump.oOuttakeReady;
                    }
            }

            //Arm_position
            if(gamepad2.triangle) {
                robot.oOpenClaw();
                robot.oElevMove(Constants.eOElevatorState.Ready);
                robot.oArmTake();
            }

            if(gamepad2.cross)
                robot.oArmDumpRelease();

            //Outtake_claw
            if(gamepad2.right_bumper) {
                robot.oCloseClaw();
            }
            if(gamepad2.left_bumper)
                robot.oOpenClaw();

            //Hook
            if(gamepad2.left_stick_y <= -0.5 && !stickmoved1) {
                robot.oCloseClaw();

                waitForSeconds(0.3);

                robot.oElevMove(Constants.eOElevatorState.Clip_Ready);

                waitForSeconds(0.25);

                robot.oArmHookup();
            }

            if(gamepad2.left_stick_y >= 0.5) {
                robot.oArmHookgrab();
                robot.oOpenClaw();
                robot.oElevMove(Constants.eOElevatorState.Clip_Grab);
            }

            switch (outtakeStateClipping){
                case oOuttakeReady:
                    if(gamepad2.square){
                        robot.oElevMove(Constants.eOElevatorState.Clip_Hang);

                        OuttakeStartTime3 = (long) outtakeTimer.milliseconds();

                        stickmoved1 = true;

                        outtakeStateClipping = Constants.eOuttakeStateClipHang.oOuttakeOpenClaw;
                    }
                case oOuttakeOpenClaw:
                    if(outtakeTimer.milliseconds() - OuttakeStartTime3 >= 700){
                        robot.oOpenClaw();

                        outtakeStateClipping = Constants.eOuttakeStateClipHang.oOuttakeReset;
                    }
                case oOuttakeReset:
                    if(outtakeTimer.milliseconds() - OuttakeStartTime3 >= 1000){
                        robot.oArmHookgrab();
                        robot.oElevMove(Constants.eOElevatorState.Clip_Grab);

                        stickmoved1 = false;

                        outtakeStateClipping = Constants.eOuttakeStateClipHang.oOuttakeReady;
                    }
            }

//********** Intake ***************************************************

            if(gamepad2.left_trigger >=0.5) {
                robot.iWheelTakeBlock();
            }
            else {
                robot.iWheelNoBlock();
            }

//          Intake Version 3
//            if ((gamepad2.right_stick_y <= -0.2) && robot.iElevGetHeight() <= 2000) {
//                robot.iElevMove(Constants.eIElevatorState.ManualUp);
//            } else if (gamepad2.right_stick_y >= 0.2 && robot.iElevGetHeight() >= 0) {
//                robot.iElevMove(Constants.eIElevatorState.ManualDown);
//            } else {
//                robot.iElevMove(Constants.eIElevatorState.ManualStop);
//            }
//
//            if(gamepad2.dpad_down)//Arm_Hover
//                robot.iArmHover();
//            if(gamepad2.dpad_up)//Arm_Grab
//                robot.iArmGrab();
//
//            if(gamepad2.right_trigger >= 0.5 && !Triggerpressed) {
//                robot.iCloseClaw();
//                IntakeStartTime2 = (long) outtakeTimer.milliseconds();
//                Triggerpressed = true;
//            }
//            if(Triggerpressed && outtakeTimer.milliseconds() - IntakeStartTime2 >= 300)
//            {
//                robot.iArmHover();
//                Triggerpressed = false;
//            }
//
//            if(gamepad2.left_trigger >= 0.5)
//                robot.iOpenClaw();
//
//            if (gamepad2.right_stick_button && !bumperPressed) {
//                robot.iCloseClaw();
//                bumperPressed = true;
//                xOpenClawDone = false;
//                yArmstartDone=false;
//                yActionStartTime = (long) intakeTimer.milliseconds();
//            }
//            if (bumperPressed && !yArmstartDone && intakeTimer.milliseconds() - yActionStartTime >= 300) {
//                robot.iElevMove(Constants.eIElevatorState.InIntake);
//                robot.iArmStart();
//                yArmstartDone=true;
//            }
//            if (bumperPressed && yArmstartDone && !xOpenClawDone && intakeTimer.milliseconds() - yActionStartTime >= 1000) {
//                robot.iOpenClaw();
//                xOpenClawDone = true;
//            }
//            if (bumperPressed && xOpenClawDone && yArmstartDone && intakeTimer.milliseconds() - yActionStartTime >= 1500) {
//                robot.iArmHover();
//                bumperPressed = false;
//            }

//            if(gamepad2.square && !stickmoved1) {
//                robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
//                OuttakeStartTime3 = (long) outtakeTimer.milliseconds();
//                stickmoved1 = true;
//                OpenClawDone2 = false;
//            }
//            if(stickmoved1 && !OpenClawDone2 && outtakeTimer.milliseconds() - OuttakeStartTime3 >= 700) {
//                robot.oOpenClaw();
//                OpenClawDone2 = true;
//            }
//            if(stickmoved1 && OpenClawDone2 && outtakeTimer.milliseconds() - OuttakeStartTime3 >= 1000) {
//                robot.oArmHookgrab();
//                robot.oElevMove(Constants.eOElevatorState.Clip_Grab);
//                stickmoved1 = false;
//            }
        }
    }

    public void waitForSeconds(double seconds){
        ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        timer.reset();

        while(timer.time() < seconds){
        }
    }
}


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

//Grab + Claw_close + Up/Arm_dump
//            if(gamepad2.circle && !Outtakepressed1){
//                Outtakepressed1 = true;
//                OuttakeclawDone = false;
//                LinearSlidereadyDone = false;
//                OuttakeStartTime1 = (long) outtakeTimer.milliseconds();
//            }
//            if(Outtakepressed1 && !OuttakeclawDone && !LinearSlidereadyDone && outtakeTimer.milliseconds() - OuttakeStartTime1 >= 0){//400
//                OuttakeclawDone = true;
//            }
//            if(Outtakepressed1 &&  !LinearSlidereadyDone && OuttakeclawDone && outtakeTimer.milliseconds() - OuttakeStartTime1 >= 0){//700
//                robot.oCloseClaw();
//                LinearSlidereadyDone = true;
//            }
//            if(Outtakepressed1 && OuttakeclawDone && LinearSlidereadyDone && outtakeTimer.milliseconds() - OuttakeStartTime1 >= 400){//1200
//                robot.oArmDumpReady();
//                Outtakepressed1 = false;
//            }