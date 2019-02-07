package frc.robot.commands.Vision;


import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.commands.CommandInterface;
import frc.robot.teleop.Teleop_Arcade_Differential;

public class TargetAlignment extends PIDCommand implements CommandInterface{
    private static final double
        P = 0.1,
        I = 0.01,
        D = 0.0;
    private final double kP = 0.0265;
    private double min_command = 0.05;
    private double leftFwd, rightFwd;


    public TargetAlignment(){
        super("TargetAlignment", P, I, D, robot.drivetrain);    
        setSetpoint(0);
        getPIDController().setPercentTolerance(5);
    }
//pranked
    @Override
    protected void initialize() {

    }
    @Override
    protected void execute() {
        double headingError = robot.lemonlight.getX();
        double steeringAdjust = 0.0;

        if(robot.lemonlight.getX() > 1.0){
            steeringAdjust = kP*headingError - min_command;
        }
        else if(robot.lemonlight.getX() < 1.0){
            steeringAdjust = kP*headingError + min_command;
        }
        leftFwd -= steeringAdjust;
        rightFwd += steeringAdjust;
        //SmartDashboard.putNumber("Pid Input", returnPIDInput());
        SmartDashboard.putNumber("Left Drive", leftFwd);
        SmartDashboard.putNumber("Right Drive", rightFwd);
    }
    @Override
    protected void usePIDOutput(double output) {
        double steeringAdjust = 0;
        leftFwd = OI.getInstance().getForwardPower() - OI.getInstance().getRotationalPower();
        rightFwd = OI.getInstance().getForwardPower() + OI.getInstance().getRotationalPower();

        if(robot.lemonlight.getX() > 1.0){
            steeringAdjust = output - min_command;
        }
        else if(robot.lemonlight.getX() == 0){
            steeringAdjust = 0;
        }
        else if(robot.lemonlight.getX() < 1.0){
            steeringAdjust = output + min_command;
        }
        leftFwd -= steeringAdjust;
        rightFwd += steeringAdjust;
        robot.drivetrain.robotDrive.tankDrive(leftFwd, rightFwd);
        SmartDashboard.putNumber("Output", steeringAdjust);
    }
    @Override
    protected boolean isFinished() {
        return !getPIDController().isEnabled();
    }
    @Override
    protected double returnPIDInput() {
        return -robot.lemonlight.getX() * kP;
    }
}