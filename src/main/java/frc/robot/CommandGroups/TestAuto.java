package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.MoveByNewGyro;
import frc.robot.commands.TestMast;

public class TestAuto extends CommandGroup {
    public TestAuto() {
        //addSequential(new MoveByNewGyro(90, 0.5));
        addSequential(new TestMast(-0.5, 3));
    }
}