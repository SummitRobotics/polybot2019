package frc.robot;

import frc.robot.sensors.*;
import frc.robot.subsystems.*;

public class RobotBuilder {

    public Drivetrain drivetrain;
    public TestMotor mast;
    public Limelight lemonlight;
    public REVdisplay revBoard;
    public OI gamepad;
    //public USBCamera camera = new USBCamera();

    private static RobotBuilder robotBuilder;

    
    private RobotBuilder(){
        drivetrain = new Drivetrain();
        mast = new TestMotor();
        lemonlight = new Limelight();
        revBoard = new REVdisplay();
        gamepad = new OI();

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