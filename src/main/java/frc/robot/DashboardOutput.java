package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;

public class DashboardOutput{
    public static void run(){
        SmartDashboard.putNumber("Gyro Angle", Drivetrain.getPigeonYaw());
    }
}