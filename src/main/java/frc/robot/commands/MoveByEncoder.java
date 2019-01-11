package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;

public class MoveByEncoder extends Command implements CommandInterface {

    private Drivetrain drivetrain = subsystems.drivetrain;

    private double leftInch, rightInch;
    private double leftInitPosition, rightInitPosition;
    private double leftTarget, rightTarget;
    private double power;

    public MoveByEncoder(double distance, double power) {
        requires(drivetrain);
        leftInch = distance;
        rightInch = distance;
        this.power = power;
    }

    @Override
    protected void initialize() {
        leftInitPosition = drivetrain.getLeftEncoderPos();
        rightInitPosition = drivetrain.getRightEncoderPos();

        leftTarget = leftInitPosition + drivetrain.inchesToTicks(leftInch);
        rightTarget = rightInitPosition + drivetrain.inchesToTicks(rightInch);
    }

    @Override
    protected void execute() {
        while((drivetrain.getLeftEncoderPos() < leftTarget) && (drivetrain.getRightEncoderPos() < rightTarget)) {
            //todo - encoder invert
            drivetrain.robotDrive.tankDrive(-power, -power);
            postValues();
        }
    }

    @Override
    protected void end() {
        
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        //return ((Drivetrain.getLeftEncoderPos() >= leftTarget) || (Drivetrain.getRightEncoderPos() >= rightTarget));
        if (drivetrain.getLeftEncoderPos() >= leftTarget) {
            SmartDashboard.putString("Is Finished:", "True");
            return true;
        } else if (drivetrain.getRightEncoderPos() >= rightTarget) {
            SmartDashboard.putString("Is Finished:", "True");
            return true;
        } else {
            SmartDashboard.putString("Is Finished:", "False");
            return false;
        }
    }

    protected void postValues() {
        SmartDashboard.putNumber("Left Target:", leftTarget);
        SmartDashboard.putNumber("Right Target:", rightTarget);
        SmartDashboard.putNumber("Left Position", drivetrain.getLeftEncoderPos());
        SmartDashboard.putNumber("Right Position", drivetrain.getRightEncoderPos());
    }
}
