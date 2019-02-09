package frc.robot.commands.Move;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.CommandInterface;

public class MoveToPosition extends Command implements CommandInterface {

    private final double THRESHOLD = 10;
    private double position, power;


    public MoveToPosition(double distance, double power) {
        requires(robot.drivetrain);
        setInterruptible(true);
        position = distance;
        this.power = power;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        robot.drivetrain.runToPosition(position, THRESHOLD, power);
               
    }

   @Override
   protected void end() {
        robot.drivetrain.stopRobot(); 
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