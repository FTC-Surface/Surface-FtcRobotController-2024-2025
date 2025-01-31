package org.firstinspires.ftc.teamcode.ArchivedCode;

public class CommentedOutTeleOpStuff {

//        Constants constants = new Constants();

//        boolean xOpenClawDone = false;
//        boolean yOpenClawDone = false;
//        boolean yArmstartDone = false;
//        boolean yPressed = false;
//        boolean bumperPressed = false;
//        long yActionStartTime = 0;


//        long IntakeStartTime2=0;

//        long OuttakeStartTime4=0;

//        boolean Outtakepressed1 = false;
//        boolean Triggerpressed = false;
//        boolean stickmoved2 = false;
//        boolean OuttakeclawDone = false;
//        boolean ArmTakeDone = false;
//        boolean OpenClawDone = false;
//        boolean LinearSlidereadyDone = false;
//        boolean OpenClawDone2 = false;


    //Will need to change in future
//            if(gamepad2.left_trigger >= 0.5) {
//                robot.iWheelTakeBlock();
//            }
//            else {
//                robot.iWheelNoBlock();
//            }


//          Intake Version 3
//            if ((gamepad2.right_stick_y <= -0.2) && robot.iElevGetHeight() <= 2000) {
//                robot.iElevMove(Constants.eIElevatorState.ManualUp);
//            } else if (gamepad2.right_stick_y >= 0.2 && robot.iElevGetHeight() >= 0) {
//                robot.iElevMove(Constants.eIElevatorState.ManualDown);
//            } else {
//                robot.iElevMove(Constants.eIElevatorState.ManualStop);
//            }
//
//            if(gamepad2.dpad_down)//Arm_Hover
//                robot.iArmHover();
//            if(gamepad2.dpad_up)//Arm_Grab
//                robot.iArmGrab();
//
//            if(gamepad2.right_trigger >= 0.5 && !Triggerpressed) {
//                robot.iCloseClaw();
//                IntakeStartTime2 = (long) outtakeTimer.milliseconds();
//                Triggerpressed = true;
//            }
//            if(Triggerpressed && outtakeTimer.milliseconds() - IntakeStartTime2 >= 300)
//            {
//                robot.iArmHover();
//                Triggerpressed = false;
//            }
//
//            if(gamepad2.left_trigger >= 0.5)
//                robot.iOpenClaw();
//
//            if (gamepad2.right_stick_button && !bumperPressed) {
//                robot.iCloseClaw();
//                bumperPressed = true;
//                xOpenClawDone = false;
//                yArmstartDone=false;
//                yActionStartTime = (long) intakeTimer.milliseconds();
//            }
//            if (bumperPressed && !yArmstartDone && intakeTimer.milliseconds() - yActionStartTime >= 300) {
//                robot.iElevMove(Constants.eIElevatorState.InIntake);
//                robot.iArmStart();
//                yArmstartDone=true;
//            }
//            if (bumperPressed && yArmstartDone && !xOpenClawDone && intakeTimer.milliseconds() - yActionStartTime >= 1000) {
//                robot.iOpenClaw();
//                xOpenClawDone = true;
//            }
//            if (bumperPressed && xOpenClawDone && yArmstartDone && intakeTimer.milliseconds() - yActionStartTime >= 1500) {
//                robot.iArmHover();
//                bumperPressed = false;
//            }


    ////          Intake Version 1 (Down+Up)
//            if (gamepad2.y && !yPressed) {
//                yPressed = true;
//                xOpenClawDone = false;
//                yOpenClawDone=false;
//                yArmstartDone=false;
//                yActionStartTime = (long) intakeTimer.milliseconds();
//                robot.iArmGrab();
//            }
//
//            if (yPressed && !yOpenClawDone && intakeTimer.milliseconds() - yActionStartTime >= 100) {
//                robot.iCloseClaw();
//                yOpenClawDone=true;
//            }
//            if (yPressed && yOpenClawDone && !yArmstartDone && intakeTimer.milliseconds() - yActionStartTime >= 600) {
//                 // Reset flag for the open claw action
//                robot.iArmStart();
//                yArmstartDone=true;
//            }
//            if (yPressed && !xOpenClawDone && yArmstartDone && yOpenClawDone && intakeTimer.milliseconds() - yActionStartTime >= 1000) {
//                robot.iOpenClaw();
//                xOpenClawDone = true;
//            }
//            if (yPressed && xOpenClawDone && yArmstartDone && yOpenClawDone && intakeTimer.milliseconds() - yActionStartTime >= 1500) {
//                robot.iArmHover();
//                yPressed = false;
//            }


//            switch(intakeState){
//                case iIntakeReady:
//                    if (gamepad2.y) {
//                        yPressed = true;
//                        xOpenClawDone = false;
//                        yOpenClawDone=false;
//                        yArmstartDown=false;

//                        yActionStartTime = (long) intakeTimer.milliseconds();

//                        robot.iArmGrab();
//                        intakeState = eIntakeState.iCloseClaw;
//                    }
//                    break;
//
//                case iCloseClaw:
//                    if(intakeTimer.milliseconds() - yActionStartTime >= 100){
//                        robot.iCloseClaw();
//                        intakeState = eIntakeState.iStartArm;
//                    }
//                    break;
//
//                case iStartArm:
//                    if (intakeTimer.milliseconds() - yActionStartTime >= 500) {
//                        // Reset flag for the open claw action
//                        robot.iArmStart();
//                        intakeState = eIntakeState.iOpenClaw;
//                    }
//                    break;
//
//                case iOpenClaw:
//                    if(intakeTimer.milliseconds() - yActionStartTime >= 1000){
//                        robot.iOpenClaw();
//                        xOpenClawDone = true;
//                        intakeState = eIntakeState.iResetArm;
//                    }
//                    break;
//
//                case iResetArm:
//                    if(intakeTimer.milliseconds() - yActionStartTime >= 1500){
//                        robot.iArmHover();
//                        yPressed = false;
//                        intakeState = eIntakeState.iIntakeReady;
//                    }
//                    break;
//            }


//Dump + Claw_Open + Arm_Start + Slide_Ready
//            if(gamepad2.left_trigger > 0.5 && !Outtakepressed2)
//            {
//                robot.oArmDump();
//                OuttakeStartTime2 = (long) outtakeTimer.milliseconds();
//                Outtakepressed2 = true;
//                ArmTakeDone = false;
//                OpenClawDone = false;
//            }
//            if(Outtakepressed2 && !OpenClawDone && outtakeTimer.milliseconds() - OuttakeStartTime2 >= 300){
//                robot.oOpenClaw();
//                OpenClawDone = true;
//            }
//            if(Outtakepressed2 && !ArmTakeDone && OpenClawDone && outtakeTimer.milliseconds() - OuttakeStartTime2 >= 500){
//                robot.oArmStart();
//                ArmTakeDone = true;
//            }
//            if(Outtakepressed2 && ArmTakeDone && OpenClawDone && outtakeTimer.milliseconds() - OuttakeStartTime2 >= 800){
//                //robot.oElevMove(Constants.eOElevatorState.Ready);
//                Outtakepressed2 = false;
//            }

//********** Limelight Camera ***************************************************

//            LLResult result = robot.limeLightGetResult();

//            if(result != null){
//                if(result.isValid()){
//                    if(gamepad2.square){
//                        blockYAngle = result.getTy();
//                        blockXAngle = result.getTx();
//                    }
//                }
//            } else {
//                blockYAngle = 0;
//                blockXAngle = 0;
//            }
//
//            theta = 45 + blockYAngle;
//
//            distanceToMove = (int) (Math.tan(theta) * cameraHeight);

//commented out code that was put here has been moved to CommentedOutTeleOpStuff in ArchivedCode

//            switch (outtakeStateGrabbing){
//                case oOuttakeReady:
//                    if(gamepad2.circle){
//                        //Insert Code Here
//
//                        OuttakeStartTime1 = (long) outtakeTimer.milliseconds();
//
//                        outtakeStateGrabbing = Constants.eOuttakeStateGrabDump.oOuttakeCloseClaw;
//                    }
//                case oOuttakeCloseClaw:
//                    if(outtakeTimer.milliseconds() - OuttakeStartTime1 >= 0){
//                        outtakeStateGrabbing = Constants.eOuttakeStateGrabDump.oOuttakeOuttakeSlides;
//                    }
//                case oOuttakeOuttakeSlides:
//                    if(outtakeTimer.milliseconds() - OuttakeStartTime1 >= 0){
//                        robot.oCloseClaw();
//
//                        outtakeStateGrabbing = Constants.eOuttakeStateGrabDump.oOuttakeArmDump;
//                    }
//                case oOuttakeArmDump:
//                    if(outtakeTimer.milliseconds() - OuttakeStartTime1 >= 400){
//                        robot.oArmDumpReady();
//
//                        outtakeStateGrabbing = Constants.eOuttakeStateGrabDump.oOuttakeReady;
//                    }
//            }

//            switch (outtakeStateClipping){
//                case oOuttakeReady:
//                    if(gamepad2.square){
//                        robot.oElevMove(Constants.eOElevatorState.Clip_Hang);
//
//                        OuttakeStartTime3 = (long) outtakeTimer.milliseconds();
//
//                        stickmoved1 = true;
//
//                        outtakeStateClipping = Constants.eOuttakeStateClipHang.oOuttakeOpenClaw;
//                    }
//                case oOuttakeOpenClaw:
//                    if(outtakeTimer.milliseconds() - OuttakeStartTime3 >= 700){
//                        robot.oOpenClaw();
//
//                        outtakeStateClipping = Constants.eOuttakeStateClipHang.oOuttakeReset;
//                    }
//                case oOuttakeReset:
//                    if(outtakeTimer.milliseconds() - OuttakeStartTime3 >= 1000){
//                        robot.oArmHookgrab();
//                        robot.oElevMove(Constants.eOElevatorState.Clip_Grab);
//
//                        stickmoved1 = false;
//
//                        outtakeStateClipping = Constants.eOuttakeStateClipHang.oOuttakeReady;
//                    }
//            }

//Remove this if robot starts crashing

//            telemetry.addData("Block Y Angle: ", blockYAngle);
//            telemetry.addData("Block X Angle: ", blockXAngle);
//            telemetry.addData("Theta: ", theta);
//            telemetry.addData("DistanceToMove: ", distanceToMove);
}
