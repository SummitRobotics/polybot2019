package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardOutput{
    public static void run(){
        SmartDashboard.putNumber("Gyro Angle", RobotBuilder.getInstance().drivetrain.getPigeonYaw());
        SmartDashboard.putNumber("Target X", RobotBuilder.getInstance().lemonlight.getX());
        SmartDashboard.putNumber("Target Y", RobotBuilder.getInstance().lemonlight.getY());
        SmartDashboard.putNumber("Target Area", RobotBuilder.getInstance().lemonlight.getArea());
    }
}