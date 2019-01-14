package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class MoveByNewGyro extends Command implements CommandInterface {
    private double angle, power, targetAngle;
    private double direction;
    private final double threshold = 3;
    
    public MoveByNewGyro(double angle, double power) {
        requires(subsystems.drivetrain);
        this.angle = angle;
        this.power = Math.abs(power);
        direction = Math.copySign(1, angle);
    }

    @Override
    protected void initialize() {
        targetAngle = getAngleError(Drivetrain.getPigeonYaw(), this.angle);
    }

    @Override
    protected void execute() {
         while(!isWithinThreshold()){
                Drivetrain.robotDrive.tankDrive(power * direction, -power * direction);
            }
         Drivetrain.robotDrive.tankDrive(0, 0);
    }

    @Override
    protected void end() {
        
    }

    @Override
    protected void interrupted() {
        
    }

    @Override
    protected boolean isFinished() {
        return subsystems.drivetrain.getGyroRotation() == targetAngle;
    }

    private double getAngleError(double currentAngle, double expectedAngle){
        return expectedAngle - currentAngle;
    }

    private boolean isWithinThreshold(){
        return getAngleError(Drivetrain.getPigeonYaw(), targetAngle) < threshold;
    }


}