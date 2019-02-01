package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.CommandInterface;
import frc.robot.commands.MoveByEncoder;

public class GoFwd extends CommandGroup implements CommandInterface {
    public final double HATCH_HEIGHT = 28.5;
    public GoFwd() {
        ///addSequential(new MoveByGyro(90, 0.5));
        addSequential(new MoveByEncoder(15, 0.4));
        //addSequential(new MoveByGyro(-180, 0.5));
        //addSequential(new MoveByEncoder(5, 0.5));
    }
}
