package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveByEncoder extends Command{

    public MoveByEncoder(){

    }

    @Override
    protected void initialize(){
        requires(Robot.drivetrain);

    }

    @Override
    protected void execute(){

    }

    @Override
    protected boolean isFinished(){
        return false;
    }

    @Override
    protected void end(){

    }

    @Override
    protected void interrupted(){

    }

    
}