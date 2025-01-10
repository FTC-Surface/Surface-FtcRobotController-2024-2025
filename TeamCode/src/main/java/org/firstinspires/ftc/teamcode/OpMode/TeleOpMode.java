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

            if(gamepad1.a){
                intakeTimer.reset();
                robot.intakeIn();
            }

            if(gamepad1.b){
                intakeTimer.reset();
                robot.intakeOut();
            }

            if(intakeTimer.milliseconds() >= 1000 || gamepad2.y){
                robot.intakeStop();
            }
        }
    }
}
