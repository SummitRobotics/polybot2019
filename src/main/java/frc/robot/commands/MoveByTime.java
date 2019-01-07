package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class MoveByTime extends Command {

    private double power, time;

    public MoveByTime(double power, double time){
        requires(Robot.drivetrain);
        this.power = power;
        this.time = time;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        while(timeSinceInitialized() < time){
            Drivetrain.robotDrive.tankDrive(power, power);
        }
    }

    protected boolean isFinished(){
        return (timeSinceInitialized() >= time);
    }

    @Override
    protected void end() {
        
    }

    @Override
    protected void interrupted() {
        end();
    }
}
