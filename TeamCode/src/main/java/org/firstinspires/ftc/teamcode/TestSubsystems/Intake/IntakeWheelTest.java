package org.firstinspires.ftc.teamcode.TestSubsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;

@TeleOp(name = "Intake Wheel Test", group = "Tests Intake")
@Config
public class IntakeWheelTest extends LinearOpMode{

    private DcMotorEx wheel;

    public static double motorPow = 0;

    public static int activateTestMode = 0;

    private ColorSensor colorSensor;

    private Servo armServo;

    private char color;


    public int colorRed(){return colorSensor.red();}
    public int colorGreen(){return colorSensor.green();}
    public int colorBlue(){return colorSensor.blue();}
    public int alpha(){
        return colorSensor.alpha();
    }
    @Override
    public void runOpMode() throws InterruptedException {

        wheel = hardwareMap.get(DcMotorEx.class, "IntakeWheel");
        wheel.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");

        armServo = hardwareMap.get(Servo.class,"iArm");

        waitForStart();

        while(opModeIsActive() && !isStopRequested()){
            if(activateTestMode == 0){
                wheel.setPower(motorPow);
            }

            if(activateTestMode == 1){

                if (colorRed() > colorGreen() && colorRed() > colorBlue()) {
                    color='r';
                } else if (colorGreen() > colorRed() && colorGreen() > colorBlue() && colorGreen()>=200) {
                    color='y';
                } else if (colorBlue() > colorRed() && colorBlue() > colorGreen()) {
                    color='b';
                } else {
                    color='n';
                }
                if(gamepad2.right_trigger >=0.5 && color!='r'){//specifically for red
                    wheel.setPower(motorPow);
                }
                else if (gamepad2.dpad_left)
                {
                    wheel.setPower(motorPow);
                }
                else if (gamepad2.left_trigger >=0.5)
                {
                    wheel.setPower(-motorPow);
                }
                else{
                    wheel.setPower(0);
                }
                if(gamepad2.dpad_down)//Down
                {
                    armServo.setPosition(0);
                }
                if(gamepad2.dpad_up)//up
                {
                    armServo.setPosition(0.435);
                }
            }
        }
    }
}
