package frc.team5468.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5468.robot.CommandGroups.GoFwd;
import frc.team5468.robot.Subsystems.Drivetrain;
import frc.team5468.robot.Teleop.Teleop_Arcade_Differential;

public class Robot extends TimedRobot {

    Command auto;

    public static Drivetrain Drivetrain = new Drivetrain();

    private Teleop_Arcade_Differential Teleop;

    //todo - autonomous SendableChooser
    //private GoFwd auto;
    public SendableChooser autoChooser;

    @Override
    public void robotInit() {

        this.Drivetrain.init();

        autoChooser = new SendableChooser();
        autoChooser.addDefault("Cross The Line", new GoFwd());

        SmartDashboard.putData("Autonomous", autoChooser);

        Drivetrain.zeroEncoders();

    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void autonomousInit() {

        auto = (Command) autoChooser.getSelected();

        //Zero encoders on the initialization of an auto path.
        Drivetrain.zeroEncoders();

        auto.start();
    }

    @Override
    public void teleopInit() {

        Teleop = new Teleop_Arcade_Differential();

        Teleop.init();

        //Drivetrain.zeroEncoders();


    }

    @Override
    public void testInit() {

    }


    @Override
    public void disabledPeriodic() {

    }
    
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();

    }

    @Override
    public void teleopPeriodic() {

        Teleop.run();

    }

    @Override
    public void testPeriodic() {

    }
}