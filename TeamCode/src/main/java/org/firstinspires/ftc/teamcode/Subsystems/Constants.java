package org.firstinspires.ftc.teamcode.Subsystems;

public class Constants {
    public enum eOElevatorState{
        Ground,
        Basket,
        Clip,
        PrepareClip,
        Hang
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

    public final double OpenClaw = 0;
    public final double CloseClaw = 0.31;

    public final double OuttakeElevatorMotorPower = 0.5;
    public final double IntakeElevatorMotorPower = 0.5;

    public final double ActiveIntakePow = 0.3;

    public final double IntakeWristPow = 0.5;

    public final double OutakeWristStartPos = 0.8;
    public final double OutakeWristRaisePos = 0.62;
    public final double OutakeWristOutPos = 0.775;
    public final double OutakeWristReadyPos = 0;

    public final double OutakeArmStartPos = 0.8;
    public final double OutakeArmReadyPos = 0;
    public final double OutakeArmOutPos = 0.9;

    public final double IntakeArmStartPos = 0;
    public final double IntakeArmOutPos = 0;
}
