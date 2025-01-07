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

    public final double openClawL = 0;
    public final double openClawR = 0;

    public final double closeClawR = 0;
    public final double closeClawL = 0;

    public final double outtakeElevatorMotorPower = 0.75;
    public final double intakeElevatorMotorPower = 0.75;

    public final double ActiveIntakePow = 0.3;
}
