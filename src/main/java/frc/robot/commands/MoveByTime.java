package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;

public class MoveByTime extends Command implements CommandInterface {

    private double power, time;

    public MoveByTime(double power, double time){
        requires(subsystems.drivetrain);
        this.power = power;
        this.time = time;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        while(timeSinceInitialized() < time){
            subsystems.drivetrain.robotDrive.tankDrive(power, power);
        }
    }

    protected boolean isFinished() {
        return (timeSinceInitialized() >= time);
    }

    @Override
    protected void end() {
        
    }

    @Override
    protected void interrupted() {
       
    }
}
