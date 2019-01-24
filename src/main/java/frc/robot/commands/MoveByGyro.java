package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveByGyro extends Command implements CommandInterface {

    private double angle, power, targetAngle;
    //Direction is defined by 1 being clockwise and -1 being counter-clockwise
    private double direction;
    private final double THRESHOLD = 1;
    private double error;

    public MoveByGyro(double angle, double power) {
        requires(subsystems.drivetrain);
        this.angle = angle;
        //We only ever want positive power, as the angle of the gyro will determine wether we go in the positive or negative direction.
        this.power = Math.abs(power);
        //SmartDashboard.putNumber("Target Angle", targetAngle);
    }

    @Override
    protected void initialize() {
        targetAngle = subsystems.drivetrain.getPigeonYaw() + this.angle;
        direction = Math.copySign(1, angle);
    }

    @Override
    protected void execute() {
        error = targetAngle - subsystems.drivetrain.getPigeonYaw();
        while((error > THRESHOLD)||(-error > THRESHOLD)) {
            subsystems.drivetrain.robotDrive.tankDrive(power * direction, -power * direction);
            error = targetAngle - subsystems.drivetrain.getPigeonYaw(); 
        }
        subsystems.drivetrain.robotDrive.tankDrive(0, 0);
    }

    @Override
    protected void end() {
        
    }

    @Override
    protected void interrupted() {
        
    }

    @Override
    protected boolean isFinished() {
        //dont work
        return Math.abs(error) <= THRESHOLD;
    }

}