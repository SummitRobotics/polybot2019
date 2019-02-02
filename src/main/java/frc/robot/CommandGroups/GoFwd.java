package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.CommandInterface;
import frc.robot.commands.MoveByEncoder;
import frc.robot.commands.MoveByTime;

public class GoFwd extends CommandGroup implements CommandInterface {
    public final double HATCH_HEIGHT = 28.5;
    public GoFwd() {
            
    }
}
