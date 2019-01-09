package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.MoveByEncoder;



public class GoFwd extends CommandGroup {

    public GoFwd(){
        addSequential(new MoveByEncoder(12, 0.4));
        
    }
}
