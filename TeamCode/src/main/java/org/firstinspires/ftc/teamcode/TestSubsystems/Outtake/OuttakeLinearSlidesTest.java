package org.firstinspires.ftc.teamcode.TestSubsystems.Outtake;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import kotlin.reflect.KParameter;

@TeleOp(name = "Outtake Slides Test Op", group = "Tests Outtake")
@Config
public class OuttakeLinearSlidesTest extends LinearOpMode {

    private DcMotorEx leftOuttakeMotor;

    public static int targetPos = 0;
    public static int maxHeight = 2100;
    public static int minHeight = 0;
    public static double KP = 0.05;
    public static double KD = 0;
    public static double KI = 0;

    public int currentHeight = 0;

    public static double motorPower = 1;

    public class PIDController {

        double p,i,d;
        double lastError=0;
        double integralSum=0;
        ElapsedTime timer = new ElapsedTime();
        /**
         * construct PID controller
         * @param Kp Proportional coefficient
         * @param Ki Integral coefficient
         * @param Kd Derivative coefficient
         */
        public PIDController(double Kp, double Ki, double Kd) {
            p=Kp;
            i=Ki;
            d=Kd;
        }

        /**
         * update the PID controller output
         * @param target where we would like to be, also called the reference
         * @param state where we currently are, I.E. motor position
         * @return the command to our motor, I.E. motor power
         */
        public double update(double target, double state) {
            // PID logic and then return the output
            // obtain the encoder position
            // calculate the error
            double error = target - state;

            // rate of change of the error
            double derivative = (error - lastError) / timer.seconds();

            // sum of all error over time
            integralSum = integralSum + (error * timer.seconds());

            double out = (p * error) + (i * integralSum) + (d * derivative);

            lastError = error;

            // reset the timer for next time
            timer.reset();

            return out;
        }
    }

    PIDController control = new PIDController(KP,KI,KD);
    
    public void runOpMode() {
        leftOuttakeMotor = hardwareMap.get(DcMotorEx.class, "outtakeLinearSlideOne");

        leftOuttakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftOuttakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        leftOuttakeMotor.setDirection(DcMotorEx.Direction.REVERSE);

        leftOuttakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        telemetry.addData("Height", currentHeight);

        while (opModeIsActive()) {
            if (targetPos >= maxHeight)
                targetPos = maxHeight;
            else if (targetPos <= minHeight)
                targetPos = minHeight;

            telemetry.addData("Left Motor Position", leftOuttakeMotor.getCurrentPosition());
            telemetry.addData("Height", currentHeight);
            telemetry.addData("Is Busy", isBusy());

            telemetry.update();

            double Power = control.update(targetPos, leftOuttakeMotor.getCurrentPosition());
            // assign motor the PID output
            leftOuttakeMotor.setPower(Power);

//            leftOuttakeMotor.setTargetPosition(targetPos);
//
//            leftOuttakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            if (currentHeight > targetPos) {
//                leftOuttakeMotor.setPower(-motorPower);
//            }
//
//            if (currentHeight < targetPos) {
//                leftOuttakeMotor.setPower(motorPower);
//            }
//
//            currentHeight = leftOuttakeMotor.getCurrentPosition();

        }
    }

    public boolean isBusy(){return Math.abs(currentHeight-targetPos) > 10;}

    public void setPowerZero(){
        leftOuttakeMotor.setPower(0);
    }
}