package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardOutput{
    public RobotBuilder robot = RobotBuilder.getInstance();
    double initTime;

    public void run(){
        SmartDashboard.putNumber("Gyro Angle", robot.drivetrain.getPigeonYaw());
        SmartDashboard.putNumber("Target X", robot.lemonlight.getX());
        SmartDashboard.putNumber("Target Y", robot.lemonlight.getY());
        SmartDashboard.putNumber("Target Area", robot.lemonlight.getArea());
        SmartDashboard.putNumber("Velocity", getVelocity());
        SmartDashboard.putNumber("Left Encoder", robot.drivetrain.getLeftEncoderPos());
        SmartDashboard.putNumber("Right Encoder", robot.drivetrain.getRightEncoderPos());
    }
    
    public double getVelocity(){
        return (robot.drivetrain.getLeftEncoderVel() + robot.drivetrain.getRightEncoderVel()) / 2;
    }
}