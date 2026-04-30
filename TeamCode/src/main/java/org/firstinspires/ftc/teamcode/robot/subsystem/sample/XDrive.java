package org.firstinspires.ftc.teamcode.robot.subsystem.sample;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.Subsystem;

/*
 * This class can be used to handle everything relating to a 4 motor drive train with omni wheels
 * mounted at 90 degree angles with each other.
 */
public class XDrive extends Subsystem implements Drivetrain {
    /*
     * Declaring a local variable for each motor and declaring a double to handle and manipulate
     * motor powers more easily.
     */
    double motor1power;
    DcMotor motor1;

    double motor2power;
    DcMotor motor2;

    double motor3power;
    DcMotor motor3;

    double motor4power;
    DcMotor motor4;

    /*
     * Declaring a variable used to keep track of the rotation of the robot which is needed to use
     * field centric controls.
     */
    double heading;
    /*
     * Declaring variables to handle the strafe and forward power as well as the offset angle from
     * the front of the robot to motor 1.
     */
    double strafe;
    double forward;
    double angleMotor1;

    /*
     * Using the parent class, Subsystem, to construct Drivetrain.
     * Setting all the local Motor variables to the Robot's motors.
     * Setting the wheel offset angle.
     */
    public XDrive(Robot robot, double angleDegrees, DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4){
        super(robot);

        this.angleMotor1 = Math.toRadians(angleDegrees);

        this.motor1 = motor1;
        this.motor2 = motor2;
        this.motor3 = motor3;
        this.motor4 = motor4;
    }
    /*
     * The drive function here uses the x-drive magic that I do really get the math behind to move
     * around the robot and drive.
     */
    public void drive(double y, double x, double rotate) {
        strafe = x * Math.cos(angleMotor1) - y * Math.sin(angleMotor1);
        forward = x * Math.sin(angleMotor1) - y * Math.cos(angleMotor1);

        /* the denominator is the largest motor power (absolute value) or 1
         * This ensures all the powers maintain the same ratio,
         * but only if at least one is out of the range [-1, 1]
         */
        double denominator = Math.max(Math.max(Math.abs(forward) +  rotate, Math.abs(strafe) + rotate), 1);

        motor1power = (strafe + rotate) / denominator;
        motor2power = (forward + rotate) / denominator;
        motor3power = (-strafe + rotate) / denominator;
        motor4power = (-forward + rotate) / denominator;

        motor1.setPower(motor1power);
        motor2.setPower(motor2power);
        motor3.setPower(motor3power);
        motor4.setPower(motor4power);
    }

    public void fieldCentricDrive(double y, double x, double rotate) {
        strafe = x * Math.cos(heading) - y * Math.sin(heading);
        forward = x * Math.sin(heading) - y * Math.cos(heading);

        drive(forward, strafe, rotate);
    }
    /*
     * Method for resetting the yaw on a button press or something.
     */
    public void resetYaw() {
        robot.odometry.resetYaw();
    }
    /*
     * Updating the heading and telemetry of the robot every frame.
     */
    @Override
    public void loop() {
        heading = robot.odometry.getHeading(AngleUnit.RADIANS);
        addToTelemetry("Strafe power", strafe);
        addToTelemetry("Forward power", forward);
        addToTelemetry("Motor 1", motor1power);
        addToTelemetry("Motor 2", motor2power);
        addToTelemetry("Motor 3", motor3power);
        addToTelemetry("Motor 4", motor4power);
    }
}
