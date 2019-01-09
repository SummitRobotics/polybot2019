package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotBuilder;
import frc.robot.subsystems.Drivetrain;

public class MoveByGyro extends Command {
    private double angle, power;
    //Direction is defined by 1 being clockwise and -1 being counter-clockwise
    private double direction;
    private double targetAngle;

    private Drivetrain drivetrain = Drivetrain.GetInstance();

    public MoveByGyro(double angle, double power) {
        requires(drivetrain);
        this.angle = angle;
        //We only ever want positive power, as the angle of the gyro will determine wether we go in the positive or negative direction.
        this.power = Math.abs(power);
        direction = Math.copySign(1, angle);
    }

    @Override
    protected void initialize() {
        targetAngle = angle + drivetrain.getGyroRotation();
    }

    @Override
    protected void execute() {
        while(roundAngle(drivetrain.getGyroRotation()) < roundAngle(targetAngle) || (roundAngle(drivetrain.getGyroRotation()) > roundAngle(targetAngle)));{
            drivetrain.robotDrive.tankDrive(power, power * direction);
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

    private double roundAngle(double angle) {
        double scale = Math.pow((10), 2);
        return Math.floor(angle * scale) / scale;
    }
}