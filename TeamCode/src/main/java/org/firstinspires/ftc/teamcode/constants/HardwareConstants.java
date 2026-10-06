package org.firstinspires.ftc.teamcode.constants;

import com.acmerobotics.dashboard.config.Config;

@Config
public class HardwareConstants {
    /*
     * When we control our launcher motor, we are using encoders. These allow the control system
     * to read the current speed of the motor and apply more or less power to keep it at a constant
     * velocity. Here we are setting the target, and minimum velocity that the launcher should run
     * at. The minimum velocity is a threshold for determining when to fire.
     */
    public static final double FLYWHEEL_TARGET_VELOCITY = 2000; // old 1125
    public static final double FLYWHEEL_MIN_VELOCITY = 1990; // old: 2300

    /*
     * Set the odometry pod positions relative to the point that the odometry computer tracks around.
     * The X pod offset refers to how far sideways from the tracking point the X (forward) odometry
     * pod is. Left of the center is a positive number, right of center is a negative number.
     * the Y pod offset refers to how far forwards from the tracking point the Y (strafe) odometry
     * pod is. forward of center is a positive number, backwards is a negative number.
     */
    public static double X_OFFSET = 120;
    public static double Y_OFFSET = 96;
}