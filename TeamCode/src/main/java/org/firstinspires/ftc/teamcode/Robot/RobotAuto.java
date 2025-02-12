package org.firstinspires.ftc.teamcode.Robot;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.util.ElapsedTime;

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

    public RobotAuto(HardwareMap hardwareMap) {
        oClaw.init(hardwareMap);
        oArm.init(hardwareMap);
        oSlides.init(hardwareMap);

        iSlides.init((hardwareMap));
        iArm.init(hardwareMap);

//        limelightCamera.init(hardwareMap);
        colorSensor.init(hardwareMap);
    }

    public void iSlideMoveElevator(int multiplier) {iSlides.moveElevator(multiplier);}

    public void oSlideLoop() {oSlides.loop();}
    public void iSlideLoop() {iSlides.loop();}

    public double oElevGetHeight() {return oSlides.getPos();}
    public double iElevGetHeight() {return iSlides.getPos();}

    public void oElevMove(Constants.eOElevatorState state) {oSlides.moveElevator(state);}
    public void oElevStop() {oSlides.setPowerZero();}

    public void iElevMove(Constants.eIElevatorState state) {iSlides.moveElevator(state);}
    public void iElevStop() {iSlides.setPowerZero();}

    public boolean oElevIsBusy() {return oSlides.isBusy();}
    public boolean iElevIsBusy() {return iSlides.isBusy();}

    public void iArmGrab() {iArm.downPos();}
    public void iArmStart() {iArm.upPos();}

    public void iWheelTakeBlock() {iWheel.takeBlock();}
    public void iWheelNoBlock() {iWheel.noBlock();}
    public void iWheelOutBlock() {iWheel.outBlock();}

    public void oOpenClaw() {oClaw.openClawOut();}
    public void oCloseClaw() {oClaw.closeClawOut();}

    public void oArmStart() {oArm.startPos();}
    public void oArmDumpRelease() {oArm.dumpreleasePos();}
    public void oArmDumpReady() {oArm.dumpreadyPos();}
    public void oArmTake() {oArm.takePos();}
    public void oArmHookdown() {oArm.hookdownPos();}
    public void oArmHookready() {oArm.hookreadyPos();}
    public void oArmHookgrab() {oArm.hookgrabPos();}
    public void oArmHookup() {oArm.hookupPos();}

    public LLResult limeLightGetResult() {return limelightCamera.returnResult();}
    public void limelightStart(int index) {limelightCamera.limelightStart(index);}

    public Constants.eColorSensed getColorResult() {return colorSensor.getColor();}
    public String getColorBlock() {return colorSensor.TelemetryColor();}

//********************************** Actions *****************************************

    public Action oSlidesBucketAction() {return new InstantAction(() -> oElevMove(Constants.eOElevatorState.Basket));}
    public Action oSlideHookUpAction() {return new InstantAction(() -> oElevMove(Constants.eOElevatorState.Clip_Hang));}
    public Action oSlideHookGrabAction() {return new InstantAction(() -> oElevMove(Constants.eOElevatorState.Clip_Grab));}
    public Action oSlideHookReadyAction() {return new InstantAction(() -> oElevMove(Constants.eOElevatorState.Clip_Ready));}
    public Action oSlideGroundAction() {return new InstantAction(() -> oElevMove(Constants.eOElevatorState.Ground));}
    public Action oSlideReadyAction() {return new InstantAction(() -> oElevMove(Constants.eOElevatorState.Ready));}

    public Action iSlideOutIntakeAction(){return new InstantAction(() -> iElevMove(Constants.eIElevatorState.OutIntake));}
    public Action iSlideInIntakeAction(){return new InstantAction(() -> iElevMove(Constants.eIElevatorState.InIntake));}
    public Action iSlideLowAction(){return new InstantAction(() -> iElevMove(Constants.eIElevatorState.ShortRange));}
    public Action iSlideMediumAction(){return new InstantAction(() -> iElevMove(Constants.eIElevatorState.MidRange));}
    public Action iSlideHighAction(){return new InstantAction(() -> iElevMove(Constants.eIElevatorState.LongRange));}

    public class ISlideAction {
        public Action manualForwardAction(int pos) {return new ManualForward(pos);}
        public Action manualBackAction(int pos) {return new ManualBack(pos);}

        private class ManualForward implements Action{

            private boolean initialized = false;
            int targetPos = 0;

            ManualForward(int targetPos){
                this.targetPos = targetPos;
            }

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    iElevMove(Constants.eIElevatorState.ManualForward);
                    initialized = true;
                }

                double pos = iElevGetHeight();

                if (pos < targetPos) {
                    return true;
                } else {
                    iElevStop();
                    return false;
                }
            }
        }
        private class ManualBack implements Action{

            private boolean initialized = false;
            int targetPos = 0;

            ManualBack(int targetPos){
                this.targetPos = targetPos;
            }

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    iElevMove(Constants.eIElevatorState.ManualBackward);
                    initialized = true;
                }

                double pos = iElevGetHeight();

                if (pos > targetPos) {
                    return true;
                } else {
                    iElevStop();
                    return false;
                }
            }
        }
    }

    public Action oClawClosewAction(){return new InstantAction(() -> oCloseClaw());}
    public Action oClawOpenAction(){return new InstantAction(() -> oOpenClaw());}

    public Action oArmStartAction(){return new InstantAction(() -> oArmStart());}
    public Action oArmDumpReleaseAction(){return new InstantAction(() -> oArmDumpRelease());}
    public Action oArmDumpReadyAction(){return new InstantAction(() -> oArmDumpReady());}
    public Action oArmTakeAction(){return new InstantAction(() -> oArmTake());}
    public Action oArmHookDownAction(){return new InstantAction(() -> oArmHookdown());}
    public Action oArmHookReadyAction(){return new InstantAction(() -> oArmHookready());}
    public Action oArmHookGrab(){return new InstantAction(() -> oArmHookgrab());}
    public Action oArmHookUp(){return new InstantAction(() -> oArmHookup());}

    public Action iArmStartAction(){return new InstantAction(() -> iArmStart());}
    public Action iArmGrabAction(){return new InstantAction(() -> iArmGrab());}

    public Action iWheelTakeBlockAction(){return new InstantAction(() -> iWheelTakeBlock());}
    public Action iWheelNoBlockAction(){return new InstantAction(() -> iWheelNoBlock());}
    public Action iWheelOutBlockAction(){return new InstantAction(() -> iWheelOutBlock());}

//***************************** timer ********************************

    public void waitForSeconds(double seconds) {
        ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        timer.reset();

        while (timer.time() < seconds) {
        }
    }
}
