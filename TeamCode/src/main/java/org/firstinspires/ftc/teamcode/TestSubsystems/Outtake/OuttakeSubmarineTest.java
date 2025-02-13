package org.firstinspires.ftc.teamcode.TestSubsystems.Outtake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Outtake Sub", group = "Tests Outtake")
@Config
public class OuttakeSubmarineTest extends LinearOpMode {

    Servo Arm;
    Servo Wrist;
    Servo Claw;
    Servo Sub;
    Servo SpeciArm;

    public static double ArmTarget = 0;
    public static double WristTarget = 0;
    public static double ClawTarget = 0.3;
    public static double SubTarget = 0;
    public static double SpeciArmTarget = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        Arm = hardwareMap.get(Servo.class, "Outtake Arm Left");
        Wrist = hardwareMap.get(Servo.class, "Outtake Wrist Right");
        Wrist.setDirection(Servo.Direction.REVERSE);
        Claw = hardwareMap.get(Servo.class, "OClaw");
        Sub = hardwareMap.get(Servo.class, "Outtake Sub");
        SpeciArm = hardwareMap.get(Servo.class, "Speci Arm");

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){
            Arm.setPosition(ArmTarget);
            Wrist.setPosition(WristTarget);
            Claw.setPosition((ClawTarget));
            Sub.setPosition(SubTarget);
            SpeciArm.setPosition(SpeciArmTarget);
        }
    }
}
