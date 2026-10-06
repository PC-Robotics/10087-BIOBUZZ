package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp(name = "Robot Centric Driving", group = "Teleop")
public class Teleop extends OpMode {
    protected Robot robot;
    protected boolean isFieldCentric = false;

    @Override
    /* Code to run ONCE when the driver hits INIT
     *
     * Initializes Robot object.
     */
    public void init(){
        robot = new Robot();
        robot.init(hardwareMap);

        telemetry.addLine("Initialized");
    }

    /* Code to run REPEATEDLY after the driver hits INIT, but before they hit START.
     *
     * As of right now, I have nothing that needs to be done in the initialization loop, but in the
     * future that might be useful, so here the architecture is set up and ready to be used.
     */
    @Override
    public void init_loop(){
        robot.init_loop();
    }

    /* Code to run ONCE when the driver hits START
     */
    @Override
    public void start(){
        robot.start();
    }

    @Override
    public void loop(){
        robot.loop();
        if (isFieldCentric) {
            robot.drivetrain.fieldCentricDrive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        } else {
            robot.drivetrain.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        }
        if (gamepad1.options) robot.drivetrain.resetYaw();

        /*
         * Show the state, motor powers, and servo positions.
         */
        robot.addTelemetry(telemetry);
    }
}
