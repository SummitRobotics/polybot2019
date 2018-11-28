package frc.team5468.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team5468.robot.Commands.EncoderDrive;
import frc.team5468.robot.Commands.MoveByTime;


public class GoFwd extends CommandGroup {

    public GoFwd(){
        addSequential(new EncoderDrive(12, 0.4));
    }
}
