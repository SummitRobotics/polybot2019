package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class MoveByEncoderNew extends Command implements CommandInterface{

    private double distance, power, direction;
    double leftTarget, rightTarget;
    double leftError, rightError;
    private final double THRESHOLD = 50;

    public MoveByEncoderNew(double power, double distance, boolean isReversed){
        if(isReversed){
            direction = -1;
        }
        else{
            direction = 1;
        }
        this.distance = Math.abs(distance);
        this.power = Math.abs(power);
    }

    @Override
    protected void initialize() {
        leftTarget = subsystems.drivetrain.inchesToTicks(distance) + subsystems.drivetrain.getLeftEncoderPos();
        rightTarget = subsystems.drivetrain.inchesToTicks(distance) + subsystems.drivetrain.getRightEncoderPos();
    }
    @Override
    protected void execute() {
        leftError = leftTarget - subsystems.drivetrain.getLeftEncoderPos();
        rightError = rightTarget - subsystems.drivetrain.getRightEncoderPos();

        while((leftError > THRESHOLD) || (rightError > THRESHOLD)){
            subsystems.drivetrain.robotDrive.tankDrive(power * direction, power * direction);
            leftError = leftTarget - subsystems.drivetrain.getLeftEncoderPos();
            rightError = rightTarget - subsystems.drivetrain.getRightEncoderPos();
        }
    }
    @Override
    protected boolean isFinished() {
        return ((leftError <= THRESHOLD) || (rightError <= THRESHOLD));
    }
}