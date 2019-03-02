package frc.robot.drivetrain.Move;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drivetrain.Drivetrain;

public class MoveToPosition extends Command {

    private Drivetrain drivetrain = Drivetrain.getInstance();

    private final double THRESHOLD = 10;
    private double position, power;


    public MoveToPosition(double distance, double power) {
        requires(drivetrain);
        setInterruptible(true);
        position = distance;
        this.power = power;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        drivetrain.runToPosition(position, THRESHOLD, power);
               
    }

   @Override
   protected void end() {
        drivetrain.stopRobot(); 
   }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}