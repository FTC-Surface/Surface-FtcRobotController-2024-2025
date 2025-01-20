package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class IntakeWheel extends Subsystem {

    Servo wheelPartOne;
    Servo wheelPartTwo;

    public void init(HardwareMap hardwareMap) {
        wheelPartOne = hardwareMap.get(Servo.class, "WheelPartOne");
        wheelPartTwo = hardwareMap.get(Servo.class, "WheelPartTwo");
    }

    public void takeBlock() {
        double positionOfWheelOne = wheelPartOne.getPosition();
        double positionOfWheelTwo = wheelPartTwo.getPosition();

        positionOfWheelOne += 1;
        positionOfWheelTwo += 1;

        wheelPartOne.setPosition(positionOfWheelOne);
        wheelPartTwo.setPosition(positionOfWheelTwo);
    }
}
