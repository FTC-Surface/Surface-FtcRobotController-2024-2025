package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

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

public class Robot {
    DcMotorEx topLeftMotor, topRightMotor, bottomLeftMotor, bottomRightMotor;

    OuttakeClaw oClaw = new OuttakeClaw();
    OuttakeArm oArm = new OuttakeArm();
    OuttakeLinearSlides oSlides = new OuttakeLinearSlides();

    IntakeWheel iWheel = new IntakeWheel();
    IntakeArm iArm = new IntakeArm();
    IntakeLinearSlides iSlides = new IntakeLinearSlides();

    LimelightCamera limelightCamera = new LimelightCamera();
    RevColorSensorColor colorSensor = new RevColorSensorColor();

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

        topLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        bottomLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        oClaw.init(hardwareMap);
        oArm.init(hardwareMap);
        oSlides.init(hardwareMap);

        iSlides.init((hardwareMap));
        iArm.init(hardwareMap);

//        limelightCamera.init(hardwareMap);
        colorSensor.init(hardwareMap);

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
