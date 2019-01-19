package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.MoveByEncoder;
import frc.robot.commands.MoveByGyro;

public class GoFwd extends CommandGroup {

    public GoFwd() {
        addSequential(new MoveByGyro(90, 0.5));
    }
}
