package frc.team5468.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5468.robot.Robot;
import frc.team5468.robot.Subsystems.Drivetrain;

public class EncoderDrive extends Command {

    private double leftInch, rightInch;
    private double leftInitPosition, rightInitPosition;
    private double leftTarget, rightTarget;
    private double power;

    public EncoderDrive(double distance, double power){
        requires(Robot.Drivetrain);
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
        while((Drivetrain.getLeftEncoderPos() < leftTarget) && (Drivetrain.getRightEncoderPos() < rightTarget)){
            Drivetrain.robotDrive.tankDrive(power, power);
        }
    }

    @Override
    protected void end() {
        //Drivetrain.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return ((Drivetrain.getLeftEncoderPos() >= leftTarget) || (Drivetrain.getRightEncoderPos() >= rightTarget));
    }
}
