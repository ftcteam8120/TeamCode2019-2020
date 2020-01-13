package org.firstinspires.ftc.teamcode.TeleOp.MechanumDrive;

import org.firstinspires.ftc.teamcode.RobotBrian;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "brianDrive")
public class brianDrive extends OpMode{
    private RobotBrian robot;
    private double speed;


    public void init() {
        robot = new RobotBrian();
        robot.init(hardwareMap);
        speed = 1;
    }

    public void loop() {
        // Routines separated into separate functions
        updateDriver();
        updateCodriver();
    }

    /**
     * Process gamepad1 (driver) controls
     */
    private void updateDriver() {

        // Set drive speed
        speed = .75;

        if (gamepad1.left_bumper)
            speed -= .25;
        if (gamepad1.right_bumper)
            speed += .25;

        // direction controls
        if (gamepad1.dpad_up) robot.forward(speed*.5);
        else if (gamepad1.dpad_right) robot.right(speed*.5);
        else if (gamepad1.dpad_down) robot.backward(speed*.5);
        else if (gamepad1.dpad_left) robot.left(speed*.5);
        else if (gamepad1.left_stick_y != 0 || gamepad1.left_stick_x != 0) {
            if (Math.abs(gamepad1.left_stick_y) > Math.abs(gamepad1.left_stick_x)) {
                if (gamepad1.left_stick_y > 0.1) {
                    //forward
                    robot.backward(speed);
                } else {
                    //backwards
                    robot.forward(speed);
                }
            } else if (gamepad1.left_stick_x > -0.1) {
                //right
                robot.right(speed);
            } else {
                //left
                robot.left(speed);
            }
        }
        else {
            robot.stop();
        }

        //spin
        if(gamepad1.right_stick_x>0.1){
            robot.spinright();
        }
        else if(gamepad1.right_stick_x<-0.1){
            robot.spinleft();
        }

        if (gamepad1.a){
            robot.impell(speed);
        }
        if(gamepad1.b){
            robot.expell(speed);
        }
        if (gamepad1.y){
            robot.stoppell();
        }

    }
    /**
     * Process gamepad2 (codriver) controls
     *
     */

        private void updateCodriver() {

            if(gamepad2.left_bumper){
                //up
                robot.moverDown();
            }
            else if (gamepad2.right_bumper){
                //down
                robot.moverUp();
            }
        }




}
