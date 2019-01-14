package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class MoveByGyro extends Command implements CommandInterface {
    private double angle, power;
    //Direction is defined by 1 being clockwise and -1 being counter-clockwise
    private double direction;
    private double targetAngle;

    public MoveByGyro(double angle, double power) {
        requires(subsystems.drivetrain);
        this.angle = angle;
        //We only ever want positive power, as the angle of the gyro will determine wether we go in the positive or negative direction.
        this.power = Math.abs(power);
        direction = Math.copySign(1, angle);
    }

    @Override
    protected void initialize() {
        targetAngle = angle + subsystems.drivetrain.getGyroRotation();
    }

    @Override
    protected void execute() {
        while(Drivetrain.getGyroRotation() < targetAngle || Drivetrain.getGyroRotation() >targetAngle){
            Drivetrain.robotDrive.tankDrive(power, power * direction); 
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
        return false;
    }

}