package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

import com.qualcomm.robotcore.hardware.Servo;

public class IntakeWristTest extends LinearOpMode {

    Servo lWrist;
    Servo rWrist;

    @Override
    public void runOpMode() throws InterruptedException {
        lWrist = hardwareMap.get(Servo.class, "lWrist");
        rWrist = hardwareMap.get((Servo.class), "rWrist");
    }
}
