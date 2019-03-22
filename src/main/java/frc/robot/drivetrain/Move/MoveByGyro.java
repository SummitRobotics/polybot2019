package frc.robot.drivetrain.Move;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drivetrain.Drivetrain;

public class MoveByGyro extends PIDCommand{

    private Drivetrain drivetrain = Drivetrain.getInstance();

    private double angle, target;
    private static final double PERCENT_TOLERANCE = 5;
    private final double THRESHOLD = 1;
    private static final double
    P = 0.05,
    I = 0.005,
    D = 0.06;

    public MoveByGyro(double angle){
        super(P, I, D, Drivetrain.getInstance());
        this.angle = angle;
    }
    /*public GyroTurn(double angle, double radius, double power){
        
    }*/
    @Override
    protected void initialize() {
        target = angle + drivetrain.getPigeonYaw();
        setSetpoint(target);

    }
    @Override
    protected double returnPIDInput() {
        return drivetrain.getPigeonYaw();
    }
    @Override
    protected void usePIDOutput(double output) {
        SmartDashboard.putNumber("Gyro Output", output);
        SmartDashboard.putNumber("Error For Gyro", getPIDController().getError());
        drivetrain.robotDrive.tankDrive(output/1.4, -output/1.4);
    }

    @Override
    protected boolean isFinished() {
        //return (error < THRESHOLD) && (error > -THRESHOLD);
        //return getPIDController().onTarget();
        double error = getPIDController().getError();
        return (error < THRESHOLD) && (error > -THRESHOLD);
    }

    @Override
    protected void end() {
        super.end();
        //drivetrain.robotDrive.tankDrive(0,0);
    }

}