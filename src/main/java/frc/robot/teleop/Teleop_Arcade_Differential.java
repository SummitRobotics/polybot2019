package frc.robot.teleop;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotBuilder;

public class Teleop_Arcade_Differential {

    OI gamepad;

    private double xSpeed, zRotation;
    private short red, green, blue;

    RobotBuilder subsystems = RobotBuilder.getInstance();

    public void init(){
        gamepad = new OI();
    }

    public void run(){

        xSpeed = gamepad.getLeftTrigger() - gamepad.getRightTrigger();
        zRotation = -gamepad.getLeftJoystickX();

        //Potentially implement curvatureDrive in the future?
        subsystems.drivetrain.robotDrive.arcadeDrive(xSpeed, zRotation);

        if(gamepad.isButtonA()){
            subsystems.testSystem.testServo.set(1);
        }
        else if(gamepad.isButtonB()){
            subsystems.testSystem.testServo.set(0.5);
        }
        else{
            subsystems.testSystem.testServo.set(0);
        }

        subsystems.colorSensor.read();
        this.red = subsystems.colorSensor.red;
        this.green = subsystems.colorSensor.green;
        this.blue = subsystems.colorSensor.blue;


        SmartDashboard.putNumber("Left Encoder", subsystems.drivetrain.getLeftEncoderPos());
        SmartDashboard.putNumber("Right Encoder", subsystems.drivetrain.getRightEncoderPos());
        SmartDashboard.putNumber("Left Velocity", subsystems.drivetrain.getLeftEncoderVel());
        SmartDashboard.putNumber("Right Velocity", subsystems.drivetrain.getRightEncoderVel());
        SmartDashboard.putString("Color", String.valueOf(red) + ", " + String.valueOf(green) + ", " + String.valueOf(blue));
    }
}