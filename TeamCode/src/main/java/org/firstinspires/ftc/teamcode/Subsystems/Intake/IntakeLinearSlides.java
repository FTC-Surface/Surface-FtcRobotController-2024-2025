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
    private int max_pos=2000;
    private int min_pos=0;


    private double intakeElevPower;

    private Constants constants = new Constants();

    @Override
    public void init(HardwareMap hardwareMap) {
        intakeLinearSlideOne = hardwareMap.get(DcMotorEx.class, "InLinearSlideOne");
        intakeLinearSlideTwo = hardwareMap.get(DcMotorEx.class, "InLinearSlideTwo");

        intakeLinearSlideOne.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        intakeLinearSlideTwo.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        intakeLinearSlideOne.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        intakeLinearSlideTwo.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        intakeLinearSlideOne.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeLinearSlideTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intakeLinearSlideOne.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeLinearSlideTwo.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void moveElevator(int multiplier){
        intakeLinearSlideOne.setPower(constants.IntakeElevatorMotorPower);
        intakeLinearSlideTwo.setPower(constants.IntakeElevatorMotorPower);
    }

    public void moveElevator(Constants.eIElevatorState state) {
        switch(state){
            case InIntake:
                move(0);
                break;

            case ShortRange:
                move(300);
                break;

            case MidRange:
                move(700);
                break;

            case LongRange:
                move(1100);
                break;

            case ManualForward:

                if (-getPos() <max_pos) {
                    intakeLinearSlideOne.setPower(-constants.IntakeElevatorMotorPower);
                    intakeLinearSlideTwo.setPower(-constants.IntakeElevatorMotorPower);
                }
                break;

            case ManualBackward:
                if (-getPos() >min_pos) {
                    intakeLinearSlideOne.setPower(constants.IntakeElevatorMotorPower);
                    intakeLinearSlideTwo.setPower(constants.IntakeElevatorMotorPower);
                }
                break;

            case ManualStop:

                intakeLinearSlideOne.setPower(0);
                intakeLinearSlideTwo.setPower(0);

                break;
        }
    }

    public void loop(){
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

        if (Math.abs(currentPos-height)<=7.5)
        {
            intakeElevPower = 0;
        }
        else if(currentPos < height){
            intakeElevPower = -constants.IntakeElevatorMotorPower;
        }
        else if(currentPos > height){
            intakeElevPower = constants.IntakeElevatorMotorPower;
        }


        intakeLinearSlideOne.setTargetPosition(-height);
        intakeLinearSlideTwo.setTargetPosition(-height);

        intakeLinearSlideOne.setPower(intakeElevPower);
        intakeLinearSlideTwo.setPower(intakeElevPower);

        intakeLinearSlideOne.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        intakeLinearSlideTwo.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    public double getPos(){
        return -((double) (intakeLinearSlideOne.getCurrentPosition() + intakeLinearSlideTwo.getCurrentPosition()) /2);
    }

    public boolean isBusy(){ return Math.abs(getPos()-targetPos) < 15;}
}
