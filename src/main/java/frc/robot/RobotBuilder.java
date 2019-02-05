package frc.robot;

import frc.robot.sensors.*;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.TestSystem;
import frc.robot.subsystems.initableSubsystem;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.I2C;

public class RobotBuilder {

    public Drivetrain drivetrain = new Drivetrain();
    public TestSystem testSystem = new TestSystem();
    public Limelight lemonlight = new Limelight();
    public REVdisplay revBoard = new REVdisplay();
    public USBCamera camera = new USBCamera();
    public ColorSensor colorSensor = new ColorSensor(I2C.Port.kOnboard);

    ArrayList<initableSubsystem> initiableList;

    private static RobotBuilder robotBuilder;

    
    private RobotBuilder(){
        initiableList = new ArrayList<initableSubsystem>();

        initiableList.add(drivetrain);
        initiableList.add(testSystem);

    }

    public static RobotBuilder getInstance() {

        if (robotBuilder == null) {
            robotBuilder = new RobotBuilder();
        }
        
        return robotBuilder;
    }

    public void init(){
        for(int i = 0; i < initiableList.size(); i++){
            initiableList.get(i).init();
        }
    }
}