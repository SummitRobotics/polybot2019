package frc.robot.drivetrain.Vision;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.RobotConstants;
import frc.robot.devices.Limelight;
import frc.robot.drivetrain.Drivetrain;

public class MoveToTarget extends Command {
    
    private Drivetrain drivetrain = Drivetrain.getInstance();
    private Limelight lemonlight = Limelight.getInstance();

    private double power, visionDistance;
    private double leftTarget, rightTarget;
    private double leftError, rightError;
    private final double THRESHOLD = 1;

    public MoveToTarget(double power){
        requires(drivetrain);
        this.power = power;
    }

    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {
        //TODO - Alignment to line
        //TODO - Feed-forward system given updating camera position instead of fixed target
        visionDistance = drivetrain.inchesToTicks(lemonlight.getDistance(RobotConstants.HATCH_HEIGHT_TEST));
        leftTarget = visionDistance + drivetrain.getLeftEncoderPos();
        rightTarget = visionDistance + drivetrain.getRightEncoderPos();

        leftError = Math.abs(leftTarget - drivetrain.getLeftEncoderPos());
        rightError = Math.abs(rightTarget - drivetrain.getRightEncoderPos());

        while((leftError > THRESHOLD) || (rightError > THRESHOLD)){
            drivetrain.robotDrive.tankDrive(power, power);
            leftError = leftTarget - drivetrain.getLeftEncoderPos();
            rightError = rightTarget - drivetrain.getRightEncoderPos();
        }
        drivetrain.stopRobot();
    }

    @Override
    protected boolean isFinished(){
        return (lemonlight.getY() >= 0) || (leftError <= THRESHOLD) || (rightError <= THRESHOLD);
    }
}