package org.firstinspires.ftc.teamcode.TestSubsystems.Sensing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Color Sensor Test", group = "Tests")
@Config
public class RevColorSensorColorTest extends LinearOpMode {

    // Declare the color sensor
    private ColorSensor colorSensor;

    public void runOpMode() throws InterruptedException{
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            int red = colorSensor.red();
            int green = colorSensor.green();
            int blue = colorSensor.blue();
            int alpha = colorSensor.alpha();

            String detectedColor;
            if (red > green && red > blue) {
                detectedColor = "Red_Block";
            } else if (green > red && green > blue && green >= 200) {
                detectedColor = "Yellow_Block";
            } else if (blue > red && blue > green) {
                detectedColor = "Blue_Block";
            } else {
                detectedColor = "No_Block";
            }

            // Send telemetry data to the driver station and FTC Dashboard
            telemetry.addData("Alpha", alpha);
            telemetry.addData("Red", red);
            telemetry.addData("Green", green);
            telemetry.addData("Blue", blue);
            telemetry.addData("Detected Color", detectedColor);
            telemetry.update();

            // Optional: Add a short delay to prevent telemetry from updating too rapidly
            sleep(50);
        }
    }
}
