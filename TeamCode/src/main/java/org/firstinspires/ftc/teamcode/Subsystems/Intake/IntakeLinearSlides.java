package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import android.widget.Switch;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class IntakeLinearSlides extends Subsystem {

    private DcMotorEx intakeLinearSlideOne;
    private DcMotorEx intakeLinearSlideTwo;

    private double currentPos;

    private double lPower;
    private double rPower;

    @Override
    public void init(HardwareMap hardwareMap) {
        intakeLinearSlideOne = hardwareMap.get(DcMotorEx.class, "lInLinearSlide");
        intakeLinearSlideTwo = hardwareMap.get(DcMotorEx.class, "rInLinearSlide");

        intakeLinearSlideOne.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        intakeLinearSlideTwo.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        intakeLinearSlideOne.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        intakeLinearSlideTwo.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void moveElevator(Constants.eIElevatorState state, int manualMove) {
        switch(state){
            case Intake:
            case Outtake:
            case ManualUp:
            case ManualDown:
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

        intakeLinearSlideOne.setTargetPosition(height);
        intakeLinearSlideTwo.setTargetPosition(height);

        intakeLinearSlideOne.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        intakeLinearSlideTwo.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        intakeLinearSlideOne.setPower(lPower);
        intakeLinearSlideTwo.setPower((rPower));

        currentPos = getPos();
    }

    private double getPos(){
        return (double) (intakeLinearSlideOne.getCurrentPosition() + intakeLinearSlideTwo.getCurrentPosition()) /2;
    }

    public boolean isBusy(){
        double position = currentPos;
        if (position != currentPos){
            return true;
        }
        return false;
    }
}
