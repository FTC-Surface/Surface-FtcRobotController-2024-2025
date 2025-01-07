package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class IntakeActiveIntake extends Subsystem {

    CRServo lIntake;
    CRServo rIntake;

    Constants constants = new Constants();

    @Override
    public void init(HardwareMap hardwareMap) {
        lIntake = hardwareMap.get((CRServo.class), "lIntake");
        rIntake = hardwareMap.get((CRServo.class), "rIntake");
    }

    public void intake(){
        rIntake.setPower(constants.ActiveIntakePow * -1);
        lIntake.setPower(constants.ActiveIntakePow * -1);
    }

    public void outTake(){
        rIntake.setPower(constants.ActiveIntakePow);
        lIntake.setPower(constants.ActiveIntakePow);
    }

    public void stopActiveIntake(){
        rIntake.setPower(0);
        lIntake.setPower(0);
    }
}
