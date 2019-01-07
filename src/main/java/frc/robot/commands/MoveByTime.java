package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotBuilder;
import frc.robot.subsystems.Drivetrain;

public class MoveByTime extends Command {

    private double power, time;

    public MoveByTime(double power, double time){
        requires(RobotBuilder.drivetrain);
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
