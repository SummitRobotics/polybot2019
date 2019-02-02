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
        if(subsystems.lemonlight.getX() > 0){
            new MoveByGyro(subsystems.lemonlight.getX(), power, false);
        }
        else if(subsystems.lemonlight.getX() < 0){
            new MoveByGyro(subsystems.lemonlight.getX(), power, true);
        }
        else{
            subsystems.drivetrain.robotDrive.tankDrive(0, 0);
        }
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(subsystems.lemonlight.getX()) <= THRESHOLD;
    }

}