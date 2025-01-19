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
        long IntakeStartTime2=0;
        long OuttakeStartTime3=0;
        long OuttakeStartTime4=0;
        boolean Outtakepressed1 = false;
        boolean Triggerpressed = false;
        boolean stickmoved1 = false;
        boolean stickmoved2 = false;
        boolean OuttakeclawDone = false;
        boolean ArmTakeDone = false;
        boolean OpenClawDone = false;
        boolean LinearSlidereadyDone = false;

        Constants.eIntakeState intakeState = Constants.eIntakeState.iIntakeReady;

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        robot.oElevMove(Constants.eOElevatorState.Ready);

        robot.iArmHover();
        robot.iOpenClaw();

        robot.oOpenClaw();
        robot.oArmStart();

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

            robot.teleOpDrive(drive * 0.9,strafe * 0.9,rotate * 0.6);

//          Outtake Linear Slides
            if(gamepad1.dpad_up)
                robot.oElevMove(Constants.eOElevatorState.Basket);

//            if(gamepad1.dpad_down)
//                robot.oElevMove(Constants.eOElevatorState.Ready);

//********** Player Two Controls ***************************************************

//********** Outtake ***************************************************

            //Grab + Claw_close + Up/Arm_dump
            if(gamepad2.circle && !Outtakepressed1){
                robot.oElevMove(Constants.eOElevatorState.Grab);
                Outtakepressed1 = true;
                OuttakeclawDone = false;
                LinearSlidereadyDone = false;
                OuttakeStartTime1 = (long) outtakeTimer.milliseconds();
            }
            if(Outtakepressed1 && !OuttakeclawDone && !LinearSlidereadyDone && outtakeTimer.milliseconds() - OuttakeStartTime1 >= 200){//400
                robot.oCloseClaw();
                OuttakeclawDone = true;
            }
            if(Outtakepressed1 &&  !LinearSlidereadyDone && OuttakeclawDone && outtakeTimer.milliseconds() - OuttakeStartTime1 >= 700){//700
                robot.oElevMove(Constants.eOElevatorState.Ready);
                LinearSlidereadyDone = true;
            }
            if(Outtakepressed1 && OuttakeclawDone && LinearSlidereadyDone && outtakeTimer.milliseconds() - OuttakeStartTime1 >= 1200){//1200
                robot.oArmDump();
                Outtakepressed1 = false;
            }

            //Arm_position
            //if(gamepad2.triangle) {
                //robot.oOpenClaw();
                //robot.oElevMove(Constants.eOElevatorState.Ready);
                //robot.oArmTake();
            //}

            while(gamepad2.triangle) {
                robot.iWheelTakeBlock();
            }

            if(!gamepad2.triangle) {
                robot.iWheelNoBlock();
            }

            if(gamepad2.cross)
                robot.oArmDump();

            //Outtake_claw
            if(gamepad2.right_bumper)
                robot.oCloseClaw();
            if(gamepad2.left_bumper)
                robot.oOpenClaw();


            //Hook
            if(gamepad2.left_stick_y >= 0.5 && !stickmoved1) {
                robot.oArmHookgrab();
                robot.oOpenClaw();
                OuttakeStartTime3 = (long) outtakeTimer.milliseconds();
                stickmoved1 = true;
            }
            if(stickmoved1 && outtakeTimer.milliseconds() - OuttakeStartTime3 >= 500) {
                robot.oElevMove(Constants.eOElevatorState.Clip_Grab);
                stickmoved1 = false;
            }

            if(gamepad2.left_stick_y <=-0.5) {
                robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
                robot.oArmHookstart();
            }
            if(gamepad2.square) {
                robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
                robot.oArmHookup();
            }




                

//********** Intake ***************************************************

//          Intake Version 2
//            if ((gamepad2.right_stick_y <= -0.2) && robot.iElevGetHeight() <= 2000) {
//                robot.iElevMove(Constants.eIElevatorState.ManualUp);
//            } else if (gamepad2.right_stick_y >= 0.2 && robot.iElevGetHeight() >= 0) {
//                robot.iElevMove(Constants.eIElevatorState.ManualDown);
//            } else {
//                robot.iElevMove(Constants.eIElevatorState.ManualStop);
//            }

            if(gamepad2.dpad_down)//Arm_Hover
                robot.iArmHover();
            if(gamepad2.dpad_up)//Arm_Grab
                robot.iArmGrab();
            
            if(gamepad2.right_trigger >= 0.5 && !Triggerpressed) {
                robot.iCloseClaw();
                IntakeStartTime2 = (long) outtakeTimer.milliseconds();
                Triggerpressed = true;
            }
            if(Triggerpressed && outtakeTimer.milliseconds() - IntakeStartTime2 >= 300)
            {
                robot.iArmHover();
                Triggerpressed = false;
            }

            if(gamepad2.left_trigger >= 0.5)
                robot.iOpenClaw();



            if (gamepad2.right_stick_button && !bumperPressed) {
                robot.iCloseClaw();
                bumperPressed = true;
                xOpenClawDone = false;
                yArmstartDone=false;
                yActionStartTime = (long) intakeTimer.milliseconds();
            }
            if (bumperPressed && !yArmstartDone && intakeTimer.milliseconds() - yActionStartTime >= 300) {
                robot.iElevMove(Constants.eIElevatorState.InIntake);
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
