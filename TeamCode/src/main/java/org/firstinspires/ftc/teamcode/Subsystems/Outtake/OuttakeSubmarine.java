package org.firstinspires.ftc.teamcode.Subsystems.Outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class OuttakeSubmarine extends Subsystem {

    Servo SubVertical;
    Servo SubHorizontal;
    Servo SpeciArm;

    private Constants constants = new Constants();

    @Override
    public void init(HardwareMap hardwareMap) {
        SubVertical = hardwareMap.get(Servo.class, "SubVerticalSlides");
        SubHorizontal = hardwareMap.get(Servo.class, "SubHorizontalSlides");
        SpeciArm = hardwareMap.get(Servo.class, "Speci Arm");
    }
}
