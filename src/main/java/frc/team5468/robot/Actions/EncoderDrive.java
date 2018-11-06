package frc.team5468.robot.Actions;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5468.robot.RobotMap;

public class EncoderDrive extends Action {
    double leftInches, rightInches;
    double power;
    double leftTarget, rightTarget;
    boolean isFirstRun = true;

    public EncoderDrive(double leftInchTarget, double rightInchTarget, double powerTarget){
        leftInches = leftInchTarget;
        rightInches = rightInchTarget;
        power = powerTarget;
    }

    public void init(){

    }

    public void run(){
        while(true) {
            while (leftPos() < leftTarget) {
                SmartDashboard.putNumber("LEFT ENCODER", RobotMap.leftDrive.getSelectedSensorPosition(0));
                SmartDashboard.putNumber("RIGHT ENCODER", RobotMap.rightDrive.getSelectedSensorPosition(0));
            }
        }
    }

    public void toTicks(double inch){

    }

    public double leftPos(){
        return RobotMap.leftDrive.getSelectedSensorPosition(0);
    }
    public double rightPos(){
        return RobotMap.rightDrive.getSelectedSensorPosition(0);
    }

}
