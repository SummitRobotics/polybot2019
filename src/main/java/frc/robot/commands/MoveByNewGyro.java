package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Drivetrain;

public class MoveByNewGyro extends Command implements CommandInterface {

    private Drivetrain drivetrain = Drivetrain.getInstance();
    private double angle, power, targetAngle;
    private double direction;
    private final double threshold = 3;
    
    public MoveByNewGyro(double angle, double power) {
        requires(drivetrain);
        this.angle = angle;
        this.power = Math.abs(power);
        direction = Math.copySign(1, angle);
    }

    @Override
    protected void initialize() {
        targetAngle = getAngleError(drivetrain.getPigeonYaw(), this.angle);
    }

    @Override
    protected void execute() {
         while(!isWithinThreshold()){
                drivetrain.robotDrive.tankDrive(power * direction, -power * direction);
            }
         drivetrain.robotDrive.tankDrive(0, 0);
    }

    @Override
    protected void end() {
        
    }

    @Override
    protected void interrupted() {
        
    }

    @Override
    protected boolean isFinished() {
        return subsystems.drivetrain.getGyroRotation() == targetAngle;
    }

    private double getAngleError(double currentAngle, double expectedAngle){
        return expectedAngle - currentAngle;
    }

    private boolean isWithinThreshold(){
        return getAngleError(drivetrain.getPigeonYaw(), targetAngle) < threshold;
    }


}