package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.MoveByGyro;

public class TestAuto extends CommandGroup {
    public TestAuto() {
        addSequential(new MoveByGyro(90, 0.5));
    }


}