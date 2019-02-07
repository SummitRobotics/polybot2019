package frc.robot;

import frc.robot.sensors.*;
import frc.robot.subsystems.*;

public class RobotBuilder {

    public Drivetrain drivetrain;
    public TestSystem testSystem;
    public Limelight lemonlight;
    public REVdisplay revBoard;
    //public USBCamera camera = new USBCamera();

    private static RobotBuilder robotBuilder;

    
    private RobotBuilder(){
        drivetrain = new Drivetrain();
        testSystem = new TestSystem();
        lemonlight = new Limelight();
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