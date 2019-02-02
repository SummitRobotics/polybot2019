package frc.robot.commands;

//TODO - make work someday with negative values.

public class MoveByEncoder/* extends Command implements CommandInterface*/ {

    /*private double leftInch, rightInch;
    private double leftInitPosition, rightInitPosition;
    private double leftTarget, rightTarget;
    private double power;
    private double leftDirection, rightDirection;
    private double leftError, rightError;
    private double THRESHOLD = 100;*/


    public MoveByEncoder(double distance, double power) {
        /*requires(subsystems.drivetrain);
        leftInch = distance;
        rightInch = distance;
        this.power = Math.abs(power);*/
    }

   /* @Override
    protected void initialize() {
        leftInitPosition = subsystems.drivetrain.getLeftEncoderPos();
        rightInitPosition = subsystems.drivetrain.getRightEncoderPos();
        SmartDashboard.putNumber("LeftInit", leftInitPosition);
        SmartDashboard.putNumber("RightInit", rightInitPosition);

        leftTarget = leftInitPosition + subsystems.drivetrain.inchesToTicks(leftInch);
        rightTarget = rightInitPosition + subsystems.drivetrain.inchesToTicks(rightInch);
        SmartDashboard.putNumber("LeftTarget", leftTarget);
        SmartDashboard.putNumber("RightTarget", rightTarget);

        leftError = leftTarget - subsystems.drivetrain.getLeftEncoderPos();
        rightError = rightTarget - subsystems.drivetrain.getRightEncoderPos();

        leftDirection = Math.copySign(1, leftError);
        rightDirection = Math.copySign(1, rightError);
        SmartDashboard.putNumber("leftError", leftError);
        SmartDashboard.putNumber("rightError", rightError);
    }

    @Override
    protected void execute() {
        //get initial errors at the start of execution
        SmartDashboard.putNumber("leftError", leftError);
        SmartDashboard.putNumber("rightError", rightError);

        while(((leftError) > THRESHOLD) || ((rightError) > THRESHOLD)) {
            subsystems.drivetrain.robotDrive.tankDrive(power * leftDirection, power * rightDirection);
            SmartDashboard.putNumber("direction", leftDirection);
            //continue feeding the error value as encoder positions draw closer to the target
            leftError = leftTarget - subsystems.drivetrain.getLeftEncoderPos();
            rightError = rightTarget - subsystems.drivetrain.getRightEncoderPos();
            SmartDashboard.putNumber("leftError", leftError);
            SmartDashboard.putNumber("rightError", rightError);
            SmartDashboard.putNumber("Pos", subsystems.drivetrain.getRightEncoderPos());
        }
        SmartDashboard.putBoolean("Done", isFinished());

    }

   @Override
   protected void end() {
        subsystems.drivetrain.robotDrive.tankDrive(0,0);
        super.end();
   }

    @Override
    protected void interrupted() {
        super.interrupted();
    }

    @Override
    protected boolean isFinished() {
        return (((leftError) <= THRESHOLD) || ((rightError) <= THRESHOLD));
    }*/

}
