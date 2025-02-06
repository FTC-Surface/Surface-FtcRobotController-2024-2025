package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import android.graphics.Color;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import com.qualcomm.hardware.limelightvision.LLResult;
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

        ElapsedTime intakeTimer, outtakeTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

        boolean xOpenClawDone, yOpenClawDone, yArmstartDone, yPressed, bumperPressed;
        xOpenClawDone = yOpenClawDone = yArmstartDone = yPressed = bumperPressed = false;

        long yActionStartTime = 0;

        long IntakeStartTime2=0;
        long OuttakeStartTime4=0;

        boolean Outtakepressed1, Triggerpressed, stickmoved2, OuttakeclawDone, ArmTakeDone,
                OpenClawDone, LinearSlidereadyDone, OpenClawDone2;

        Outtakepressed1 = Triggerpressed = stickmoved2 = OuttakeclawDone = ArmTakeDone =
        OpenClawDone = LinearSlidereadyDone = OpenClawDone2 = false;

        long OuttakeStartTime1=0;
        long OuttakeStartTime3=0;

        boolean stickmoved1 = false;
        boolean hasBlock = false;

        //Change depending on alliance color
        double r, g, b;

        //Change depending on alliance color
        Constants.eColorSensed eliminatedColor = Constants.eColorSensed.red;

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        robot.oElevMove(Constants.eOElevatorState.Ready);

        robot.iArmStart();

        robot.oOpenClaw();
        robot.oArmStart();

//        intakeTimer.reset();

        while (opModeIsActive() && !isStopRequested()) {
//********** Telemetry **********************************************************

            telemetry.addData("Intake Slide Pos: ", robot.iElevGetHeight());
            telemetry.addData("Outtake Slide Pos: ", robot.oElevGetHeight());

            telemetry.addData("Block Color: ", robot.getColorBlock());
            telemetry.addData("Has Block: ", hasBlock);

            telemetry.update();

            robot.iSlideLoop();
            robot.oSlideLoop();

//********** Player One Controls ***************************************************

            //Drive
            double drive = -gamepad1.left_stick_y;
            double strafe = -gamepad1.left_stick_x;
            double rotate = -gamepad1.right_stick_x;

            robot.teleOpDrive(drive * 0.8,strafe * 0.8,rotate * 0.7);

//          Outtake Linear Slides
            if(gamepad1.dpad_up) {robot.oElevMove(Constants.eOElevatorState.Basket);}
            if(gamepad1.dpad_down) robot.oElevMove(Constants.eOElevatorState.Ready);

//********** Player Two Controls ***************************************************

            //Outtake

            //Slide_down + Claw_close + Up/Arm_dump
            if(gamepad2.cross && !Outtakepressed1){
                Outtakepressed1 = true;
                OuttakeclawDone = false;
                LinearSlidereadyDone = false;
                OuttakeStartTime1 = (long) outtakeTimer.milliseconds();
                robot.oElevMove(Constants.eOElevatorState.Ground);
            }
            if(Outtakepressed1 && !OuttakeclawDone && !LinearSlidereadyDone && outtakeTimer.milliseconds() - OuttakeStartTime1 >= 0){//400
                OuttakeclawDone = true;
            }
            if(Outtakepressed1 &&  !LinearSlidereadyDone && OuttakeclawDone && outtakeTimer.milliseconds() - OuttakeStartTime1 >= 200){//700
                robot.oCloseClaw();
                LinearSlidereadyDone = true;
            }
            if(Outtakepressed1 && OuttakeclawDone && LinearSlidereadyDone && outtakeTimer.milliseconds() - OuttakeStartTime1 >= 500){//1200
                robot.oArmDumpReady();
                Outtakepressed1 = false;
            }
            
            //Arm_position
            if(gamepad2.triangle) {
                robot.oElevMove(Constants.eOElevatorState.Ready);
                robot.oOpenClaw();
                robot.oArmTake();
            }

            //Drop Sample
            if(gamepad2.circle) {
                robot.oArmDumpRelease();
                waitForSeconds(0.3);
                robot.oOpenClaw();
                waitForSeconds(0.3);
                robot.oArmTake();
            }

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

                robot.oArmHookup();

                robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
            }

            if(gamepad2.left_stick_y >= 0.5) {
                robot.oArmHookgrab();
                robot.oOpenClaw();
                robot.oElevMove(Constants.eOElevatorState.Ground);
            }

            if(gamepad2.left_stick_button)
            {
                robot.oOpenClaw();
                waitForSeconds(0.2);
                robot.oElevMove(Constants.eOElevatorState.Ground);
                robot.oArmHookgrab();
            }


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



//********** Intake ***************************************************

//            if(gamepad2.dpad_down)//Down
//            {
//                robot.iArmGrab();
//            }
//            if(gamepad2.dpad_up)//up
//            {
//                robot.iArmStart();
//            }
            if (gamepad2.right_stick_y <= -0.2) {
                robot.iElevMove(Constants.eIElevatorState.ManualForward);
            }
            else if (gamepad2.right_stick_y >= 0.2) {
                robot.iElevMove(Constants.eIElevatorState.ManualBackward);
            }
            else {
                robot.iElevMove(Constants.eIElevatorState.ManualStop);
            }

            if(gamepad2.square)
            {
                robot.iArmStart();
                waitForSeconds(0.2);
                robot.iElevMove(Constants.eIElevatorState.InIntake);
            }

            if(gamepad2.right_trigger >= 0.5 && robot.getColorResult() != eliminatedColor){//specifically for red
                robot.iWheelTakeBlock();
            }
            else if (gamepad2.dpad_left)
            {
                robot.iWheelTakeBlock();
            }
            else if (gamepad2.left_trigger >= 0.5)
            {
                robot.iWheelOutBlock();
            }
            else{
                robot.iWheelNoBlock();
            }

            if(robot.getColorResult() != Constants.eColorSensed.unknown){
                hasBlock = true;
            } else{
                hasBlock = false;
            }

//********** Controller Color ***************************************************

            if(robot.getColorResult() == Constants.eColorSensed.red){
                r = 255;
                g = b = 0;
            } else if(robot.getColorResult() == Constants.eColorSensed.blue){
                b = 255;
                r = g = 0;
            } else if(robot.getColorResult() == Constants.eColorSensed.yellow){
                r = g = 255;
                b = 0;
            } else {
                //set the color to green if there is no block
                r = 0;
                g = 255;
                b = 0;
            }

            gamepad1.setLedColor(r,g,b,1000);
            gamepad2.setLedColor(r,g,b,1000);
        }
    }

    public void waitForSeconds(double seconds){
        ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        timer.reset();

        while(timer.time() < seconds){
        }
    }
}