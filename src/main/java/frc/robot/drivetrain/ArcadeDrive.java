package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;

public class ArcadeDrive extends Command{
    private Drivetrain drivetrain = Drivetrain.getInstance();

    public ArcadeDrive(){
        requires(drivetrain);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        drivetrain.robotDrive.arcadeDrive(OI.getInstance().getForwardPower(), OI.getInstance().getRotationalPower());
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