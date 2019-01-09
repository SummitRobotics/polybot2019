package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotBuilder;
import frc.robot.subsystems.Drivetrain;

public class MoveByEncoder extends Command {

    private double leftInch, rightInch;
    private double leftInitPosition, rightInitPosition;
    private double leftTarget, rightTarget;
    private double power;

    public MoveByEncoder(double distance, double power) {
        requires(RobotBuilder.drivetrain);
        leftInch = distance;
        rightInch = distance;
        this.power = power;
    }

    @Override
    protected void initialize() {
        leftInitPosition = Drivetrain.getLeftEncoderPos();
        rightInitPosition = Drivetrain.getRightEncoderPos();

        leftTarget = leftInitPosition + Drivetrain.inchesToTicks(leftInch);
        rightTarget = rightInitPosition + Drivetrain.inchesToTicks(rightInch);
    }

    @Override
    protected void execute() {
        while((Drivetrain.getLeftEncoderPos() < leftTarget) && (Drivetrain.getRightEncoderPos() < rightTarget)) {
            //todo - encoder invert
            Drivetrain.robotDrive.tankDrive(-power, -power);
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
        if (Drivetrain.getLeftEncoderPos() >= leftTarget) {
            SmartDashboard.putString("Is Finished:", "True");
            return true;
        } else if (Drivetrain.getRightEncoderPos() >= rightTarget) {
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
        SmartDashboard.putNumber("Left Position", Drivetrain.getLeftEncoderPos());
        SmartDashboard.putNumber("Right Position", Drivetrain.getRightEncoderPos());
    }
}
