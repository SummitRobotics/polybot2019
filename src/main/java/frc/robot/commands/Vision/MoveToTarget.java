package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.RobotConstants;
import frc.robot.commands.CommandInterface;

public class MoveToTarget extends Command implements CommandInterface{
    private double power, visionDistance;
    private double leftTarget, rightTarget;
    private double leftError, rightError;
    private final double THRESHOLD = 1;

    public MoveToTarget(double power){
        requires(robot.drivetrain);
        this.power = power;
    }

    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {
        //TODO - Alignment to line
        //TODO - Feed-forward system given updating camera position instead of fixed target
        visionDistance = robot.drivetrain.inchesToTicks(robot.lemonlight.getDistance(RobotConstants.HATCH_HEIGHT_TEST));
        leftTarget = visionDistance + robot.drivetrain.getLeftEncoderPos();
        rightTarget = visionDistance + robot.drivetrain.getRightEncoderPos();

        leftError = Math.abs(leftTarget - robot.drivetrain.getLeftEncoderPos());
        rightError = Math.abs(rightTarget - robot.drivetrain.getRightEncoderPos());

        while((leftError > THRESHOLD) || (rightError > THRESHOLD)){
            robot.drivetrain.robotDrive.tankDrive(power, power);
            leftError = leftTarget - robot.drivetrain.getLeftEncoderPos();
            rightError = rightTarget - robot.drivetrain.getRightEncoderPos();
        }
        robot.drivetrain.stopRobot();
    }

    @Override
    protected boolean isFinished(){
        return (robot.lemonlight.getY() >= 0) || (leftError <= THRESHOLD) || (rightError <= THRESHOLD);
    }
}