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

        SmartDashboard.putNumber("Red", red/2);
        SmartDashboard.putNumber("Green", green/2);
        SmartDashboard.putNumber("Blue", blue);

        SmartDashboard.putNumber("Red Avg.", redAvg/count);
        SmartDashboard.putNumber("Green Avg.", greenAvg/count);
        SmartDashboard.putNumber("Blue Avg.", blueAvg/count);

        SmartDashboard.putBoolean("Detected", detect(red, green, blue));
    }

    public boolean detect(int red, int green, int blue) {

        red /= 2;
        green /= 2;
        
        int average = (red+green+blue)/3;

        if (average-20 < red && red < average+20 && average-20 < green && green < average+20 && average-20 < blue && blue < average+20 && red > 90 && green > 90 && blue > 90) {
            return true;
        }

        return false;
    }
}