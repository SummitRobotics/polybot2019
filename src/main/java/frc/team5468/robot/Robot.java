package frc.team5468.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.team5468.robot.Teleop.Teleop_Arcade_Differential;

public class Robot extends TimedRobot {

    private RobotMap robot = new RobotMap();

    private Teleop_Arcade_Differential Teleop;

    @Override
    public void robotInit() {

        robot.init();

    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void autonomousInit() {

        //Zero encoders on the initialization of an auto path.
        RobotMap.leftDriveMotor.setSelectedSensorPosition(0,0,0);
        RobotMap.rightDriveMotor.setSelectedSensorPosition(0,0,0);
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

    }

    @Override
    public void teleopPeriodic() {

        Teleop.run();

    }

    @Override
    public void testPeriodic() {

    }
}