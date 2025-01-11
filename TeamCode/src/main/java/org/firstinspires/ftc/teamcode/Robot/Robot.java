package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.util.ElapsedTime;

//import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeActiveIntake;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeLinearSlides;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeWrist;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeArm;

import org.firstinspires.ftc.teamcode.Subsystems.Outtake.OuttakeArm;
import org.firstinspires.ftc.teamcode.Subsystems.Outtake.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Subsystems.Outtake.OuttakeLinearSlides;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;

public class Robot {
    DcMotorEx topLeftMotor, topRightMotor, bottomLeftMotor, bottomRightMotor;

    OuttakeArm oArm = new OuttakeArm();
    OuttakeLinearSlides oSlides = new OuttakeLinearSlides();
    OuttakeClaw oClaw = new OuttakeClaw();

    IntakeArm iArm = new IntakeArm();
    IntakeWrist iWrist = new IntakeWrist();
//    IntakeActiveIntake iIntake = new IntakeActiveIntake();
    IntakeLinearSlides iSlides = new IntakeLinearSlides();

    Constants constants = new Constants();

    double maxSpeed;

    public Robot(HardwareMap hardwareMap){
        topLeftMotor = hardwareMap.get(DcMotorEx.class, "topLeft");
        topRightMotor = hardwareMap.get(DcMotorEx.class, "topRight");
        bottomLeftMotor = hardwareMap.get(DcMotorEx.class, "bottomLeft");
        bottomRightMotor = hardwareMap.get(DcMotorEx.class, "bottomRight");

//        Might Turn On
        topLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        topRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bottomLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bottomRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        topRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        bottomRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

//        oClaw.init(hardwareMap);
        oArm.init(hardwareMap);
        oSlides.init(hardwareMap);

//
//        iIntake.init(hardwareMap);
//        iSlides.init((hardwareMap));
//        iWrist.init(hardwareMap);
//        iArm.init(hardwareMap);

        for (LynxModule module : hardwareMap.getAll(LynxModule.class)) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
    }

    public void teleOpDrive(double drive, double strafe, double rotate){

        double[] motorPower = {
                drive-strafe-rotate,
                drive+strafe-rotate,
                drive+strafe+rotate,
                drive-strafe+rotate};

        maxSpeed = Math.abs(motorPower[0]);

        for (double power : motorPower){
            if(Math.abs(power) > maxSpeed){
                maxSpeed = Math.abs(power);
            }
        }

        if(maxSpeed > 1){
            for (int i = 0; i < motorPower.length; i++){
                motorPower[i] /= maxSpeed;
            }
        }

        topLeftMotor.setPower(motorPower[0]);
        bottomLeftMotor.setPower(motorPower[1]);
        topRightMotor.setPower(motorPower[2]);
        bottomRightMotor.setPower(motorPower[3]);
    }

    public void waitForSeconds(int seconds){
        ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        timer.reset();

        while(timer.time() < seconds){
        }
    }

    public void iSlideMoveElevator(int multiplier){iSlides.moveElevator(multiplier);}

    public void oSlideLoop(){oSlides.loop();}
//    public void iSlideLoop(){iSlides.loop();}

    public void oElevGetHeight(){
        oSlides.getPos();
    }
//    public void iElevGetHeight(){iSlides.getPos();}

    public void oElevMove(Constants.eOElevatorState state, int manualMove){oSlides.moveElevator(state, manualMove);}
//    public void iElevMove(Constants.eIElevatorState state, int manualMove){iSlides.moveElevator(state, manualMove);}

    public boolean oElevIsBusy(){return oElevIsBusy();}

//    public void intakeIn(){iIntake.intake();}
//    public void intakeOut(){
//        iIntake.outTake();
//    }
//    public void intakeStop(){
//        iIntake.stopActiveIntake();
//    }

    public void openClaw(){oClaw.open();}
    public void closeClaw(){oClaw.close();}

    public void oArmStart(){oArm.startPos();}
    public void oArmOut(){oArm.outPos();}

    public void iArmMove(double pos){iArm.moveArm(pos);}
    public void iArmStart(){iArm.startPos();}

    public void iWristStart(){iWrist.startPos();}
    public void iWristOut(){iWrist.outPos();}
    public void iWristIn(){iWristIn();}
}
