package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class TurnToTarget extends Command implements CommandInterface{
    private final double THRESHOLD = 0.5;
    private double power, direction;

    public TurnToTarget(double power){
        this.power = power;
    }

    @Override
    protected void initialize() {
        direction = Math.copySign(1, subsystems.lemonlight.getX());
    }

    @Override
    protected void execute() {
        //TODO - gyro alignment?
        while(Math.abs(subsystems.lemonlight.getX()) > THRESHOLD){
            subsystems.drivetrain.robotDrive.tankDrive(-power * direction, power * direction);
        }
        subsystems.drivetrain.robotDrive.tankDrive(0, 0);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(subsystems.lemonlight.getX()) <= THRESHOLD;
    }

}