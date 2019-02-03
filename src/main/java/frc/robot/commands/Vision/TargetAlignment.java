package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConstants;
import frc.robot.commands.CommandInterface;

public class TargetAlignment extends PIDCommand implements CommandInterface{
    private static final double
        P = 0.3,
        I = 0.00,
        D = 0.0;
    private double power, direction;

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
        //pranked
    }
    @Override
    protected void usePIDOutput(double output) {
        if(returnPIDInput() < 0.0){
            direction = -1;
        }
        else{
            direction = 1;
        }
        double leftDrive = robot.gamepad.getForwardPower();
        double rightDrive = robot.gamepad.getForwardPower();
        /*robot.drivetrain.leftDrive.pidWrite(-output);
        robot.drivetrain.rightDrive.pidWrite(-output);*/
        robot.drivetrain.robotDrive.tankDrive(-power * output, power * output);
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