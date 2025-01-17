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
        Clip_Grab, Clip_Hang, ManualDown
    }

    public enum eIElevatorState{
        Start,
        MaxOut,
        ManualUp,
        InIntake, ManualDown
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
        depositFourth
    }

    public enum eIntakeState{
        iIntakeReady,
        iCloseClaw,
        iStartArm,
        iOpenClaw,
        iResetArm
    }

    public enum eOuttakeState{

    }

    public final double IOpenClaw = 0.5;
    public final double ICloseClaw = 0.725;

    public final double OOpenClaw = 0.1;
    public final double OCloseClaw = 0.31;

    public final double OuttakeElevatorMotorPower = 0.9;
    public final double IntakeElevatorMotorPower = 0.3;

    public final double OutakeArmStartPos = 0;
    public final double OutakeArmDumpPos = 0;
    
    public final double OutakeArmTakePos = 0.88;
    public final double OutakeArmHookgrabPos = 0.5;
    public final double OutakeArmHookstartPos = 0.125;
    public final double OutakeArmHookupPos = 0.175;

    public final double OutakeWristStartPos = 0.2;
    public final double OutakeWristDumpPos = 0.7;
    public final double OutakeWristTakePos = 0.9;
    public final double OutakeWristHookgrabPos = 0.55;
    public final double OutakeWristHookstartPos = 0.95;
    public final double OutakeWristHookupPos = 0.95;

    public final double IntakeArmStartPos = 1.0;
    public final double IntakeArmGrabPos = 0.55;
    public final double IntakeArmHoverPos = 0.7;

    public final double IntakeWristStart = 0.95;
    public final double IntakeWristGrab = 0.35;
    public final double IntakeWristHoverPos = 0.35;
}
