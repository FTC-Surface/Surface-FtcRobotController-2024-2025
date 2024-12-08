package org.firstinspires.ftc.teamcode.Subsystems.Outtake;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class OuttakeLinearSlides extends Subsystem {

    private DcMotorEx leftOuttakeMotor;
    private DcMotorEx rightOuttakeMotor;

    private double currentPos;

    private double lPower;
    private double rPower;

    @Override
    public void init(HardwareMap hardwareMap) {
        leftOuttakeMotor = hardwareMap.get(DcMotorEx.class, "lInLinearSlide");
        rightOuttakeMotor = hardwareMap.get(DcMotorEx.class, "rInLinearSlide");

        leftOuttakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightOuttakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        leftOuttakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightOuttakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void moveElevator(Constants.eOElevatorState state) {
        switch(state){
            case BottomClip:
            case TopClip:
            case Ground:
            case Basket:
            case Hang:
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

        leftOuttakeMotor.setTargetPosition(height);
        rightOuttakeMotor.setTargetPosition(height);

        leftOuttakeMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightOuttakeMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        leftOuttakeMotor.setPower(lPower);
        rightOuttakeMotor.setPower((rPower));

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
