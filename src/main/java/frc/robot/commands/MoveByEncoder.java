package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class MoveByEncoder extends Command implements CommandInterface {

    private double leftInch, rightInch;
    private double leftInitPosition, rightInitPosition;
    private double leftTarget, rightTarget;
    private double power, direction;
    private double leftError, rightError;
    private final double THRESHOLD = 1;

    public MoveByEncoder(double distance, double power) {
        requires(subsystems.drivetrain);
        leftInch = distance;
        rightInch = distance;
        this.power = Math.abs(power);
        this.direction = Math.copySign(1, distance);
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
        //get initial errors at the start of execution
        leftError = Math.abs(leftTarget - subsystems.drivetrain.getLeftEncoderPos());
        rightError = Math.abs(rightTarget - subsystems.drivetrain.getRightEncoderPos());

        while((leftError > THRESHOLD) || (rightError > THRESHOLD)) {
            subsystems.drivetrain.robotDrive.tankDrive(power * direction, power * direction);
            //continue feeding the error value as encoder positions draw closer to the target
            leftError = Math.abs(leftTarget - subsystems.drivetrain.getLeftEncoderPos());
            rightError = Math.abs(rightTarget - subsystems.drivetrain.getRightEncoderPos());
        }
        subsystems.drivetrain.robotDrive.tankDrive(0,0);
    }

   @Override
   protected void end() {
       super.end();
   }

    @Override
    protected void interrupted() {
        
    }

    @Override
    protected boolean isFinished() {
        return ((leftError <= THRESHOLD) || (rightError <= THRESHOLD));
    }

}
