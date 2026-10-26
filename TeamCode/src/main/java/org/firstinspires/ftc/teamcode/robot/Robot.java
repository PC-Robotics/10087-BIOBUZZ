package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot.subsystem.custom.Transfer;
import org.firstinspires.ftc.teamcode.robot.subsystem.custom.TransferServos;
import org.firstinspires.ftc.teamcode.robot.subsystem.custom.Shooter;
import org.firstinspires.ftc.teamcode.robot.subsystem.sample.Mecanum;
import org.firstinspires.ftc.teamcode.robot.subsystem.custom.Intake;

import java.util.ArrayList;
//import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/*
 * This class handles the setup for all the different subsystems of the robot as well as providing
 * a space for any extra methods, such as launch() to be.
 */

public class Robot extends RobotSetup {
    /*
     * Declaring all the subsystems as well as the subsystem list.
     */
    public Mecanum drivetrain;
    //public DrivetrainPID PID;
    //public Odometry odometry;
    public Intake intake;
    public Transfer transfer;
    public TransferServos servos;
    public Shooter shooter;

    private final List<Subsystem> subsystems = new ArrayList<>();

    /*
     * Initializing RobotSetup as well as assigning all the components, adding them to the
     * subsystem list, and then initializing everything in the subsystem list.
     */
    @Override
    public void init(HardwareMap hardwareMap) {
        super.init(hardwareMap);
        drivetrain = new Mecanum(this, leftFrontDrive, rightFrontDrive, leftBackDrive, rightBackDrive);
        //PID = new DrivetrainPID(this);
        //odometry = new Odometry(this);
        intake = new Intake(this, intakeMotor);
        transfer = new Transfer(this, transferMotor);
        servos = new TransferServos(this, leftBoot, rightBoot, gecko);
        shooter = new Shooter(this, flywheel);

        //subsystems.add(odometry);
        //subsystems.add(PID);
        subsystems.add(drivetrain);
        subsystems.add(intake);
        subsystems.add(transfer);
        subsystems.add(servos);
        subsystems.add(shooter);

        for (Subsystem s: subsystems){
            s.init();

    }}

    /*
     * Code to be run every frame after initialization but before start.
     */
    public void init_loop(){
        for (Subsystem s: subsystems){
            s.init_loop();
        }
    }

    /*
     * Code to be run once on start.
     */
    public void start(){
        for (Subsystem s: subsystems){
            s.clearTelemetry();
            s.start();
        }
    }

    /*
     * Code to be run every frame after start.
     */
    public void loop(){
        for (Subsystem s: subsystems){
            s.loop();
        }
    }

    /*
     * Iterating through the list of subsystems to get their telemetry maps and then iterating
     * through each subsystems telemetry map, getting the captions and the values of their telemetry,
     * and adding it to the Teleop's telemetry.
     */
    public void addTelemetry(Telemetry telemetry){
        for (Subsystem s: subsystems){
            Map<String, Object> telemetryData = s.getTelemetry();

            for (Map.Entry<String, Object> entry : telemetryData.entrySet()){
                String caption = entry.getKey();
                Object value = entry.getValue();

                telemetry.addData(caption, value);
            }
        }
    }
    public double getVelocity(DcMotorEx motor){
        return motor.getVelocity();
    }
}
