package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class MoveByNewGyro extends Command implements CommandInterface {
    private double angle, power, targetAngle;
    private double direction;
    
    public MoveByNewGyro(double angle, double power) {
        requires(subsystems.drivetrain);
        this.angle = angle;
        this.power = Math.abs(power);
        direction = Math.copySign(1, angle);
    }

    @Override
    protected void initialize() {
        targetAngle = angle + subsystems.drivetrain.getPigeonYaw();
    }

    @Override
    protected void execute() {
        while((subsystems.drivetrain.getPigeonYaw() < targetAngle) || (subsystems.drivetrain.getPigeonYaw() > targetAngle)){
            subsystems.drivetrain.robotDrive.tankDrive(power, power * direction);
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
        return subsystems.drivetrain.getGyroRotation() == targetAngle;
    }

}