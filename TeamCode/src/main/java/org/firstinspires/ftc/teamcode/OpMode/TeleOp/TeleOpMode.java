package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

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

//        boolean xOpenClawDone = false;
//        boolean yOpenClawDone = false;
//        boolean yArmstartDone = false;
//        boolean yPressed = false;
//        boolean bumperPressed = false;
//        long yActionStartTime = 0;

//        long IntakeStartTime2=0;
//        long OuttakeStartTime4=0;

//        boolean Outtakepressed1 = false;
//        boolean Triggerpressed = false;
//        boolean stickmoved2 = false;
//        boolean OuttakeclawDone = false;
//        boolean ArmTakeDone = false;
//        boolean OpenClawDone = false;
//        boolean LinearSlidereadyDone = false;
//        boolean OpenClawDone2 = false;

        ElapsedTime intakeTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        ElapsedTime outtakeTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

        long OuttakeStartTime1=0;
        long OuttakeStartTime3=0;

        boolean stickmoved1 = false;

        Constants.eOuttakeStateClipHang outtakeStateClipping = Constants.eOuttakeStateClipHang.oOuttakeReady;
        Constants.eOuttakeStateGrabDump outtakeStateGrabbing = Constants.eOuttakeStateGrabDump.oOuttakeReady;

        int cameraIndex = 0;

        double blockXAngle = 0;
        double blockYAngle = 0;

        double cameraHeight = 0 - 1.5; //Need to input value. This is in inches.
        double theta = 45 + blockYAngle;
        int distanceToMove = 0;

        robot.limelightStart(cameraIndex);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        robot.oElevMove(Constants.eOElevatorState.Ready);

//        robot.iArmHover();
//        robot.iOpenClaw();

        robot.oOpenClaw();
        robot.oArmStart();

        intakeTimer.reset();

        while (opModeIsActive() && !isStopRequested()) {

//********** Telemetry **********************************************************

            //Remove this if robot starts crashing

            telemetry.addData("Block Y Angle: ", blockYAngle);
            telemetry.addData("Block X Angle: ", blockXAngle);
            telemetry.addData("Theta: ", theta);
            telemetry.addData("DistanceToMove: ", distanceToMove);

            telemetry.addData("Intake Slide Pos: ", robot.iElevGetHeight());
            telemetry.addData("Outtake Slide Pos: ", robot.oElevGetHeight());

            telemetry.update();

//********** Limelight Camera ***************************************************

            LLResult result = robot.limeLightGetResult();

            if(result != null){
                if(result.isValid()){
                    if(gamepad2.square){
                        blockYAngle = result.getTy();
                        blockXAngle = result.getTx();
                    }
                }
            } else {
                blockYAngle = 0;
                blockXAngle = 0;
            }

            theta = 45 + blockYAngle;

            distanceToMove = (int) (Math.tan(theta) * cameraHeight);

//********** Player One Controls ***************************************************

            //Drive
            double drive = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            robot.teleOpDrive(drive * 0.8,strafe * 0.8,rotate * 0.7);

//          Outtake Linear Slides
            if(gamepad1.dpad_up) robot.oElevMove(Constants.eOElevatorState.Basket);
//            if(gamepad1.dpad_down) robot.oElevMove(Constants.eOElevatorState.Ready);

//********** Player Two Controls ***************************************************

//********** Outtake ***************************************************

            //Jack you can remove and revert this part if u want.

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

            //Jack you can remove and revert this part if u want.

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

            //Moved this to CommentedOutTeleOpStuff in ArchivedCode folder. Move it back if u want.
        }
    }

    public void waitForSeconds(double seconds){
        ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        timer.reset();

        while(timer.time() < seconds){
        }
    }
}

//commented out code that was put here has been moved to CommentedOutTeleOpStuff in ArchivedCode