package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveByGyro extends Command implements CommandInterface {

    private double angle, power, targetAngle;
    //Direction is defined by 1 being clockwise and -1 being counter-clockwise
    private double direction;
    private final double THRESHOLD = 1;
    private double error;

    public MoveByGyro(double angle, double power, boolean isClockwise) {
        requires(subsystems.drivetrain);
        this.angle = Math.abs(angle);
        this.power = Math.abs(power);
        if(isClockwise){
            direction = -1;
        }
        else{
            direction = 1;
        }
    }

    @Override
    protected void initialize() {
        targetAngle = subsystems.drivetrain.getPigeonYaw() + this.angle;
    }

    @Override
    protected void execute() {
        error = targetAngle - subsystems.drivetrain.getPigeonYaw();
        while((error > THRESHOLD)||(error > THRESHOLD)) {
            subsystems.drivetrain.robotDrive.tankDrive(power * direction, -power * direction);
            error = targetAngle - subsystems.drivetrain.getPigeonYaw(); 
        }
        subsystems.drivetrain.robotDrive.tankDrive(0, 0);
    }

    @Override
    protected boolean isFinished() {
        //dont work
        return Math.abs(error) <= THRESHOLD;
    }

}