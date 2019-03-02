package frc.robot.drivetrain.Move;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drivetrain.Drivetrain;

public class MoveByEncoder extends Command {

    private Drivetrain drivetrain = Drivetrain.getInstance();

    private double leftInch, rightInch;
    private double leftInitPosition, rightInitPosition;
    private double leftTarget, rightTarget;
    private double power;
    private double leftDirection, rightDirection;
    private double leftError, rightError;
    private double THRESHOLD = 35;


    public MoveByEncoder(double distance, double power) {
        requires(drivetrain);
        setInterruptible(true);
        leftInch = distance;
        rightInch = distance;
        this.power = Math.abs(power);
    }

    @Override
    protected void initialize() {
        leftInitPosition = drivetrain.getLeftEncoderPos();
        rightInitPosition = drivetrain.getRightEncoderPos();

        leftTarget = leftInitPosition + drivetrain.inchesToTicks(leftInch);
        rightTarget = rightInitPosition + drivetrain.inchesToTicks(rightInch);

        leftError = leftTarget - drivetrain.getLeftEncoderPos();
        rightError = rightTarget - drivetrain.getRightEncoderPos();

        leftDirection = Math.copySign(1, leftError);
        rightDirection = Math.copySign(1, rightError);
    }

    @Override
    protected void execute() {
            while(((leftError > THRESHOLD) || (leftError < -THRESHOLD)) && ((rightError > THRESHOLD) || (rightError < -THRESHOLD))){
                drivetrain.robotDrive.tankDrive(power * leftDirection, power * rightDirection);
                leftError = leftTarget - drivetrain.getLeftEncoderPos();
                rightError = rightTarget - drivetrain.getRightEncoderPos();
            }
        drivetrain.stopRobot();        
    }

   @Override
   protected void end() {
        drivetrain.robotDrive.tankDrive(0,0);
        super.end();
   }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return ((leftError <= THRESHOLD) && (leftError >= -THRESHOLD)
                ||(rightError <= THRESHOLD) && (rightError >= -THRESHOLD));
    }

}
