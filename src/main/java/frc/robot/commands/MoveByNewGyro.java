package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotBuilder;
import frc.robot.subsystems.Drivetrain;

public class MoveByNewGyro extends Command {
    private double angle, power, targetAngle;
    private double direction;
    
    public MoveByNewGyro(double angle, double power) {
        requires(RobotBuilder.drivetrain);
        this.angle = angle;
        this.power = Math.abs(power);
        direction = Math.copySign(1, angle);
    }

    @Override
    protected void initialize() {
        targetAngle = angle + Drivetrain.getPigeonYaw();
        SmartDashboard.putNumber("Gyro", Drivetrain.getPigeonYaw());
    }

    @Override
    protected void execute() {
        while(/*(Drivetrain.getPigeonYaw() < tolerance(targetAngle)) || (Drivetrain.getPigeonYaw() > tolerance(targetAngle))*/ 
                Drivetrain.getPigeonYaw() != tolerance(targetAngle)){
            Drivetrain.robotDrive.tankDrive(power * direction, power * -direction);
            SmartDashboard.putNumber("Gyro",Drivetrain.getPigeonYaw());
        }
        Drivetrain.robotDrive.stopMotor();
    }

    @Override
    protected void end() {
        
    }

    @Override
    protected void interrupted() {
        
    }

    @Override
    protected boolean isFinished() {
        return Drivetrain.getGyroRotation() ==tolerance(targetAngle) || Drivetrain.getGyroRotation() == tolerance(targetAngle);
    }

    /*private double roundAngle(double angle) {
        double scale = Math.pow((10), 2);
        return Math.floor(angle * scale) / scale;
    }*/

    private double tolerance(double angle){
        return Math.round(angle);
    }

}