package frc.team5468.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5468.robot.Robot;
import frc.team5468.robot.Subsystems.Drivetrain;

public class MoveByTime extends Command {

    private boolean isDone;
    private double power, time;

    public MoveByTime(double power, double time){
        requires(Robot.Drivetrain);
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
        if(timeSinceInitialized() >= time){
            isDone = true;
        }
    }

    protected boolean isFinished(){
        if(isDone)
            return true;
        else
            return false;
    }

    @Override
    protected void end() {
        Drivetrain.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
