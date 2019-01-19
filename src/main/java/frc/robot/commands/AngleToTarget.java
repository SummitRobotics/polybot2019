package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class AngleToTarget extends Command implements CommandInterface{
    private double direction, power;
    double threshold = 1;

    public AngleToTarget(double power){
        this.power = power;
    }

    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {
        direction = Math.copySign(1, subsystems.lemonlight.getX());

        while((subsystems.lemonlight.getX() < -threshold) || (subsystems.lemonlight.getX() > threshold)){
            subsystems.drivetrain.robotDrive.tankDrive(power * direction, -power * direction);
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
        return false;
    }
}