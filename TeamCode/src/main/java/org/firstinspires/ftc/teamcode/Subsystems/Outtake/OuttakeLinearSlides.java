package org.firstinspires.ftc.teamcode.Subsystems.Outtake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class OuttakeLinearSlides extends Subsystem {

    private DcMotorEx outtakeLinearSlideOne;

    private double currentPos;

    private int targetPos;

    private Constants constants = new Constants();

    public double outtakeElevMotPow = 0;

    @Override
    public void init(HardwareMap hardwareMap) {
        outtakeLinearSlideOne = hardwareMap.get(DcMotorEx.class, "outtakeLinearSlideOne");

        outtakeLinearSlideOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        outtakeLinearSlideOne.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        outtakeLinearSlideOne.setDirection(DcMotorEx.Direction.REVERSE);

        outtakeLinearSlideOne.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void moveElevator(Constants.eOElevatorState state) {
        switch(state){
            case Clip_Grab:
                move(0);
                break;
            case Clip_Ready:
                move(0);
                break;
            case Clip_Hang:
                move(1000);
                break;
            case Ground:
                move(-10);
                break;
            case Basket:
                move(2000);
                break;
            case Ready:
                move(300);
                break;

        }
    }

    public void loop(){
        currentPos = getPos();
    }

    public void setPowerZero(){
        outtakeLinearSlideOne.setPower(0);
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

        outtakeLinearSlideOne.setPower(outtakeElevMotPow);

        outtakeLinearSlideOne.setTargetPosition(targetPos);

        outtakeLinearSlideOne.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    public double getPos(){
        return (double) outtakeLinearSlideOne.getCurrentPosition();
    }

    public boolean isBusy(){ return Math.abs(getPos()-targetPos) < 5;}

    public double getPower(){return outtakeLinearSlideOne.getPower();}
}
