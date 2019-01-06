package frc.old.robot.Teleop;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.old.robot.OI;
import frc.old.robot.Subsystems.Drivetrain;

public class Teleop_Arcade_Differential {

    OI gamepad;

    double xSpeed, zRotation;

    public void init(){
        gamepad = new OI();
    }

    public void run(){

        xSpeed = gamepad.getLeftTrigger() - gamepad.getRightTrigger();
        zRotation = -gamepad.getLeftJoystickX();

        //Potentially implement curvatureDrive in the future?
        Drivetrain.robotDrive.arcadeDrive(xSpeed, zRotation);
        SmartDashboard.putNumber("Left Encoder", Drivetrain.getLeftEncoderPos());
        SmartDashboard.putNumber("Right Encoder", Drivetrain.getRightEncoderPos());
        SmartDashboard.putNumber("Left Velocity", Drivetrain.getLeftEncoderVel());
        SmartDashboard.putNumber("Right Velocity", Drivetrain.getRightEncoderVel());

    }
}