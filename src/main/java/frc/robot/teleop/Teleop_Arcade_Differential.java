package frc.robot.teleop;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotBuilder;
import frc.robot.commands.Vision.TargetAlignment;

public class Teleop_Arcade_Differential {

    double xSpeed, zRotation;

    RobotBuilder robot = RobotBuilder.getInstance();

    public void init(){
        
    }

    public void run(){

        xSpeed = robot.gamepad.getForwardPower();
        zRotation = robot.gamepad.getRotationalPower();

        //Potentially implement curvatureDrive in the future?
        robot.drivetrain.robotDrive.arcadeDrive(xSpeed, -zRotation);

        robot.gamepad.triggerAlign();
        robot.gamepad.interruptCommand();
    }
}