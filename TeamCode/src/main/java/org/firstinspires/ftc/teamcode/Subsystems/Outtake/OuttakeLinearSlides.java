package org.firstinspires.ftc.teamcode.Subsystems.Outtake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.apache.commons.math3.analysis.function.Constant;
import org.firstinspires.ftc.robotcore.external.Const;
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

        outtakeLinearSlideOne.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        outtakeLinearSlideTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        outtakeLinearSlideOne.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        outtakeLinearSlideTwo.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void moveElevator(Constants.eOElevatorState state) {
        switch(state){
            case Clip:
                break;
            case PrepareClip:
                break;
            case Ground:
                break;
            case Basket:
                break;
            case Hang:
                break;
        }
    }

    private void move(int height){
        setPos(height);
    }

    private void setPos(int height){
        currentPos = getPos();
        targetPos = height;

        if(currentPos > height){
            outtakeElevMotPow = -constants.outtakeElevatorMotorPower;
        }

        if(currentPos < height){
            outtakeElevMotPow = constants.outtakeElevatorMotorPower;
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

    public boolean isBusy(){
        double position = currentPos;
        if (position != currentPos){
            return true;
        }
        return false;
    }
}
