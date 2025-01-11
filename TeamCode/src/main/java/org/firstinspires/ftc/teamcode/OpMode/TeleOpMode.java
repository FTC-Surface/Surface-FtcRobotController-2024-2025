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

    public int inputHeight = 0;

    public enum eDropOff{
        DropOff_start
    }

    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot(hardwareMap);
        ElapsedTime intakeTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        Constants constants = new Constants();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        telemetry.update();

        robot.iArmStart();
        robot.oArmStart();
        robot.oWristStart();
        robot.openClaw();
        robot.oElevMove(Constants.eOElevatorState.Ground);

        eDropOff eDropOffState = eDropOff.DropOff_start;

        while (opModeIsActive()) {

            robot.oSlideLoop();
            robot.iSlideLoop();

            double drive = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            robot.teleOpDrive(drive * 0.6,strafe * 0.6,rotate * 0.6);

            if(gamepad2.left_bumper){
                intakeTimer.reset();
                robot.intakeIn();
            }

            if(gamepad2.right_bumper){
                intakeTimer.reset();
                robot.intakeOut();
            }

            if(intakeTimer.milliseconds() >= 1000 || gamepad1.y){
                robot.intakeStop();
            }

            if(gamepad2.a){
                robot.oElevMove(Constants.eOElevatorState.Ready);
                robot.oWristReady();
                robot.oArmReady();
            }

            if(gamepad2.b){
                robot.oElevMove(Constants.eOElevatorState.Grab);

                if(!robot.oElevIsBusy()){
                    robot.closeClaw();
                }
            }

            if(gamepad2.x){
                robot.oElevMove(Constants.eOElevatorState.Basket);
                robot.oWristOut();
                robot.oArmOut();

                if(!robot.oElevIsBusy()){
                    robot.openClaw();
                }
            }

            if(gamepad2.y){
                robot.oElevMove(Constants.eOElevatorState.Ground);
                robot.oArmStart();
                robot.oWristStart();
                robot.closeClaw();
            }

//            if(gamepad2.dpad_down){
//                robot.oElevMove(Constants.eOElevatorState.Ground);
//            }
//            if(gamepad2.dpad_up){
//                robot.oElevMove(Constants.eOElevatorState.Basket);
//            }
//            if(gamepad2.dpad_left){
//                robot.oElevMove(Constants.eOElevatorState.Ready);
//            }
//            if(gamepad2.dpad_right){
//                robot.oElevMove(Constants.eOElevatorState.Grab);
//            }
        }
    }
}
