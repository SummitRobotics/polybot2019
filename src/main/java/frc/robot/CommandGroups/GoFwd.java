package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.CommandInterface;
import frc.robot.commands.MoveByEncoder;
import frc.robot.commands.TurnToTarget;
import frc.robot.commands.MoveToTarget;
import frc.robot.commands.MoveByGyro;

public class GoFwd extends CommandGroup implements CommandInterface {
    public final double HATCH_HEIGHT = 28.5;
    public GoFwd() {
        ///addSequential(new MoveByGyro(90, 0.5));
        addSequential(new MoveByEncoder(-15, 0.38));
        //addSequential(new MoveByGyro(-180, 0.5));
        //addSequential(new MoveByEncoder(5, 0.5));
    }
}
