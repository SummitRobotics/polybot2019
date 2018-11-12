package frc.team5468.robot.Actions;


//Based on old Action architecture, depreciated.
public class TimeDrive {
/*
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
            RobotMap.leftDriveMotor.set(ControlMode.PercentOutput, leftPower);
            RobotMap.rightDriveMotor.set(ControlMode.PercentOutput, rightPower);
        }
        else{
            RobotMap.leftDriveMotor.set(ControlMode.PercentOutput, 0);
            RobotMap.rightDriveMotor.set(ControlMode.PercentOutput, 0);

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
