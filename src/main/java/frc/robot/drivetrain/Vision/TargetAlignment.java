package frc.robot.drivetrain.Vision;


import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.devices.Limelight;
import frc.robot.drivetrain.Drivetrain;

public class TargetAlignment extends PIDCommand {

    private Drivetrain drivetrain = Drivetrain.getInstance();
    private Limelight lemonlight = Limelight.getInstance();

    private static final double
        P = 0.01,
        I = 0.0,
        D = 0.0;
    //private final double kP = 0.0265;
    private double min_command = 0.0;
    private double leftFwd, rightFwd, power;


    public TargetAlignment(double power){
        super("TargetAlignment", P, I, D, Drivetrain.getInstance());    
        setSetpoint(0);
        getPIDController().setPercentTolerance(5);
        this.power = power;
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        SmartDashboard.putNumber("Left Drive", leftFwd);
        SmartDashboard.putNumber("Right Drive", rightFwd);
    }
    @Override
    protected void usePIDOutput(double output) {
        double steeringAdjust = 0;
        leftFwd = power;
        rightFwd = power;

        SmartDashboard.putNumber("Target", lemonlight.getTarget());

        if(lemonlight.getTarget() != 1){
            drivetrain.robotDrive.tankDrive(0, 0);
            end();
        }
        else if(lemonlight.getX() > 1.0){
            steeringAdjust = output - min_command;
            leftFwd += steeringAdjust;
            rightFwd -= steeringAdjust;
            drivetrain.robotDrive.tankDrive(leftFwd, rightFwd);
        }
        else if(lemonlight.getX() < 1.0){
            steeringAdjust = output + min_command;
            leftFwd += steeringAdjust;
            rightFwd -= steeringAdjust;
            drivetrain.robotDrive.tankDrive(leftFwd, rightFwd);
        }
        
        SmartDashboard.putNumber("Output", steeringAdjust);
        SmartDashboard.putBoolean("Yeah Done", isFinished());
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void end() {
        super.end();
    }
    @Override
    protected double returnPIDInput() {
        return lemonlight.getX();
    }
}