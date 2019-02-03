package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TestMast extends Command implements CommandInterface{
    private double power, time;
    private double currentTime, deltaTime;

    public TestMast(double power, double time){
        requires(robot.mast);
        power = this.power;
        time = this.time;
        setTimeout(time++);
    }

    @Override
    protected void initialize() {
        currentTime = Timer.getFPGATimestamp();
    }

    @Override
    protected void execute() {
        deltaTime = Timer.getFPGATimestamp() - currentTime;
        while(deltaTime < time){
            robot.mast.testMotor.set(ControlMode.PercentOutput, power);
        }
    }

    @Override
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