package org.firstinspires.ftc.teamcode.robot.subsystem.custom;

import static org.firstinspires.ftc.teamcode.constants.HardwareConstants.ELEVATOR_BOTTOM_POSITION;
import static org.firstinspires.ftc.teamcode.constants.HardwareConstants.ELEVATOR_TOP_POSITION;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.constants.HardwareConstants;
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
    private final Servo elevator;
    public double top = ELEVATOR_TOP_POSITION;
    public double bottom = ELEVATOR_BOTTOM_POSITION;
    private boolean elevatorUp; // true is up, false is down

    /*
     * Using the parent class, Subsystem, to construct
     */
    public Elevator(Robot robot, Servo elevator) {
        super(robot);
        this.elevator = elevator;
    }
    /*
     * Method for moving the elevator between two positions.
     */
    public void moveServo(){
        if (elevatorUp) {
            elevator.setPosition(bottom);
        } else {
            elevator.setPosition(top);
        }
        elevatorUp = !elevatorUp;
    }

    public void incrementServo(double amount){
        double currentPos = elevator.getPosition();
        double targetPos = currentPos + amount;
        elevator.setPosition(targetPos);
    }
    /*
     * Initializing the elevator to off because it's not like they would ever start running anyways.
     */
    @Override
    public void init(){
        elevatorUp = false;
    }
    @Override
    public void start(){
        elevator.setPosition(bottom);
    }
    @Override
    public void loop(){
        addToTelemetry("Elevator is up", elevatorUp);
        addToTelemetry("Elevator position", elevator.getPosition());

    }
}