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

    private enum eIntakeState{
        iIntakeReady,
        iCloseClaw,
        iStartArm,
        iOpenClaw,
        iResetArm
    }

    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot(hardwareMap);
        ElapsedTime intakeTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        Constants constants = new Constants();

        boolean xPressed = false;
        long xActionStartTime = 0;
        boolean xOpenClawDone = false;
        boolean yOpenClawDone = false;
        boolean yArmstartDown=false;
        boolean yPressed = false;
        long yActionStartTime = 0;

        eIntakeState intakeState = eIntakeState.iIntakeReady;

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        waitForStart();
        telemetry.update();

        robot.iArmHover();
        robot.oArmStart();
        robot.iOpenClaw();
        intakeTimer.reset();
        //robot.oElevMove(Constants.eOElevatorState.Ground,0);

        while (opModeIsActive() && !isStopRequested()) {

            //telemetry.addData("Outtake Elev Height", robot.oElevGetHeight());
            //telemetry.addData("Outtake Elev Is Busy", robot.oElevIsBusy());
//            telemetry.addData("Outtake Elev Get Power", robot.oElevGetPower());
//
            telemetry.update();


            //robot.oSlideLoop();

            double drive = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;
            // Define these at the class level (or in a suitable scope)


            robot.teleOpDrive(drive * 0.6,strafe * 0.6,rotate * 0.6);

            if(gamepad1.x){
                robot.oElevMove(Constants.eOElevatorState.Basket, 0);
            }

            if(gamepad1.y){
                robot.oElevMove(Constants.eOElevatorState.Ground, 0);
            }

//            if(gamepad1.y){
//                robot.oElevMove(Constants.eOElevatorState.Ground, 0);
//            }
//
//            if(gamepad1.x){
//                robot.oElevMove(Constants.eOElevatorState.Ground, 0);
//            }

            //Player Two Controls
            //Outtake
            if(gamepad2.a)
                robot.oArmOut();
            if(gamepad2.b)
                robot.oArmStart();

            //if(gamepad2.left_trigger>0.5)

            switch(intakeState){
                case iIntakeReady:
                    if (gamepad2.y) {
                        yPressed = true;
                        xOpenClawDone = false;
                        yActionStartTime = (long) intakeTimer.milliseconds();
                        robot.iArmGrab();
                        intakeState = eIntakeState.iCloseClaw;
                    }
                    break;

                case iCloseClaw:
                    if(intakeTimer.milliseconds() - yActionStartTime >= 100){
                        robot.iCloseClaw();
                        intakeState = eIntakeState.iStartArm;
                    }
                    break;

                case iStartArm:
                    if (intakeTimer.milliseconds() - yActionStartTime >= 500) {
                        // Reset flag for the open claw action
                        robot.iArmStart();
                        intakeState = eIntakeState.iOpenClaw;
                    }
                    break;

                case iOpenClaw:
                    if(intakeTimer.milliseconds() - yActionStartTime >= 2500){
                        robot.iOpenClaw();
                        xOpenClawDone = true;
                        intakeState = eIntakeState.iResetArm;
                    }
                    break;

                case iResetArm:
                    if(intakeTimer.milliseconds() - yActionStartTime >= 3000){
                        robot.iArmHover();
                        yPressed = false;
                        intakeState = eIntakeState.iIntakeReady;
                    }
                    break;
            }

            //(Down+Up)
            if (gamepad2.y && !yPressed) {
                yPressed = true;
                xOpenClawDone = false;
                yOpenClawDone=false;
                yArmstartDown=true;
                yActionStartTime = (long) intakeTimer.milliseconds();
                robot.iArmGrab();
            }

            if (yPressed && !yOpenClawDone && intakeTimer.milliseconds() - yActionStartTime >= 100) {
                robot.iCloseClaw();
                yOpenClawDone=true;
            }
            if (yPressed && yOpenClawDone && !yArmstartDown && intakeTimer.milliseconds() - yActionStartTime >= 500) {
                 // Reset flag for the open claw action
                robot.iArmStart();
                yArmstartDown=true;
            }
            if (yPressed && !xOpenClawDone && yArmstartDown && yOpenClawDone && intakeTimer.milliseconds() - yActionStartTime >= 1000) {
                robot.iOpenClaw();
                xOpenClawDone = true;
            }
            if (yPressed && xOpenClawDone && yOpenClawDone && intakeTimer.milliseconds() - yActionStartTime >= 1500) {
                robot.iArmHover();
                yPressed = false;
            }


            if(gamepad2.left_bumper)//Open
            {
                robot.iOpenClaw();
            }
            if(gamepad2.right_bumper)//Close
            {
                robot.iCloseClaw();
            }

//            height = robot.oElevGetHeight();
//            if(gamepad2.left_trigger >0.5){
//                robot.iArmStart();
//            }
//            if(gamepad2.right_trigger >0.5){
//                robot.iArmMove(0.5);
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
