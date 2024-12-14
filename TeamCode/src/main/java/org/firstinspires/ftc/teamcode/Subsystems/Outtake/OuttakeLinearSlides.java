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

    private double lPower;
    private double rPower;

    @Override
    public void init(HardwareMap hardwareMap) {
        outtakeLinearSlideOne = hardwareMap.get(DcMotorEx.class, "outtakeLinearSlideOne");
        outtakeLinearSlideTwo = hardwareMap.get(DcMotorEx.class, "outtakeLinearSlideTwo");

        outtakeLinearSlideOne.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        outtakeLinearSlideTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        outtakeLinearSlideOne.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        outtakeLinearSlideTwo.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        outtakeLinearSlideOne.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        outtakeLinearSlideTwo.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void moveElevator(Constants.eOElevatorState state) {
        switch(state){
            case BottomClip:
                break;
            case TopClip:
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
        double currentPosition = getPos();

        if(currentPos > height){

        }

        if(currentPos < height){

        }

        outtakeLinearSlideOne.setTargetPosition(height);
        outtakeLinearSlideTwo.setTargetPosition(height);

        outtakeLinearSlideOne.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        outtakeLinearSlideTwo.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        outtakeLinearSlideOne.setPower(lPower);
        outtakeLinearSlideTwo.setPower((rPower));

        currentPos = getPos();
    }

    private double getPos(){
        return 0;
    }

    public boolean isBusy(){
        double position = currentPos;
        if (position != currentPos){
            return true;
        }
        return false;
    }
}
