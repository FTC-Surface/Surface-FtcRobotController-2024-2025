package org.firstinspires.ftc.teamcode.Subsystems;

public class Constants {
    public enum eOElevatorState{
        Ground,
        Basket,
        Ready,
        Clip_Grab,
        Clip_Hang,
        Clip_Ready,
    }

    public enum eIElevatorState{
        ManualBackward,
        ManualForward,
        ManualStop,
        OutIntake,
        InIntake,
        AutoIntakePos, MidRange, ShortRange, LongRange,
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
        park,
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
    public final double IntakeWheelPowIn = 0.9;
    public final double IntakeWheelPowOut =-0.75;

    public final double IntakeArmStartPos = 0.1;
    public final double IntakeArmGrabPos = 0.53;
    public final double IntakeArmmiddlePos = 0.3;

    //Outake
    public final double OOpenClaw = 0.3;
    public final double OCloseClaw = 0.51;

    public final double OutakeArmStartPos = 0.925;
    public final double OutakeArmDumpReadyPos = 0.45;
    public final double OutakeArmDumpReleasePos = 0.35;
    public final double OutakeArmTakePos = 0.925;
    public final double OutakeArmHookgrabPos = 0.04;
    public final double OutakeArmHookupPos = 0.65;
    public final double OutakeArmHookreadyPos = 0.57;
    public final double OutakeArmHookdownPos = 0.635;

    public final double OutakeWristStartPos = 1;
    public final double OutakeWristDumpReleasePos = 0.25;
    public final double OutakeWristTakePos = 1;
    public final double OutakeWristHookgrabPos = 0.16;
    public final double OutakeWristHookupPos = 0.85;
    public final double OutakeWristHookreadyPos = 0.36;
    public final double OutakeWristHookdownPos = 0.4;

}
