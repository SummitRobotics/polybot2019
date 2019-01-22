package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class MoveToTarget extends Command implements CommandInterface{
    private double power, targetDistance, error;
    private final double HATCH_HEIGHT = 28.5;
    private final double THRESHOLD = 1;

    public MoveToTarget(double power){
        this.power = power;
    }

    @Override
    protected void initialize() {
        targetDistance = subsystems.drivetrain.inchesToTicks(subsystems.lemonlight.getDistance(HATCH_HEIGHT)) + avgPosition();
        error = targetDistance - avgPosition();
    }

    @Override
    protected void execute() {
        while(error > targetDistance){
            subsystems.drivetrain.robotDrive.tankDrive(power, power);
            error = targetDistance - avgPosition();
        }
        subsystems.drivetrain.robotDrive.tankDrive(0,0);
    }

    @Override
    protected boolean isFinished(){
        return Math.abs(avgPosition() - targetDistance) < THRESHOLD;
    }
    private double avgPosition(){
        return (subsystems.drivetrain.getLeftEncoderPos() + subsystems.drivetrain.getRightEncoderPos()) /2;
    }
}