package org.firstinspires.ftc.teamcode.robot.subsystem.custom;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.Subsystem;

/*
 * This class handles everything related to the two continuous rotation servos on the front of the
 * robot that spin intake wheels to suck in the artifacts.
 */
public class Elevator extends Subsystem {
    /*
     * Boolean to keep track of whether or not the intakes are running
     */
    private boolean servoRunning;
    private Servo elevator;
    double startPos, endPos;
    private boolean pos; // true is up, false is down

    /*
     * Using the parent class, Subsystem, to construct
     */
    public Elevator(Robot robot, Servo elevator) {
        super(robot);
        this.elevator = elevator;
    }
    /*
     * Returns true or false depending on whether the elevator is running.
     */
    public boolean servoRunning(){
        return servoRunning;
    }
    /*
     * Method for moving the elevator between two positions.
     */
    public void moveServo(){
        servoRunning = true;
        if (pos) {
            elevator.setPosition(startPos);
        } else {
            elevator.setPosition(endPos);
        }
        servoRunning = false;
    }
    /*
     * Initializing the elevator to off because it's not like they would ever start running anyways.
     */
    @Override
    public void init(){
        servoRunning = false;
    }
    @Override
    public void loop(){
        addToTelemetry("Elevator moving: ", servoRunning);
    }
}