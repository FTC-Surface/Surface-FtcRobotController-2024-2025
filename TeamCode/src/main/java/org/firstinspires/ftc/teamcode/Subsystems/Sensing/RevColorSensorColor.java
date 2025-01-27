package org.firstinspires.ftc.teamcode.Subsystems.Sensing;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class RevColorSensorColor extends Subsystem {

    private ColorSensor colorSensor;

    @Override
    public void init(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
    }

    public int colorRed(){return colorSensor.red();}
    public int colorGreen(){return colorSensor.green();}
    public int colorBlue(){return colorSensor.blue();}
    public int alpha(){return colorSensor.alpha();}
}
