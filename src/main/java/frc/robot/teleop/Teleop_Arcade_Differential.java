package frc.robot.teleop;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotBuilder;
import frc.robot.commands.Vision.TargetAlignment;

public class Teleop_Arcade_Differential {

    static double leftFwd, rightFwd;
    double zRotation, xSpeed;
    double Kp = 0.0265;
    double min_command = 0.05;

    RobotBuilder robot = RobotBuilder.getInstance();
    OI gamepad = OI.getInstance();

    public void init(){
        
    }

    public void run(){
        
        while(gamepad.isButtonB()){
            leftFwd = gamepad.getForwardPower() - gamepad.getRotationalPower();
            rightFwd = gamepad.getForwardPower() + gamepad.getRotationalPower();
            calculateVisionAlign();
            robot.drivetrain.robotDrive.tankDrive(leftFwd, rightFwd);
        }

        zRotation = gamepad.getRotationalPower();
        xSpeed = gamepad.getForwardPower();
        robot.drivetrain.robotDrive.arcadeDrive(xSpeed, zRotation);
        

        //Potentially implement curvatureDrive in the future?
        
        

    }

    public void calculateVisionAlign(){
        double headingError = robot.lemonlight.getX();
        double steeringAdjust = 0.0;

        if(robot.lemonlight.getX() > 1.0){
            steeringAdjust = Kp*headingError - min_command;
        }
        else if(robot.lemonlight.getX() < 1.0){
            steeringAdjust = Kp*headingError + min_command;
        }
        leftFwd -= steeringAdjust;
        rightFwd += steeringAdjust;
    }

}