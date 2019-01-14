package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotBuilder;
import frc.robot.subsystems.RevController;

public class TestMast extends Command{
    private double power, time, timestamp;

    public TestMast(double power, double time){
        requires(RobotBuilder.revController);
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
            RevController.sparkMaxTest.set(power);
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