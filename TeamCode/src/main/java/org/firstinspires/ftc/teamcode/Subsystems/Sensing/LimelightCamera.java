package org.firstinspires.ftc.teamcode.Subsystems.Sensing;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

public class LimelightCamera extends Subsystem {

    private Limelight3A limelight;

    @Override
    public void init(HardwareMap hardwareMap) {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
    }

    public void limelightStart(int index){
        limelight.pipelineSwitch(index);

        limelight.start();
    }

    public LLResult returnResult(){return limelight.getLatestResult();}
    public double getAngle(){
        if (returnResult().getPythonOutput()[0] != -3) {
            return returnResult().getPythonOutput()[0];
        }

        else {
            return 0.00;
        }
    }

    public double getMovementAngle() {
        return returnResult().getTx();
    }
}
