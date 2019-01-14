package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardOutput{
    public static void run(){
        SmartDashboard.putNumber("Gyro Angle", RobotBuilder.getInstance().drivetrain.getPigeonYaw());
    }
}