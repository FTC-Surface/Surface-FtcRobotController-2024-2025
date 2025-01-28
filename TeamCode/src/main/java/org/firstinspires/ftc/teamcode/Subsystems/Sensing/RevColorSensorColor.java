package org.firstinspires.ftc.teamcode.Subsystems.Sensing;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

public class RevColorSensorColor extends Subsystem {

    private ColorSensor colorSensor;
    private Constants constants;

    @Override
    public void init(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
    }

    public int colorRed(){return colorSensor.red();}
    public int colorGreen(){return colorSensor.green();}
    public int colorBlue(){return colorSensor.blue();}
    public int alpha(){return colorSensor.alpha();}

    public Constants.eColorSensed getColor(){
        if (colorRed() > colorGreen() && colorRed() > colorBlue()) {
            return Constants.eColorSensed.red;
        } else if (colorGreen() > colorRed() && colorGreen() > colorBlue() && colorGreen()>=200) {
            return Constants.eColorSensed.yellow;
        } else if (colorBlue() > colorRed() && colorBlue() > colorGreen()) {
            return Constants.eColorSensed.blue;
        } else {
            return Constants.eColorSensed.unknown;
        }
    }
}
