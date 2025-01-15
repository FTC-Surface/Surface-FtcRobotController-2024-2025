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
        ManualDown
    }

    public enum eIElevatorState{
        Start,
        MaxOut,
        ManualUp,
        ManualDown
    }

    public enum AutoState{
        idle,
        depositInit,
        grabFirst,
        depositFirst,
        grabSecond,
        depositSecond,
        grabThird,
        depositThird,
        park
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

    public final double IOpenClaw = 0.4;
    public final double ICloseClaw = 0.7;

    public final double OOpenClaw = 0.4;
    public final double OCloseClaw = 0.6;

    public final double OuttakeElevatorMotorPower = 0.7;
    public final double IntakeElevatorMotorPower = 0.3;

    public final double OutakeArmStartPos = 0.9;
    public final double OutakeArmDumpPos = 0.8;
    public final double OutakeArmTakePos = 0.1;
    public final double OutakeArmHookgrabPos = 0.5;
    public final double OutakeArmHookstartPos = 0.55;
    public final double OutakeArmHookupPos = 0.65;


    public final double OutakeWristStartPos = 1;
    public final double OutakeWristDumpPos = 0.8;
    public final double OutakeWristTakePos = 0.1;
    public final double OutakeWristHookgrabPos = 0.5;
    public final double OutakeWristHookstartPos = 1;
    public final double OutakeWristHookupPos = 1;





    public final double IntakeArmStartPos = 1.0;
    public final double IntakeArmGrabPos = 0.5;//0.5
    public final double IntakeArmHoverPos = 0.65;//0.65

    public final double IntakeWristStart = 1.0;
    public final double IntakeWristGrab = 0.8;//0.45
    public final double IntakeWristHoverPos = 0.8;//0.45
}
