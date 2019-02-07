package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.CommandInterface;

public class TurnToTarget extends Command implements CommandInterface{
    private final double THRESHOLD = 0.5;
    private double power, direction;
    private double initAngle, targetAngle, error;

    public TurnToTarget(double power){
        this.power = power;
    }

    @Override
    protected void initialize() {
        direction = Math.copySign(1, robot.lemonlight.getX());
        initAngle = robot.lemonlight.getX();
        targetAngle = initAngle + robot.drivetrain.getPigeonYaw();
    }

    @Override
    protected void execute() {
        error = targetAngle - robot.drivetrain.getPigeonYaw();
        SmartDashboard.putNumber("Error", error);
        SmartDashboard.putBoolean("IsExecuting", !isFinished());
        while((robot.lemonlight.getX() > THRESHOLD) || (robot.lemonlight.getX() < -THRESHOLD)){
            robot.drivetrain.robotDrive.tankDrive(-power * direction, power * direction);
        }
        robot.drivetrain.stopRobot();
        
    }

    @Override
    protected boolean isFinished() {
        return ((robot.lemonlight.getX() <= THRESHOLD) && (robot.lemonlight.getX() >= -THRESHOLD));
    }

}