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
        //ElapsedTime intakeTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        Constants constants = new Constants();


        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        telemetry.update();

        //robot.iArmStart();
        //robot.oArmStart();
        //robot.openClaw();
        //robot.oElevMove(Constants.eOElevatorState.Ground,0);

        while (opModeIsActive() && !isStopRequested()) {

            //robot.oSlideLoop();

            double drive = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            robot.teleOpDrive(drive * 0.6,strafe * 0.6,rotate * 0.6);

            if(gamepad2.a)
            {
                robot.oArmOut();
            }
            if(gamepad2.b)
            {
                robot.oArmStart();
            }

            if(gamepad1.a){
                robot.oElevMove(Constants.eOElevatorState.Basket, 0);
            }

            if(gamepad1.b){
                robot.oElevMove(Constants.eOElevatorState.Ground, 0);
            }
            if(gamepad2.x)//Down
            {
                robot.iArmGrab();
            }
            if(gamepad2.y)//up
            {
                robot.iArmStart();
            }
            if(gamepad2.left_bumper)//Open
            {
                robot.InopenClaw();
            }
            if(gamepad2.right_bumper)//Close
            {
                robot.IncloseClaw();
            }

//            height = robot.oElevGetHeight();
//            if(gamepad2.left_trigger >0.5){
//                robot.iArmStart();
//            }
//            if(gamepad2.right_trigger >0.5){
//                robot.iArmMove(0.5);
//            }

            if(gamepad2.y){
                robot.oElevMove(Constants.eOElevatorState.Ground, 0);
            }

            if(gamepad1.x){
                robot.oElevMove(Constants.eOElevatorState.Ground, 0);
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
//            if(intakeTimer.milliseconds() >= 1000 || gamepad1.y || !gamepad2.left_bumper || !gamepad2.right_bumper){
//                robot.intakeStop();
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
