package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Intake Active Intake Test", group = "Tests")
@Config
public class IntakeActiveIntakeTest extends LinearOpMode {

    private CRServo rIntake;
    private CRServo lIntake;

    public static double targetRight = 0;
    public static double targetLeft = 0;

    public static double reverseRight = 1;
    public static double reverseLeft = 1;

    @Override
    public void runOpMode() throws InterruptedException {
         lIntake = hardwareMap.get(CRServo.class, "lIntake");
         rIntake = hardwareMap.get(CRServo.class, "rIntake");

        ElapsedTime timer = new ElapsedTime();

         waitForStart();

         while(opModeIsActive() && !isStopRequested()) {
             rIntake.setPower(targetRight);
             lIntake.setPower(targetLeft);

             //Intake
             if(gamepad1.a){
                 timer.reset();

                 targetRight = -0.3;
                 targetLeft = -0.3;
             }

             //Outtake
             if(gamepad1.b){
                 timer.reset();

                 targetRight = 0.3 * reverseRight;
                 targetLeft = 0.3 * reverseLeft;
             }

             //Stop 
             if(gamepad1.y){
                 targetRight = 0;
                 targetLeft = 0;
             }

             if(timer.milliseconds() >= 1000){
                 targetRight = 0;
                 targetLeft = 0;
             }
         }
    }

}
