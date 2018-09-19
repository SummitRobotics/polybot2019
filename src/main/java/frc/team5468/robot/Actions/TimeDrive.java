package frc.team5468.robot.Actions;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Timer;
import frc.team5468.robot.RobotMap;

public class TimeDrive extends Action {

    double leftPower, rightPower;
    double time, startTime, currentTime;
    boolean isFirstRun = true;

    public TimeDrive (double leftPwr, double rightPwr, double seconds){
        leftPower = leftPwr;
        rightPower = rightPwr;
        time = seconds;
    }

    public void init(){

    }

    public void run() {
        if (isFirstRun) {
            startTime = Timer.getFPGATimestamp();
            isFirstRun = false;
        }
        if((Timer.getFPGATimestamp() - startTime) < time){
            RobotMap.leftDrive.set(ControlMode.PercentOutput, leftPower);
            RobotMap.rightDrive.set(ControlMode.PercentOutput, rightPower);
        }
        else{
            RobotMap.leftDrive.set(ControlMode.PercentOutput, 0);
            RobotMap.rightDrive.set(ControlMode.PercentOutput, 0);
            isDone = true;
        }
    }
/*
    public void run() {
        if (isFirstRun) {
            startTime = Timer.getFPGATimestamp();

            RobotMap.leftDrive.set(ControlMode.PercentOutput, leftPower);
            RobotMap.rightDrive.set(ControlMode.PercentOutput, rightPower);
            isFirstRun = false;
        }
        if((Timer.getFPGATimestamp() - startTime) > time){
            RobotMap.leftDrive.set(ControlMode.PercentOutput, 0);
            RobotMap.rightDrive.set(ControlMode.PercentOutput, 0);
            isDone = true;
        }
        else{
            isDone = false;
        }
    }  Owen's Suggestion to set power in first loop and disable power when done, instead of continously setting power */
}
