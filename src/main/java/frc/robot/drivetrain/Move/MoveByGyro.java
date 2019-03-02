package frc.robot.drivetrain.Move;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drivetrain.Drivetrain;

public class MoveByGyro extends Command{

    private Drivetrain drivetrain = Drivetrain.getInstance();


    private double angle, power, targetAngle;
    //Direction is defined by 1 being clockwise and -1 being counter-clockwise
    private double direction;
    private final double THRESHOLD = 3;
    private double error;

    public MoveByGyro(double angle, double power) {
        requires(drivetrain);
        this.angle = angle;
        this.power = power;
    }

    @Override
    protected void initialize() {
        targetAngle = drivetrain.getGyroRot() + angle;
        error = targetAngle - drivetrain.getGyroRot();
        direction = Math.copySign(1, error);
    }

    @Override
    protected void execute() {
        SmartDashboard.putNumber("InitError", error);
        while((error > THRESHOLD)||(error < -THRESHOLD)) {
            drivetrain.robotDrive.tankDrive(-power * direction, power * direction);
            error = targetAngle - drivetrain.getGyroRot(); 
        }
        drivetrain.stopRobot();
    }

    @Override
    protected boolean isFinished() {
        return ((error <= THRESHOLD) && (error >= -THRESHOLD));
    }

}