package org.firstinspires.ftc.teamcode;

public final class HardwareNames {
    private HardwareNames() {}

    // Drive motors
    public static final String FRONT_LEFT  = "left_front";
    public static final String BACK_LEFT   = "left_back";
    public static final String FRONT_RIGHT = "right_front";
    public static final String BACK_RIGHT  = "right_back";

    // Sensors
    public static final String IMU      = "imu";      // Control Hub built-in IMU
    public static final String PINPOINT = "pinpoint"; // goBILDA Pinpoint odometry (if installed)

    // Intake motors (on the Expansion Hub - the Control Hub's 4 motor ports are used by the drive)
    public static final String INTAKE_LEFT  = "intake_left";
    public static final String INTAKE_RIGHT = "intake_right";

    // TODO: add shooter names here when the robot has them
}