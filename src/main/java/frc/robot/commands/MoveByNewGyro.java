package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotBuilder;
import frc.robot.subsystems.Drivetrain;

public class MoveByNewGyro extends Command {
    private double angle, power, targetAngle;
    private double direction;
    private final double threshold = 3;
    
    public MoveByNewGyro(double angle, double power) {
        requires(RobotBuilder.drivetrain);
        this.angle = angle;
        this.power = Math.abs(power);
        direction = Math.copySign(1, angle);
    }

    @Override
    protected void initialize() {
        SmartDashboard.putNumber("Gyro", Drivetrain.getPigeonYaw());
        targetAngle = getAngleError(Drivetrain.getPigeonYaw(), this.angle);
    }

    @Override
    protected void execute() {
            while(!isWithinThreshold()){
                Drivetrain.robotDrive.tankDrive(power * direction, -power * direction);
            }
    }

    @Override
    protected void end() {
        
    }

    @Override
    protected void interrupted() {
        
    }

    @Override
    protected boolean isFinished() {
        return isWithinThreshold();
    }

    //todo - a better way
    private double getAngleError(double currentAngle, double expectedAngle){
        return expectedAngle - currentAngle;
    }

    private boolean isWithinThreshold(){
        return getAngleError(Drivetrain.getPigeonYaw(), targetAngle) < threshold;
    }

}