package frc.robot.drivetrain.Move;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drivetrain.Drivetrain;


public class MoveByTime extends Command {

    private Drivetrain drivetrain = Drivetrain.getInstance();

    private double power, time, initTime, deltaTime;

    public MoveByTime(double power, double time){
        requires(drivetrain);
        this.power = power;
        this.time = time;
    }

    @Override
    protected void initialize() {
        initTime = Timer.getFPGATimestamp();
    }

    @Override
    protected void execute() {
        deltaTime = Timer.getFPGATimestamp() - initTime;
        while(deltaTime < time){
            drivetrain.robotDrive.tankDrive(power, power);
            SmartDashboard.putNumber("Delta Time", deltaTime);
            deltaTime = Timer.getFPGATimestamp() - initTime;
        }
        drivetrain.stopRobot();
    }

    protected boolean isFinished() {
        return deltaTime >= time;
    }

    @Override
    protected void end() {
        super.end();
    }

    @Override
    protected void interrupted() {
       
    }
}
