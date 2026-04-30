package org.firstinspires.ftc.teamcode.robot.subsystem.sample;

public interface Drivetrain {
    void drive(double y, double x, double rotate);

    void fieldCentricDrive(double y, double x, double rotate);
    void resetYaw();

    void loop();
}
