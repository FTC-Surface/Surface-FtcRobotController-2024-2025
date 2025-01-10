package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Timer;

@TeleOp(name="Intake Active Intake Test", group = "Tests")
@Config
public class IntakeActiveIntakeTest extends LinearOpMode {

    private CRServo rIntake;
    private CRServo lIntake;

    public static double targetRight = 0;
    public static double targetLeft = 0;

    ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
         lIntake = hardwareMap.get(CRServo.class, "lIntake");
         rIntake = hardwareMap.get(CRServo.class, "rIntake");

         lIntake.setDirection(CRServo.Direction.REVERSE);

         waitForStart();

         while(opModeIsActive() && !isStopRequested()) {
             rIntake.setPower(targetRight);
             lIntake.setPower(targetLeft);

             if(gamepad1.a){
                 targetRight = 1;
                 targetLeft = -1;
             }

             if(gamepad1.b){
                 targetRight = -1;
                 targetLeft = 1;
             }

             if(gamepad1.y){
                 targetRight = 0;
                 targetLeft = 0;
             }
         }
    }

}
