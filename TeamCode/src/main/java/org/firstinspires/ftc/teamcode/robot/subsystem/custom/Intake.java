package org.firstinspires.ftc.teamcode.robot.subsystem.custom;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.Subsystem;
import static org.firstinspires.ftc.teamcode.constants.HardwareConstants.INTAKE_TARGET_VELOCITY;

/*
 * This class handles everything related to the two continuous rotation servos on the front of the
 * robot that spin intake wheels to suck in the artifacts.
 */
public class Intake extends Subsystem {
    /*
     * Boolean to keep track of if the intakes are running
     */
    private boolean intakeRunning;
    final private DcMotorEx motor;

    public static double triggerDeadzone = 0.08;

    /*
     * Constructing intakes with parent class
     */
    public Intake(Robot robot, DcMotorEx motor) {
        super(robot);
        this.motor = motor;
    }
    /*
     * Returns true or false depending on if the intakes are running.
     */
    public boolean intakeRunning(){
        return intakeRunning;
    }
    /*
     * Method for activating / deactivating the intakes.
     */
    public void setIntakeRunning(boolean on){
        intakeRunning = on;
        if (on) {
            motor.setVelocity(INTAKE_TARGET_VELOCITY);
        } else {
            motor.setVelocity(0);
        }
    }
    /*
     * Method for toggling the intakes to whatever they aren't currently set to. If this is being
     * called from a teleop, use gamepad.buttonWasPressed() rather than gamepad.button, as one
     * button press will last multiple frames and immediately toggle off the motors.
     */
    public void toggleIntake(){
        setIntakeRunning(!intakeRunning());
    }

    /*
     * Method for setting velocity of the intake using a float value 0-1 of a trigger. Called from
     * teleop, which makes sure trigger depression is more than the trigger deadzone, and this
     * method snaps to max velocity if trigger depression is within a deadzone of 1.
     */
    public void setIntake(double trigger){
        double force = trigger >= 1-triggerDeadzone ? 145.6 : trigger * 145.6;
        addToTelemetry("Intake Force", force);
        motor.setVelocity(force);
    }
    /*
     * Initializing the flywheel state to off
     * Setting motor mode
     */
    @Override
    public void init(){
        intakeRunning = false;
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    @Override
    public void loop(){
        addToTelemetry("Intake running", intakeRunning);
    }
}