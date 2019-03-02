package frc.robot;

import frc.robot.devices.Limelight;
import frc.robot.devices.REVdisplay;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.devices.*;
import frc.robot.drivetrain.*;
import frc.robot.testsubsystem.*;
import frc.robot.testsubsystem.TestSystem;

public class RobotBuilder {

    public Drivetrain drivetrain;
    public TestSystem testSystem;
    public Limelight lemonlight;
    public REVdisplay revBoard;
    //public USBCamera camera = new USBCamera();

    private static RobotBuilder robotBuilder;

    
    private RobotBuilder(){
        drivetrain = Drivetrain.getInstance();
        testSystem = TestSystem.getInstance();
        lemonlight = Limelight.getInstance();
        revBoard = new REVdisplay();

    }

    public static RobotBuilder getInstance() {
        if (robotBuilder == null) {
            robotBuilder = new RobotBuilder();
        }
        return robotBuilder;
    }

    public void init(){
        drivetrain.zeroEncoders();
        drivetrain.resetPigeonGyro();
        drivetrain.resetOldGyro();

        revBoard.init();
        lemonlight.enableLights();
        //camera.init();
    }
}