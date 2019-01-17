package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveByNewGyro extends Command implements CommandInterface {

    private double angle, power, targetAngle;
    private double direction;
    private final double threshold = 3;
    
    public MoveByNewGyro(double angle, double power, double timeout) {
        requires(subsystems.drivetrain);
        this.angle = angle;
        this.power = Math.abs(power);
        direction = Math.copySign(1, angle);
        setTimeout(timeout);
    }

    @Override
    protected void initialize() {
        targetAngle = getAngleError(subsystems.drivetrain.getPigeonYaw(), this.angle);
    }

    @Override
    protected void execute() {

         while(!isFinished()) {

                subsystems.drivetrain.robotDrive.tankDrive(power * direction, -power * direction);
                SmartDashboard.putBoolean("gyrotimedout", isTimedOut());
                SmartDashboard.putBoolean("NewGyroRunning", true);
            }
        SmartDashboard.putBoolean("NewGyroRunning", false);
         subsystems.drivetrain.robotDrive.tankDrive(0, 0);
    }

    @Override
    protected void end() {
        
    }

    @Override
    protected void interrupted() {
        
    }

    @Override
    protected boolean isFinished() {
        SmartDashboard.putBoolean("isTimedOut", isTimedOut());
        SmartDashboard.putBoolean("isWithinThreshold", isWithinThreshold());
        return isWithinThreshold() || (isTimedOut());
    }

    private double getAngleError(double currentAngle, double expectedAngle){
        return expectedAngle - currentAngle;

    }

    private boolean isWithinThreshold(){
        SmartDashboard.putNumber("Angle Current", subsystems.drivetrain.getPigeonYaw());
        SmartDashboard.putNumber("Target Angle", targetAngle);
        SmartDashboard.putNumber("Threshold", threshold);
        return getAngleError(subsystems.drivetrain.getPigeonYaw(), targetAngle) < threshold;
    }
}
//turn in circles bitch