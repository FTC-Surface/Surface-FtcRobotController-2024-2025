package org.firstinspires.ftc.teamcode.Subsystems;

public class Constants {
    public enum eOElevatorState{
        Ground,
        Basket,
        TopClip,
        BottomClip,
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

    public final double ActiveIntakeIn = 0;
    public final double ActiveIntakeOut = 0;
}
