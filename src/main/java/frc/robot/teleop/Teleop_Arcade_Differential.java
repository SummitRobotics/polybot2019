package frc.robot.teleop;

import org.lwjgl.system.CallbackI.S;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotBuilder;

public class Teleop_Arcade_Differential {

    OI gamepad;

    private double xSpeed, zRotation;
    private short red, green, blue;
    private short redAvg = 0, greenAvg = 0, blueAvg = 0;
    
    //get color averages (temp)
    private int count = 1;
    private boolean toggleOne = false, toggleTwo = false;

    RobotBuilder subsystems = RobotBuilder.getInstance();

    public void init(){
        gamepad = new OI();
    }

    public void run(){

        xSpeed = gamepad.getLeftTrigger() - gamepad.getRightTrigger();
        zRotation = -gamepad.getLeftJoystickX();

        //Potentially implement curvatureDrive in the future?
        subsystems.drivetrain.robotDrive.arcadeDrive(xSpeed, zRotation);

        subsystems.colorSensor.read();
        this.red = subsystems.colorSensor.red;
        this.green = subsystems.colorSensor.green;
        this.blue = subsystems.colorSensor.blue;

        toggleTwo = toggleOne;
        toggleOne = gamepad.isButtonA();


        
        if (toggleOne && !toggleTwo) {

            redAvg += red;
            greenAvg += green;
            blueAvg += blue;

            count++;
        }

        SmartDashboard.putNumber("Left Encoder", subsystems.drivetrain.getLeftEncoderPos());
        SmartDashboard.putNumber("Right Encoder", subsystems.drivetrain.getRightEncoderPos());
        SmartDashboard.putNumber("Left Velocity", subsystems.drivetrain.getLeftEncoderVel());
        SmartDashboard.putNumber("Right Velocity", subsystems.drivetrain.getRightEncoderVel());

        SmartDashboard.putNumber("Red", red);
        SmartDashboard.putNumber("Green", green);
        SmartDashboard.putNumber("Blue", blue);

        SmartDashboard.putNumber("Red", red);
        SmartDashboard.putNumber("Green", green);
        SmartDashboard.putNumber("Blue", blue);

        SmartDashboard.putNumber("Red Avg.", redAvg/count);
        SmartDashboard.putNumber("Green Avg.", greenAvg/count);
        SmartDashboard.putNumber("Blue Avg.", blueAvg/count);

        SmartDashboard.putBoolean("Detected", red==200 && green==250 && blue==110);
    }
}