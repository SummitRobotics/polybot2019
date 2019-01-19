package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestMast extends Command implements CommandInterface{
    private double power, time;
    private double currentTime, timeError;

    public TestMast(double power, double time){
        requires(subsystems.revController);
        power = this.power;
        time = this.time;
        setTimeout(time);
    }

    @Override
    protected void initialize() {
        currentTime = Timer.getFPGATimestamp();
    }

    @Override
    protected void execute() {
        timeError = Timer.getFPGATimestamp() - currentTime;
        while(true){
            SmartDashboard.putNumber("Power", power);
        }
    }

    @Override
    protected boolean isFinished() {
        return timeError == time; 
    }

    @Override
    protected void end() {
        
    }
    @Override
    protected void interrupted() {
        
    }
}