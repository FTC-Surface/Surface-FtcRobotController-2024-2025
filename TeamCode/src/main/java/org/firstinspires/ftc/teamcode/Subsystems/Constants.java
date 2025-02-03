package org.firstinspires.ftc.teamcode.Subsystems;

public class Constants {
    public enum eOElevatorState{
        Ground,
        Basket,
        Clip,
        PrepareClip,
        Hang,
        Ready,
        Grab,
        ManualUp,
        Clip_Grab,
        Clip_Hang,
        Clip_Ready, ManualDown
    }

    public enum eIElevatorState{
        InIntake,
        ManualBackward,
        ManualForward,
        ManualStop,
        MoveTo
    }

    public enum AutoState{
        idle,
        depositInit,
        readySamples,
        grabFirst,
        depositFirst,
        grabSecond,
        depositSecond,
        grabThird,
        depositThird,
        grabFourth,
        reset,
        depositFourth
    }

    public enum eOuttakeStateGrabDump{
        oOuttakeReady,
        oOuttakeCloseClaw,
        oOuttakeOuttakeSlides,
        oOuttakeArmDump
    }

    public enum eOuttakeStateClipHang{
        oOuttakeReady,
        oOuttakeOpenClaw,
        oOuttakeReset
    }

    public enum eColorSensed{
        blue,
        yellow,
        red,
        unknown
    }

//    public final double IOpenClaw = 0.5;
//    public final double ICloseClaw = 0.725;

    //Slides Power
    public final double OuttakeElevatorMotorPower = 1;
    public final double IntakeElevatorMotorPower = 1;

    //Intake
    public final double IntakeWheelPowIn = -0.75;
    public final double IntakeWheelPowOut = 0.75;

    public final double IntakeArmStartPos = 1.0;
    public final double IntakeArmGrabPos = 0.55;

    //Outake
    public final double OOpenClaw = 0.4;
    public final double OCloseClaw = 0.1;

    public final double OutakeArmStartPos = 1;
    public final double OutakeArmDumpReadyPos = 0.3;
    public final double OutakeArmDumpReleasePos = 0.45;
    public final double OutakeArmTakePos = 1;
    public final double OutakeArmHookgrabPos = 0.04;
    public final double OutakeArmHookupPos = 0.7;

    public final double OutakeWristStartPos = 0.9;
    public final double OutakeWristDumpReleasePos = 0.2;
    public final double OutakeWristTakePos = 0.9;
    public final double OutakeWristHookgrabPos = 0.12;
    public final double OutakeWristHookupPos = 0.9;

}
