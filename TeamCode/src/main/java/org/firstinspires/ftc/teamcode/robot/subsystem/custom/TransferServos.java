package org.firstinspires.ftc.teamcode.robot.subsystem.custom;

import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.Subsystem;

/*
 * This class handles everything related to the two continuous rotation servos on the front of the
 * robot that spin intake wheels to suck in the artifacts.
 */
public class TransferServos extends Subsystem {
    /*
     * Boolean to keep track of whether or not the intakes are running
     */
    private boolean servosRunning;
    private CRServo leftBoot;
    private CRServo rightBoot;
    private CRServo gecko;
    /*
     * Using the parent class, Subsystem, to construct Claw.
     */
    public TransferServos(Robot robot, CRServo leftBoot, CRServo rightBoot, CRServo gecko) {
        super(robot);
        this.leftBoot = leftBoot;
        this.rightBoot = rightBoot;
        this.gecko = gecko;
    }
    /*
     * Returns true or false depending on whether or not the intakes are running.
     */
    public boolean servosRunning(){
        return servosRunning;
    }
    /*
     * Method for activating / deactivating the intakes.
     */
    public void setServosRunning(boolean on){
        servosRunning = on;
        if (on){
            leftBoot.setPower(1);
            rightBoot.setPower(1);
            gecko.setPower(1);
        } else {
            leftBoot.setPower(0);
            rightBoot.setPower(0);
            gecko.setPower(0);
        }
    }
    /*
     * Method for toggling the intakes to whatever they aren't currently set to. If this is being
     * called from a teleop, use gamepad.buttonWasPressed() rather than gamepad.button, as one
     * button press will last multiple frames and immediately toggle off the motors.
     */
    public void toggleServos(){
        setServosRunning(!servosRunning());
    }
    /*
     * Initializing the flywheels to off because it's not like they would ever start running anyways.
     */
    @Override
    public void init(){
        servosRunning = false;
    }
    @Override
    public void loop(){
        addToTelemetry("Boots running", servosRunning);
    }
}