package frc.team5468.robot.Teleop;

import frc.team5468.robot.OI;
import frc.team5468.robot.Subsystems.Drivetrain;

public class Teleop_Arcade_Differential {

    OI gamepad;

    double xSpeed, zRotation;

    public void init(){
        gamepad = new OI();
    }

    public void run(){

        xSpeed = gamepad.getRightTrigger() - gamepad.getLeftTrigger();
        zRotation = gamepad.getRightJoystickX();

        //Potentially implement curvatureDrive in the future?
        Drivetrain.robotDrive.curvatureDrive(xSpeed,zRotation, true);
    }


}
