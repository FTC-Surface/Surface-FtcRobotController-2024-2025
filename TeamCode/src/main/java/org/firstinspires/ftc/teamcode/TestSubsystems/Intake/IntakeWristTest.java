package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake Wrists Test", group = "Tests")
@Config
public class IntakeWristTest extends LinearOpMode {

    Servo lWrist;
    Servo rWrist;

    public static double lPos;
    public static double rPos;

    @Override
    public void runOpMode() throws InterruptedException {
        lWrist = hardwareMap.get(Servo.class, "lWrist");
        rWrist = hardwareMap.get(Servo.class, "rWrist");

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){
            if(gamepad1.a){
                lWrist.setPosition(lPos);
                rWrist.setPosition(rPos);
            }

            lWrist.setPosition(0.5);
            rWrist.setPosition(0.5);
        }
    }
}
