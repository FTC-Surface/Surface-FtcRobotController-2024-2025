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
        Constants constants = new Constants();

        boolean xOpenClawDone = false;
        boolean yOpenClawDone = false;
        boolean yArmstartDone = false;
        boolean yPressed = false;
        boolean bumperPressed = false;
        long yActionStartTime = 0;

        Constants.eIntakeState intakeState = Constants.eIntakeState.iIntakeReady;

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        robot.iArmHover();
        robot.iOpenClaw();

        robot.oArmStart();
        robot.oOpenClaw();
        intakeTimer.reset();
        robot.oElevMove(Constants.eOElevatorState.Ready,0);

        while (opModeIsActive() && !isStopRequested()) {

//            telemetry.addData("Outtake Elev Height", robot.oElevGetHeight());
//            telemetry.addData("Outtake Elev Is Busy", robot.oElevIsBusy());
//            height = robot.oElevGetHeight();
//            telemetry.update();
//            robot.oSlideLoop();

//********** Player One Controls ***************************************************

            //Drive
            double drive = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            robot.teleOpDrive(drive * 0.6,strafe * 0.6,rotate * 0.6);

            //Outtake Linear Slides
            if(gamepad1.x)
                robot.oElevMove(Constants.eOElevatorState.Basket, 0);

            if(gamepad1.y)
                robot.oElevMove(Constants.eOElevatorState.Ready, 0);

            if(gamepad1.a)
                robot.oElevMove(Constants.eOElevatorState.Grab, 0);

//********** Player Two Controls ***************************************************

//********** Outtake ***************************************************
            //Basket
            if(gamepad2.a)
                robot.oArmDump();

            //Take
            if(gamepad2.b)
                robot.oArmTake();

            //Hook
            if(gamepad2.y)
                robot.oArmHookstart();
            if(gamepad2.x)
                robot.oArmHookup();

            //Claw
            if(gamepad2.left_trigger>0.5)
                robot.oOpenClaw();
            if(gamepad2.right_trigger>0.5)
                robot.oCloseClaw();


//********** Intake ***************************************************

//          Intake Version 1 (Down+Up)
            if (gamepad2.y && !yPressed) {
                yPressed = true;
                xOpenClawDone = false;
                yOpenClawDone=false;
                yArmstartDone=false;
                yActionStartTime = (long) intakeTimer.milliseconds();
                robot.iArmGrab();
            }

            if (yPressed && !yOpenClawDone && intakeTimer.milliseconds() - yActionStartTime >= 100) {
                robot.iCloseClaw();
                yOpenClawDone=true;
            }
            if (yPressed && yOpenClawDone && !yArmstartDone && intakeTimer.milliseconds() - yActionStartTime >= 600) {
                 // Reset flag for the open claw action
                robot.iArmStart();
                yArmstartDone=true;
            }
            if (yPressed && !xOpenClawDone && yArmstartDone && yOpenClawDone && intakeTimer.milliseconds() - yActionStartTime >= 1000) {
                robot.iOpenClaw();
                xOpenClawDone = true;
            }
            if (yPressed && xOpenClawDone && yArmstartDone && yOpenClawDone && intakeTimer.milliseconds() - yActionStartTime >= 1500) {
                robot.iArmHover();
                yPressed = false;
            }

//          Intake Version 2

            if(gamepad2.left_bumper)//Arm_Down
                robot.iArmGrab();

            if (gamepad2.right_bumper && !bumperPressed) {
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
            if (bumperPressed && xOpenClawDone && yOpenClawDone && intakeTimer.milliseconds() - yActionStartTime >= 1500) {
                robot.iArmHover();
                bumperPressed = false;
            }
        }
    }

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

    public void waitForSeconds(double seconds){
        ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        timer.reset();

        while(timer.time() < seconds){
        }
    }
}
