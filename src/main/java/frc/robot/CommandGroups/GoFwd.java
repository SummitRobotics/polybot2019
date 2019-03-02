package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.robot.drivetrain.Move.MoveByEncoder;
import frc.robot.drivetrain.Move.MoveByGyro;
import frc.robot.drivetrain.Vision.*;

public class GoFwd extends CommandGroup {
    public final double HATCH_HEIGHT = 28.5;
    public GoFwd() {
        addSequential(new MoveByEncoder(15, 0.5));
        //addSequential(new MoveToTarget(0.5));
    }
}
