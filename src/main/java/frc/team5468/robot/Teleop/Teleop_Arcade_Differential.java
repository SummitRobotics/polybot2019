package frc.team5468.robot.Teleop;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5468.robot.OI;
import frc.team5468.robot.Subsystems.Drivetrain;

public class Teleop_Arcade_Differential {

    OI gamepad;

    double xSpeed, zRotation;

    MotorInputTest test = new MotorInputTest();

    public void init(){
        gamepad = new OI();
    }

    public void run(){

        xSpeed = gamepad.getLeftTrigger() - gamepad.getRightTrigger();
        zRotation = -gamepad.getLeftJoystickX();

        //Potentially implement curvatureDrive in the future?
        //turn in place?
        Drivetrain.robotDrive.arcadeDrive(xSpeed, zRotation);

        test.testValues(xSpeed, zRotation);

    }
}