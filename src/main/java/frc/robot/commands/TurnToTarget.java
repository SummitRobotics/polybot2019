package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class TurnToTarget extends Command implements CommandInterface{
    private final double THRESHOLD = 1.5;
    private double power;

    public TurnToTarget(double power){
        this.power = power;
    }

    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {
        while(!isFinished()){
            subsystems.drivetrain.robotDrive.tankDrive(power, -power);
        }
        subsystems.drivetrain.robotDrive.tankDrive(0, 0);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(subsystems.lemonlight.getX()) < THRESHOLD;
    }

}