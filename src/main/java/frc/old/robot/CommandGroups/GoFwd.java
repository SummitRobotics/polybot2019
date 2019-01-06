package frc.old.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.old.robot.Commands.EncoderDrive;



public class GoFwd extends CommandGroup {

    public GoFwd(){
        addSequential(new EncoderDrive(12, 0.4));
    }
}
