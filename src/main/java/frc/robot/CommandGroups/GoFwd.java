package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.CommandInterface;
import frc.robot.commands.MoveToTarget;
import frc.robot.commands.TurnToTarget;

public class GoFwd extends CommandGroup implements CommandInterface {
    public final double HATCH_HEIGHT = 28.5;
    public GoFwd() {
        addSequential(new TurnToTarget(0.4));
        addSequential(new MoveToTarget(0.5));
    }
}
