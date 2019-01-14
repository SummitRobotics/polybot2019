package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveByEncoder extends Command implements CommandInterface {

    private double leftInch, rightInch;
    private double leftInitPosition, rightInitPosition;
    private double leftTarget, rightTarget;
    private double power;

    public MoveByEncoder(double distance, double power) {
        requires(subsystems.drivetrain);
        leftInch = distance;
        rightInch = distance;
        this.power = power;
    }

    @Override
    protected void initialize() {
        leftInitPosition = subsystems.drivetrain.getLeftEncoderPos();
        rightInitPosition = subsystems.drivetrain.getRightEncoderPos();

        leftTarget = leftInitPosition + subsystems.drivetrain.inchesToTicks(leftInch);
        rightTarget = rightInitPosition + subsystems.drivetrain.inchesToTicks(rightInch);
    }

    @Override
    protected void execute() {
        while((subsystems.drivetrain.getLeftEncoderPos() < leftTarget) && (subsystems.drivetrain.getRightEncoderPos() < rightTarget)) {
            //todo - encoder invert
            subsystems.drivetrain.robotDrive.tankDrive(-power, -power);
            postValues();
        }
        subsystems.drivetrain.robotDrive.tankDrive(0,0);
    }

    @Override
    protected void end() {
        
    }

    @Override
    protected void interrupted() {
        
    }

    @Override
    protected boolean isFinished() {
        return ((subsystems.drivetrain.getLeftEncoderPos() >= leftTarget) || (subsystems.drivetrain.getRightEncoderPos() >= rightTarget));
    }

    protected void postValues() {
        SmartDashboard.putNumber("Left Target:", leftTarget);
        SmartDashboard.putNumber("Right Target:", rightTarget);
        SmartDashboard.putNumber("Left Position", subsystems.drivetrain.getLeftEncoderPos());
        SmartDashboard.putNumber("Right Position", subsystems.drivetrain.getRightEncoderPos());
    }
}
