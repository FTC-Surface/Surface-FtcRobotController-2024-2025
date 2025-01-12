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
        Intake,
        Outtake,
        ManualUp,
        ManualDown
    }

    public enum eClawState{
        Open,
        Close
    }

    public enum AutoState{
        idle,
        ready,
        park
    }

    public final double IOpenClaw = 0.6;
    public final double ICloseClaw = 0;

    public final double OuttakeElevatorMotorPower = 0.7;
    public final double IntakeElevatorMotorPower = 0.3;

    public final double OutakeArmStartPos = 0.1;
    public final double OutakeArmOutPos = 0.5;

    public final double IntakeArmStartPos = 1.0;
    public final double IntakeArmGrabPos = 0.5;
    public final double IntakeArmHoverPos = 0;

    public final double IntakeWristStart = 1.0;
    public final double IntakeWristGrab = 0.5;
    public final double IntakeWristHoverPos = 0;
}
