package frc.team5468.robot.Teleop;

import frc.team5468.robot.OI;
import frc.team5468.robot.RobotMap;

public class Teleop_Arcade_Differential {

    OI gamepad;

    double xSpeed, zRotation;

    public void init(){
        gamepad = new OI();
    }

    public void run(){

        xSpeed = gamepad.getRightTrigger() - gamepad.getLeftTrigger();
        zRotation = gamepad.getRightJoystickX();

        RobotMap.robotDrive.arcadeDrive(xSpeed,zRotation);
    }


}
