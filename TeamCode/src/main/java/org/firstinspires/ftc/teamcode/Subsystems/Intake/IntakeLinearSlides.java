package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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
        intakeLinearSlideOne = hardwareMap.get(DcMotorEx.class, "InLinearSlideOne");
        intakeLinearSlideTwo = hardwareMap.get(DcMotorEx.class, "InLinearSlideTwo");

        intakeLinearSlideOne.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        intakeLinearSlideTwo.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);

        intakeLinearSlideOne.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        intakeLinearSlideTwo.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        intakeLinearSlideOne.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeLinearSlideTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intakeLinearSlideOne.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeLinearSlideTwo.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void moveElevator(int multiplier){
        intakeLinearSlideOne.setPower(constants.IntakeElevatorMotorPower * multiplier);
        intakeLinearSlideTwo.setPower(constants.IntakeElevatorMotorPower * multiplier);
    }

    public void moveElevator(Constants.eIElevatorState state) {
        switch(state){
            case InIntake:
                move(0);
//            case InOuttake:
            case ManualForward:
                intakeLinearSlideOne.setPower(constants.IntakeElevatorMotorPower);
                intakeLinearSlideTwo.setPower(constants.IntakeElevatorMotorPower);

            case ManualBackward:
                intakeLinearSlideOne.setPower(-constants.IntakeElevatorMotorPower);
                intakeLinearSlideTwo.setPower(-constants.IntakeElevatorMotorPower);

            case ManualStop:
                intakeLinearSlideOne.setPower(0);
                intakeLinearSlideTwo.setPower(0);
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
        intakeLinearSlideTwo.setPower(0);
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
        return (double) ((intakeLinearSlideOne.getCurrentPosition()+intakeLinearSlideTwo.getCurrentPosition())/2);
    }

    public boolean isBusy(){ return Math.abs(getPos()-targetPos) < 10;}
}
