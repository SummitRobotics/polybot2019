package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.EncoderDrive;



public class GoFwd extends CommandGroup {

    public GoFwd(){
        addSequential(new EncoderDrive(12, 0.4));
    }
}
