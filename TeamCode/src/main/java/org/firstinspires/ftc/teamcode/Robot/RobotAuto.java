package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.util.ElapsedTime;

//import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeActiveIntake;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeLinearSlides;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeClaw;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeArm;

import org.firstinspires.ftc.teamcode.Subsystems.Outtake.OuttakeArm;
import org.firstinspires.ftc.teamcode.Subsystems.Outtake.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Subsystems.Outtake.OuttakeLinearSlides;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;

public class RobotAuto {
    OuttakeClaw oClaw = new OuttakeClaw();
    OuttakeArm oArm = new OuttakeArm();
    OuttakeLinearSlides oSlides = new OuttakeLinearSlides();
    IntakeArm iArm = new IntakeArm();
    IntakeClaw iClaw = new IntakeClaw();
    IntakeLinearSlides iSlides = new IntakeLinearSlides();

    Constants constants = new Constants();

    double maxSpeed;

    public RobotAuto(HardwareMap hardwareMap){
        oArm.init(hardwareMap);
        oSlides.init(hardwareMap);

//        iSlides.init((hardwareMap));
        iClaw.init(hardwareMap);
        iArm.init(hardwareMap);

        for (LynxModule module : hardwareMap.getAll(LynxModule.class)) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
    }

    public void iSlideMoveElevator(int multiplier){iSlides.moveElevator(multiplier);}

    public void oSlideLoop(){oSlides.loop();}
//    public void iSlideLoop(){iSlides.loop();}

    public double oElevGetHeight(){
        return oSlides.getPos();
    }
//    public void iElevGetHeight(){iSlides.getPos();}

    public void oElevMove(Constants.eOElevatorState state){oSlides.moveElevator(state);}
    public void oElevStop(){oSlides.setPowerZero();}
//    public void iElevMove(Constants.eIElevatorState state, int manualMove){iSlides.moveElevator(state, manualMove);}

    public boolean oElevIsBusy(){return oElevIsBusy();}
    public double oElevGetPower(){return oElevGetPower();}

    public void iOpenClaw(){iClaw.openClawIn();}
    public void iCloseClaw(){iClaw.closeClawIn();}

    public void iArmGrab(){iArm.downPos();}
    public void iArmStart(){iArm.upPos();}
    public void iArmHover(){iArm.hoverPos();}

    public void oOpenClaw(){oClaw.openClawOut();}
    public void oCloseClaw(){oClaw.closeClawOut();}

    public void oArmStart(){oArm.startPos();}
    public void oArmDump(){oArm.dumpPos();}
    public void oArmTake(){oArm.takePos();}
    public void oArmHookgrab(){oArm.hookgrabPos();}
    public void oArmHookstart(){oArm.hookstartPos();}
    public void oArmHookup(){oArm.hookupPos();}




    public void waitForSeconds(int seconds){
        ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        timer.reset();

        while(timer.time() < seconds){
        }
    }
}
