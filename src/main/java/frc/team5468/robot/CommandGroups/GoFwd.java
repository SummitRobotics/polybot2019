package frc.team5468.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team5468.robot.Commands.MoveByTime;


public class GoFwd extends CommandGroup {

    public GoFwd(){
        addSequential(new MoveByTime(0.5, 10));
    }
}
