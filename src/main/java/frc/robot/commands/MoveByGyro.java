package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class MoveByGyro extends Command implements CommandInterface {

    private double angle, power, targetAngle;
    //Direction is defined by 1 being clockwise and -1 being counter-clockwise
    private double direction;
    double threshold = 3;
    double error;

    public MoveByGyro(double angle, double power) {
        requires(subsystems.drivetrain);
        this.angle = angle;
        //We only ever want positive power, as the angle of the gyro will determine wether we go in the positive or negative direction.
        this.power = Math.abs(power);
        direction = Math.copySign(1, angle);
        targetAngle = subsystems.drivetrain.getGyroRotation();
    }

    @Override
    protected void initialize() {
    
    }

    @Override
    protected void execute() {
        double error = targetAngle - subsystems.drivetrain.getGyroRotation();

        while(error < threshold) {
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
        return (targetAngle - subsystems.drivetrain.getGyroRotation()) <= threshold;
    }

}