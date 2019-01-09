package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotBuilder;
import frc.robot.subsystems.Drivetrain;

public class MoveByNewGyro extends Command {
    private double angle, power, targetAngle;
    private double direction;

    private Drivetrain drivetrain = Drivetrain.GetInstance();
    
    public MoveByNewGyro(double angle, double power) {
        requires(RobotBuilder.drivetrain);
        this.angle = angle;
        this.power = Math.abs(power);
        direction = Math.copySign(1, angle);
    }

    @Override
    protected void initialize() {
        targetAngle = angle + drivetrain.getPigeonYaw();
    }

    @Override
    protected void execute() {
        while((drivetrain.getPigeonYaw() < targetAngle) || (drivetrain.getPigeonYaw() > targetAngle)){
            drivetrain.robotDrive.tankDrive(power, power * direction);
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
        return drivetrain.getGyroRotation() == targetAngle;
    }

}