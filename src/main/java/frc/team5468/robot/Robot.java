package frc.team5468.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.team5468.robot.Teleop.Teleop_DefaultDrive;

public class Robot extends TimedRobot {

    private RobotMap robot = new RobotMap();

    private Teleop_DefaultDrive Teleop;

    @Override
    public void robotInit() {

        robot.init();

    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void autonomousInit() {

    }

    @Override
    public void teleopInit() {

        Teleop = new Teleop_DefaultDrive();

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

    }

    @Override
    public void teleopPeriodic() {

        Teleop.periodic();

    }

    @Override
    public void testPeriodic() {

    }
}