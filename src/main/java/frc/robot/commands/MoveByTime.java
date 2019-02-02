package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveByTime extends Command implements CommandInterface {

    private double power, time, initTime, deltaTime;

    public MoveByTime(double power, double time){
        requires(subsystems.drivetrain);
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
            subsystems.drivetrain.robotDrive.tankDrive(power, power);
            SmartDashboard.putNumber("Delta Time", deltaTime);
            deltaTime = Timer.getFPGATimestamp() - initTime;
        }
        subsystems.drivetrain.robotDrive.tankDrive(0, 0);
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
