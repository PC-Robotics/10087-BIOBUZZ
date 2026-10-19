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
public class Transfer extends Subsystem {
    /*
     * Boolean to keep track of if the intakes are running
     */
    private boolean TransferRunning;
    final private DcMotorEx motor;
    /*
     * Constructing intakes with parent class
     */
    public Transfer(Robot robot, DcMotorEx motor) {
        super(robot);
        this.motor = motor;
    }
    /*
     * Returns true or false depending on if the intakes are running.
     */
    public boolean TransferRunning(){
        return TransferRunning;
    }
    /*
     * Method for activating / deactivating the intakes.
     */
    public void setTransferRunning(boolean on){
        TransferRunning = on;
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
    public void toggleTransfer(){
        setTransferRunning(!TransferRunning());
    }
    /*
     * Initializing the flywheel state to off
     * Setting motor mode
     */
    @Override
    public void init(){
        TransferRunning = false;
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    @Override
    public void loop(){
        addToTelemetry("Transfer running", TransferRunning);
    }
}