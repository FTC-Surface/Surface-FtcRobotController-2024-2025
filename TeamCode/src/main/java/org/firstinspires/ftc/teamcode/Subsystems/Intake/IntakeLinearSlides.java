package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class IntakeLinearSlides extends Subsystem {

    private DcMotorEx intakeLinearSlideOne;

    private double currentPos;

    private int targetPos;

    private double intakeElevPower;

    private Constants constants = new Constants();

    @Override
    public void init(HardwareMap hardwareMap) {
        intakeLinearSlideOne = hardwareMap.get(DcMotorEx.class, "InLinearSlide");

        intakeLinearSlideOne.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        intakeLinearSlideOne.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void moveElevator(int multiplier){
        intakeLinearSlideOne.setPower(constants.IntakeElevatorMotorPower * multiplier);
    }

    public void moveElevator(Constants.eIElevatorState state, int manualMove) {
        switch(state){
            case Intake:
            case Outtake:
            case ManualUp:
            case ManualDown:
        }
    }

    public void loop(){
        if(currentPos == targetPos){
            setPowerZero();
        }

        currentPos = getPos();
    }

    private void move(int height){
        setPos(height);
    }
    public void setPowerZero(){
        intakeLinearSlideOne.setPower(0);
    }

    private void setPos(int height){
        currentPos = getPos();
        targetPos = height;

        if(currentPos > height){
            intakeElevPower = -constants.IntakeElevatorMotorPower;
        }

        if(currentPos < height){
            intakeElevPower = constants.IntakeElevatorMotorPower;
        }

        intakeLinearSlideOne.setTargetPosition(height);

        intakeLinearSlideOne.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        intakeLinearSlideOne.setPower(intakeElevPower);
    }

    public double getPos(){
        return (double) (intakeLinearSlideOne.getCurrentPosition());
    }

    public boolean isBusy(){ return Math.abs(getPos()-targetPos) < 10;}
}
