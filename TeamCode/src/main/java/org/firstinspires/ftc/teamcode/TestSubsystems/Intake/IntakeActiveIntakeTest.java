package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Intake Active Intake Test")
@Config
public class IntakeActiveIntakeTest extends LinearOpMode {

    Servo lIntake;
    Servo rIntake;

    public static double targetRight = 0.3;
    public static double targetLeft = 0.415;

    @Override
    public void runOpMode() throws InterruptedException {
        lIntake = hardwareMap.get(Servo.class, "lIntake");
        rIntake = hardwareMap.get((Servo.class), "rIntake");

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){
            rIntake.setPosition(targetRight);
            lIntake.setPosition(targetLeft);
        }
    }

}
