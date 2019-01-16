package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.MoveByEncoder;
import frc.robot.commands.MoveByNewGyro;
import frc.robot.commands.TestMast;

public class TestAuto extends CommandGroup {
    public TestAuto() {
        addSequential(new MoveByEncoder(5, .2));
        addSequential(new MoveByNewGyro(90, 0.5, 5));
    }
}