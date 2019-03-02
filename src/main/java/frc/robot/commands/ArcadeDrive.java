package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;

public class ArcadeDrive extends Command implements CommandInterface{
    public ArcadeDrive(){
        requires(robot.drivetrain);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        robot.drivetrain.robotDrive.arcadeDrive(OI.getInstance().getForwardPower(), OI.getInstance().getRotationalPower());
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void end() {
        super.end();
    }
}