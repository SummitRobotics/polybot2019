package frc.robot.teleop;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.subsystems.Drivetrain;

public class Teleop_Arcade_Differential {

    OI gamepad;

    double xSpeed, zRotation;

    Drivetrain drivetrain = Drivetrain.GetInstance();

    public void init(){
        gamepad = new OI();
    }

    public void run(){

        xSpeed = gamepad.getLeftTrigger() - gamepad.getRightTrigger();
        zRotation = -gamepad.getLeftJoystickX();

        //Potentially implement curvatureDrive in the future?
        drivetrain.robotDrive.arcadeDrive(xSpeed, zRotation);
        SmartDashboard.putNumber("Left Encoder", drivetrain.getLeftEncoderPos());
        SmartDashboard.putNumber("Right Encoder", drivetrain.getRightEncoderPos());
        SmartDashboard.putNumber("Left Velocity", drivetrain.getLeftEncoderVel());
        SmartDashboard.putNumber("Right Velocity", drivetrain.getRightEncoderVel());

    }
}