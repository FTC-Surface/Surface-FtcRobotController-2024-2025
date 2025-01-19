package org.firstinspires.ftc.teamcode.Subsystems.Outtake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class OuttakeLinearSlides extends Subsystem {

    private DcMotorEx outtakeLinearSlideOne;
    private DcMotorEx outtakeLinearSlideTwo;

    private double currentPos;

    private int targetPos;

    private Constants constants = new Constants();

    public double outtakeElevMotPow = 0;

    @Override
    public void init(HardwareMap hardwareMap) {
        outtakeLinearSlideOne = hardwareMap.get(DcMotorEx.class, "outtakeLinearSlideOne");
        outtakeLinearSlideTwo = hardwareMap.get(DcMotorEx.class, "outtakeLinearSlideTwo");

        outtakeLinearSlideOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtakeLinearSlideTwo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        outtakeLinearSlideOne.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        outtakeLinearSlideTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        outtakeLinearSlideOne.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        outtakeLinearSlideTwo.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void moveElevator(Constants.eOElevatorState state) {
        switch(state){
            case Clip_Grab:
                move(1000);
                break;
            case PrepareClip:
                break;
            case Ground:
                move(0);
                break;
            case Basket:
                move(3100);
                break;
            case Clip_Hang:
                move(2100);
                break;
            case Ready:
                move(1200);
                break;
            case Grab:
                move(550);
                break;
        }
    }

    public void loop(){
        if(currentPos == targetPos){
            setPowerZero();
        }

        currentPos = getPos();
    }

    public void setPowerZero(){
        outtakeLinearSlideOne.setPower(0);
        outtakeLinearSlideTwo.setPower(0);
    }

    private void move(int height){
        setPos(height);
    }

    private void setPos(int height){
        currentPos = getPos();
        targetPos = height;

        if(currentPos > height){
            outtakeElevMotPow = -constants.OuttakeElevatorMotorPower;
        }

        if(currentPos < height){
            outtakeElevMotPow = constants.OuttakeElevatorMotorPower;
        }

        outtakeLinearSlideTwo.setPower(outtakeElevMotPow);
        outtakeLinearSlideOne.setPower(outtakeElevMotPow);

        outtakeLinearSlideOne.setTargetPosition(targetPos);
        outtakeLinearSlideTwo.setTargetPosition(targetPos);

        outtakeLinearSlideOne.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        outtakeLinearSlideTwo.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    public double getPos(){
        return (double) (outtakeLinearSlideOne.getCurrentPosition() + outtakeLinearSlideTwo.getCurrentPosition()) /2;
    }

    public boolean isBusy(){ return Math.abs(getPos()-targetPos) < 10;}

    public double getPower(){return outtakeLinearSlideTwo.getPower();}
}
