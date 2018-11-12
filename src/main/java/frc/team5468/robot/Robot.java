package frc.team5468.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team5468.robot.CommandGroups.GoFwd;
import frc.team5468.robot.Subsystems.Drivetrain;
import frc.team5468.robot.Teleop.Teleop_Arcade_Differential;

public class Robot extends TimedRobot {

    public static Drivetrain Drivetrain = new Drivetrain();

    private Teleop_Arcade_Differential Teleop;

    private GoFwd auto;

    @Override
    public void robotInit() {

        this.Drivetrain.init();

    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void autonomousInit() {

        //Zero encoders on the initialization of an auto path.
        Drivetrain.leftDriveMotor.setSelectedSensorPosition(0,0,0);
        Drivetrain.rightDriveMotor.setSelectedSensorPosition(0,0,0);

        auto.start();
    }

    @Override
    public void teleopInit() {

        Teleop = new Teleop_Arcade_Differential();

        Teleop.init();

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