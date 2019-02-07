package frc.robot.commands.Move;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.CommandInterface;

public class MoveByEncoder extends Command implements CommandInterface {

    private double leftInch, rightInch;
    private double leftInitPosition, rightInitPosition;
    private double leftTarget, rightTarget;
    private double power;
    private double leftDirection, rightDirection;
    private double leftError, rightError;
    private double THRESHOLD = 35;


    public MoveByEncoder(double distance, double power) {
        requires(robot.drivetrain);
        setInterruptible(true);
        leftInch = distance;
        rightInch = distance;
        this.power = Math.abs(power);
    }

    @Override
    protected void initialize() {
        leftInitPosition = robot.drivetrain.getLeftEncoderPos();
        rightInitPosition = robot.drivetrain.getRightEncoderPos();

        leftTarget = leftInitPosition + robot.drivetrain.inchesToTicks(leftInch);
        rightTarget = rightInitPosition + robot.drivetrain.inchesToTicks(rightInch);

        leftError = leftTarget - robot.drivetrain.getLeftEncoderPos();
        rightError = rightTarget - robot.drivetrain.getRightEncoderPos();

        leftDirection = Math.copySign(1, leftError);
        rightDirection = Math.copySign(1, rightError);
    }

    @Override
    protected void execute() {
            while(((leftError > THRESHOLD) || (leftError < -THRESHOLD)) && ((rightError > THRESHOLD) || (rightError < -THRESHOLD))){
                robot.drivetrain.robotDrive.tankDrive(power * leftDirection, power * rightDirection);
                leftError = leftTarget - robot.drivetrain.getLeftEncoderPos();
                rightError = rightTarget - robot.drivetrain.getRightEncoderPos();
            }
        robot.drivetrain.stopRobot();        
    }

   @Override
   protected void end() {
        robot.drivetrain.robotDrive.tankDrive(0,0);
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
