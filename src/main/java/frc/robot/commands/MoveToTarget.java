package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotConstants;

public class MoveToTarget extends Command implements CommandInterface{
    private double power, visionDistance;
    private double leftTarget, rightTarget;
    private double leftError, rightError;
    private final double THRESHOLD = 1;

    public MoveToTarget(double power){
        this.power = power;
    }

    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {
        //TODO - Alignment to line
        //TODO - Feed-forward system given updating camera position instead of fixed target
        visionDistance = subsystems.drivetrain.inchesToTicks(subsystems.lemonlight.getDistance(RobotConstants.HATCH_HEIGHT_TEST));
        leftTarget = visionDistance + subsystems.drivetrain.getLeftEncoderPos();
        rightTarget = visionDistance + subsystems.drivetrain.getRightEncoderPos();

        leftError = Math.abs(leftTarget - subsystems.drivetrain.getLeftEncoderPos());
        rightError = Math.abs(rightTarget - subsystems.drivetrain.getRightEncoderPos());

        while((leftError > THRESHOLD) || (rightError > THRESHOLD)){
            subsystems.drivetrain.robotDrive.tankDrive(power, power);
            leftError = leftTarget - subsystems.drivetrain.getLeftEncoderPos();
            rightError = rightTarget - subsystems.drivetrain.getRightEncoderPos();
        }
        subsystems.drivetrain.robotDrive.tankDrive(0,0);
    }

    @Override
    protected boolean isFinished(){
        return (subsystems.lemonlight.getY() >= 0) || (leftError <= THRESHOLD) || (rightError <= THRESHOLD);
    }
}