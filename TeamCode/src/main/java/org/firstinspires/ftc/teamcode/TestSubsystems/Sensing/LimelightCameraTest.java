package org.firstinspires.ftc.teamcode.TestSubsystems.Sensing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

public class LimelightCameraTest {
    OpenCvWebcam webcam1;

    public void init(HardwareMap hardwareMap) {

        WebcamName webcamName1 = hardwareMap.get(WebcamName.class, "webcam1");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam1 = OpenCvCameraFactory.getInstance().createWebcam(webcamName1, cameraMonitorViewId);

        webcam1.openCameraDeviceAsync(new OpenCvWebcam.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                webcam1.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
                webcam1.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);
            }
            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });

    }

    public void loop() {

    }

    static class ePipeline extends OpenCvPipeline {

        public Mat processFrame(Mat Input) {
            Mat mat = new Mat();

            //Turn into HSV value
            Imgproc.cvtColor(Input, mat, Imgproc.COLOR_RGB2HSV);
            if (mat.empty()) {
                return Input;
            }

            //Yellow
            Scalar lowerBound = new Scalar(20, 70, 80);
            Scalar upperBound = new Scalar(32, 255, 255);

            Mat thresh = new Mat();

            Core.inRange(mat, lowerBound, upperBound, thresh);

            Input.release();
            thresh.copyTo(Input);
            return Input;
        }
    }
}
