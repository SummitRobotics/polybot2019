package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConstants;
import frc.robot.commands.CommandInterface;

public class TargetAlignment extends PIDCommand implements CommandInterface{
    private static final double
        P = 0.15,
        I = 0.01,
        D = 0.0;
    private double power, direction, leftDrive, rightDrive;

    public TargetAlignment(double power){
        super("TargetAlignment", P, I, D, robot.drivetrain);
        setSetpoint(0);
        getPIDController().setPercentTolerance(5);
        this.power = power;

    }
//pranked
    @Override
    protected void initialize() {

    }
    @Override
    protected void execute() {
        usePIDOutput(getPIDController().get());
        robot.drivetrain.robotDrive.tankDrive(leftDrive, rightDrive);
        //pranked
    }
    @Override
    protected void usePIDOutput(double output) {
        leftDrive = power;
        rightDrive = power;
        leftDrive += output;
        rightDrive -= output;
        SmartDashboard.putNumber("output", output);
    }
    @Override
    protected boolean isFinished() {
        return !getPIDController().isEnabled();
    }
    @Override
    protected double returnPIDInput() {
        return -robot.lemonlight.getX();
    }
}