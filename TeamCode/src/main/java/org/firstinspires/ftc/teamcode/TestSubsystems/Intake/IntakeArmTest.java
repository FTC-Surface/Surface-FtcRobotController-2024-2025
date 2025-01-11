package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake Arm Test", group = "Tests")
@Config
public class IntakeArmTest extends LinearOpMode{

    private Servo armServo;

    private Servo lWrist;
    private Servo rWrist;
    public static double lPos;
    public static double rPos;

    public static double position;

    @Override
    public void runOpMode() throws InterruptedException {
        armServo = hardwareMap.get(Servo.class,"testServo");

        lWrist = hardwareMap.get(Servo.class, "lWrist");
        rWrist = hardwareMap.get(Servo.class, "rWrist");

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){
            armServo.setPosition(position);
            
            lWrist.setPosition(lPos);
            rWrist.setPosition(lPos);
        }
    }
}
