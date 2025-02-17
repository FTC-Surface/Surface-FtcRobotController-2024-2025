package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.localization.Localizers;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.util.CustomFilteredPIDFCoefficients;
import com.pedropathing.util.CustomPIDFCoefficients;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class FConstants {
    static {
        FollowerConstants.localizers = Localizers.PINPOINT;

        FollowerConstants.leftFrontMotorName = "topLeft";
        FollowerConstants.leftRearMotorName = "bottomLeft";
        FollowerConstants.rightFrontMotorName = "topRight";
        FollowerConstants.rightRearMotorName = "bottomRight";

        FollowerConstants.leftFrontMotorDirection = DcMotorEx.Direction.REVERSE;
        FollowerConstants.leftRearMotorDirection = DcMotorEx.Direction.REVERSE;
        FollowerConstants.rightFrontMotorDirection = DcMotorEx.Direction.FORWARD;
        FollowerConstants.rightRearMotorDirection = DcMotorEx.Direction.FORWARD;

        FollowerConstants.mass = 15;

        FollowerConstants.xMovement = 79.6;
        FollowerConstants.yMovement = 62.9;

        FollowerConstants.forwardZeroPowerAcceleration = -55.33222672307656;
        FollowerConstants.lateralZeroPowerAcceleration = -74.82828458861616;

        FollowerConstants.translationalPIDFCoefficients.setCoefficients(0.35,0,0.05,0);
        FollowerConstants.useSecondaryTranslationalPID = false;
        FollowerConstants.secondaryTranslationalPIDFCoefficients.setCoefficients(0.1,0,0.01,0); // Not being used, @see useSecondaryTranslationalPID

        FollowerConstants.headingPIDFCoefficients.setCoefficients(0.45,0,0.1,0);
        FollowerConstants.useSecondaryHeadingPID = true;
        FollowerConstants.secondaryHeadingPIDFCoefficients.setCoefficients(2,0,0.1,0); // Not being used, @see useSecondaryHeadingPID

        FollowerConstants.drivePIDFCoefficients.setCoefficients(0.004,0,0.0002,0.6,0);
        FollowerConstants.useSecondaryDrivePID = false;
        FollowerConstants.secondaryDrivePIDFCoefficients.setCoefficients(0.1,0,0,0.6,0); // Not being used, @see useSecondaryDrivePID

        FollowerConstants.zeroPowerAccelerationMultiplier = 4;
        FollowerConstants.centripetalScaling = 0.000175;

        FollowerConstants.pathEndTimeoutConstraint = 500;
        FollowerConstants.pathEndTValueConstraint = 0.995;
        FollowerConstants.pathEndVelocityConstraint = 0.1;
        FollowerConstants.pathEndTranslationalConstraint = 0.1;
        FollowerConstants.pathEndHeadingConstraint = 0.007;
    }
}
