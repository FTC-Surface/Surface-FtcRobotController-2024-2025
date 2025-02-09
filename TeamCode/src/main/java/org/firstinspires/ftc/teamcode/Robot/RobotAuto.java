package org.firstinspires.ftc.teamcode.Robot;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeLinearSlides;
//import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeClaw;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeArm;

import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeWheel;
import org.firstinspires.ftc.teamcode.Subsystems.Outtake.OuttakeArm;
import org.firstinspires.ftc.teamcode.Subsystems.Outtake.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Subsystems.Outtake.OuttakeLinearSlides;

import org.firstinspires.ftc.teamcode.Subsystems.Sensing.LimelightCamera;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Sensing.RevColorSensorColor;

public class RobotAuto {
    DcMotorEx topLeftMotor, topRightMotor, bottomLeftMotor, bottomRightMotor;

    OuttakeClaw oClaw = new OuttakeClaw();
    OuttakeArm oArm = new OuttakeArm();
    OuttakeLinearSlides oSlides = new OuttakeLinearSlides();

    IntakeWheel iWheel = new IntakeWheel();
    IntakeArm iArm = new IntakeArm();
    IntakeLinearSlides iSlides = new IntakeLinearSlides();

    LimelightCamera limelightCamera = new LimelightCamera();
    RevColorSensorColor colorSensor = new RevColorSensorColor();

    Constants constants = new Constants();

    double maxSpeed;

    public RobotAuto(HardwareMap hardwareMap){
        oClaw.init(hardwareMap);
        oArm.init(hardwareMap);
        oSlides.init(hardwareMap);

        iSlides.init((hardwareMap));
        iArm.init(hardwareMap);

//        limelightCamera.init(hardwareMap);
        colorSensor.init(hardwareMap);
    }

    public void iSlideMoveElevator(int multiplier){iSlides.moveElevator(multiplier);}

    public void oSlideLoop(){oSlides.loop();}
    public void iSlideLoop(){iSlides.loop();}

    public double oElevGetHeight(){return oSlides.getPos();}
    public double iElevGetHeight(){return iSlides.getPos();}

    public void oElevMove(Constants.eOElevatorState state){oSlides.moveElevator(state);}
    public void oElevStop(){oSlides.setPowerZero();}

    public void iElevMove(Constants.eIElevatorState state){iSlides.moveElevator(state);}
    public void iElevStop(){iSlides.setPowerZero();}

    public boolean oElevIsBusy(){return oSlides.isBusy();}
    public boolean iElevIsBusy(){return iSlides.isBusy();}

    public void iArmGrab(){iArm.downPos();}
    public void iArmStart(){iArm.upPos();}

    public void iWheelTakeBlock() {iWheel.takeBlock();}
    public void iWheelNoBlock() {iWheel.noBlock();}
    public void iWheelOutBlock() {iWheel.outBlock();}

    public void oOpenClaw(){oClaw.openClawOut();}
    public void oCloseClaw(){oClaw.closeClawOut();}

    public void oArmStart(){oArm.startPos();}
    public void oArmDumpRelease(){oArm.dumpreleasePos();}
    public void oArmDumpReady(){oArm.dumpreadyPos();}
    public void oArmTake(){oArm.takePos();}
    public void oArmHookdown(){oArm.hookdownPos();}
    public void oArmHookready(){oArm.hookreadyPos();}


    public void oArmHookgrab(){oArm.hookgrabPos();}
    public void oArmHookup(){oArm.hookupPos();}

    public LLResult limeLightGetResult(){return limelightCamera.returnResult();}
    public void limelightStart(int index){limelightCamera.limelightStart(index);}

    public Constants.eColorSensed getColorResult(){return colorSensor.getColor();}
    public String getColorBlock(){return colorSensor.TelemetryColor();}

    public void waitForSeconds(int seconds){
        ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        timer.reset();

        while(timer.time() < seconds){
        }
    }
}
