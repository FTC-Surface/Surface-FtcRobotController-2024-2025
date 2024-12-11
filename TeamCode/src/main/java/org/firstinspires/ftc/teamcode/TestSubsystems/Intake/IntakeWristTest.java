package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake Wrists Test", group = "Tests")
@Config
public class IntakeWristTest extends LinearOpMode {

    Servo lWrist;
    Servo rWrist;

    @Override
    public void runOpMode() throws InterruptedException {
        lWrist = hardwareMap.get(Servo.class, "lWrist");
        rWrist = hardwareMap.get((Servo.class), "rWrist");
    }
}
