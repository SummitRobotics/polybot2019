package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class TestMast extends Command implements CommandInterface{
    private double power, time, timestamp;

    public TestMast(double power, double time){
        requires(subsystems.revController);
        power = this.power;
        time = this.time;
        setTimeout(time);
    }

    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {
        while(!isFinished()){
            subsystems.revController.sparkMaxTest.set(power);
        }
    }

    @Override
    protected boolean isFinished() {
        return timeSinceInitialized() < time;
    }

    @Override
    protected void end() {
        
    }
    @Override
    protected void interrupted() {
        
    }
}