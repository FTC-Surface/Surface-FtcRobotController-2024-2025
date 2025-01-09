package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class IntakeLinearSlides extends Subsystem {

    private DcMotorEx intakeLinearSlideOne;
    private DcMotorEx intakeLinearSlideTwo;

    private double currentPos;

    private int targetPos;

    private double intakeElevPower;

    private Constants constants = new Constants();

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

    public void loop(){
        if(currentPos <= targetPos + 1.5 && currentPos >= targetPos - 1.5){
            intakeLinearSlideTwo.setPower(0.05);
            intakeLinearSlideOne.setPower(0.05);

            if(currentPos == targetPos){
                intakeLinearSlideTwo.setPower(0);
                intakeLinearSlideOne.setPower(0);
            }
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
            intakeElevPower = -constants.IntakeElevatorMotorPower;
        }

        if(currentPos < height){
            intakeElevPower = constants.IntakeElevatorMotorPower;
        }

        intakeLinearSlideOne.setTargetPosition(height);
        intakeLinearSlideTwo.setTargetPosition(height);

        intakeLinearSlideOne.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        intakeLinearSlideTwo.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        intakeLinearSlideOne.setPower(intakeElevPower);
        intakeLinearSlideTwo.setPower(intakeElevPower);
    }

    public double getPos(){
        return (double) (intakeLinearSlideOne.getCurrentPosition() + intakeLinearSlideTwo.getCurrentPosition()) /2;
    }

    public boolean isBusy(){ return Math.abs(getPos()-targetPos) < 10;}
}
