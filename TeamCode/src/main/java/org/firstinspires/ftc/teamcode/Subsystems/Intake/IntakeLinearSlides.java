package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import android.widget.Switch;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class IntakeLinearSlides extends Subsystem {

    private DcMotorEx leftIntakeMotor;
    private DcMotorEx rightIntakeMotor;

    private double currentPos;

    private double lPower;
    private double rPower;

    @Override
    public void init(HardwareMap hardwareMap) {
        leftIntakeMotor = hardwareMap.get(DcMotorEx.class, "lInLinearSlide");
        rightIntakeMotor = hardwareMap.get(DcMotorEx.class, "rInLinearSlide");

        leftIntakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightIntakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        leftIntakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightIntakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
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

        leftIntakeMotor.setTargetPosition(height);
        rightIntakeMotor.setTargetPosition(height);

        leftIntakeMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightIntakeMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        leftIntakeMotor.setPower(lPower);
        rightIntakeMotor.setPower((rPower));

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
