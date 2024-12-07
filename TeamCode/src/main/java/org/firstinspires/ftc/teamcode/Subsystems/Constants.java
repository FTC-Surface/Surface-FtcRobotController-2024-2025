package org.firstinspires.ftc.teamcode.Subsystems;

public class Constants {
    public enum eElevatorState{
        Ground,
        Basket,
        TopClip,
        BottomClip,
        Hang
    }

    public enum eClawState{
        Open,
        Close
    }

    public static double openL = 0;
    public static double openR = 0;

    public static double closeR = 0;
    public static double closeL = 0;
}
