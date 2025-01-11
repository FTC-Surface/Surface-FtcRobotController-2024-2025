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
    private double Minpos=0;
    private double Maxpos=3001;

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

    public void moveElevator(Constants.eOElevatorState state, int manualMove) {
        switch(state){
            case Clip:
                break;
            case PrepareClip:
                break;
            case Ground:
                move(0);
                break;
            case Basket:
                move(3000);
                break;
            case Hang:
                break;
            case Ready:
                move(500);
                break;
            case Grab:
                move(450);
                break;
            case ManualUp:
                move((int)manualMove);
                break;
            case ManualDown:
                move((int)manualMove);
                break;
        }
    }

    public void loop(){
        if(currentPos <= targetPos + 1.5 && currentPos >= targetPos - 1.5){
            outtakeLinearSlideOne.setPower(0);
            outtakeLinearSlideTwo.setPower(0);
        }

        currentPos = getPos();
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
}
