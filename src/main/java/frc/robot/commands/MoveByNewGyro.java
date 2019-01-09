package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotBuilder;
import frc.robot.subsystems.Drivetrain;

public class MoveByNewGyro extends Command{
    private double angle, power, targetAngle;
    private double direction;
    
    public MoveByNewGyro(double angle, double power){
        requires(RobotBuilder.drivetrain);
        this.angle = angle;
        this.power = Math.abs(power);
        direction = Math.copySign(1, angle);
    }

    @Override
    protected void initialize() {
        targetAngle = angle + Drivetrain.getPigeonYaw();
    }

    @Override
    protected void execute() {
        while((Drivetrain.getPigeonYaw() < targetAngle) || (Drivetrain.getPigeonYaw() > targetAngle)){
            Drivetrain.robotDrive.tankDrive(power, power * direction);
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
        return Drivetrain.getGyroRotation() == targetAngle;
    }

}