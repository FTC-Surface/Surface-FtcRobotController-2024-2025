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


}
