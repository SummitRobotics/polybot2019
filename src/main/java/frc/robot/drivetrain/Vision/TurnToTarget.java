package frc.robot.drivetrain.Vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.devices.Limelight;
import frc.robot.drivetrain.Drivetrain;

public class TurnToTarget extends Command {

    private Drivetrain drivetrain = Drivetrain.getInstance();
    private Limelight lemonlight = Limelight.getInstance();
    
    private final double THRESHOLD = 0.5;
    private double power, direction;
    private double initAngle, targetAngle, error;

    public TurnToTarget(double power){
        this.power = power;
    }

    @Override
    protected void initialize() {
        direction = Math.copySign(1, lemonlight.getX());
        initAngle = lemonlight.getX();
        targetAngle = initAngle + drivetrain.getPigeonYaw();
    }

    @Override
    protected void execute() {
        error = targetAngle - drivetrain.getPigeonYaw();
        SmartDashboard.putNumber("Error", error);
        SmartDashboard.putBoolean("IsExecuting", !isFinished());
        while((lemonlight.getX() > THRESHOLD) || (lemonlight.getX() < -THRESHOLD)){
            drivetrain.robotDrive.tankDrive(-power * direction, power * direction);
        }
        drivetrain.stopRobot();
        
    }

    @Override
    protected boolean isFinished() {
        return ((lemonlight.getX() <= THRESHOLD) && (lemonlight.getX() >= -THRESHOLD));
    }

}