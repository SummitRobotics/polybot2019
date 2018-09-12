package frc.team5468.robot.Actions;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Timer;
import frc.team5468.robot.RobotMap;

public class Drive extends Action {

    double leftPower, rightPower;
    double time, startTime, currentTime;
    boolean isFirstRun = true;

    public Drive (double leftPwr, double rightPwr, double seconds){
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
            isDone = true;
        }
    }
}
