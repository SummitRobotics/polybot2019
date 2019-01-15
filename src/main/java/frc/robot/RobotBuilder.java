package frc.robot;

import frc.robot.sensors.Limelight;
import frc.robot.sensors.REVdisplay;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.RevController;
import frc.robot.subsystems.TestMotor;
import frc.robot.subsystems.initableSubsystem;

import java.util.ArrayList;

public class RobotBuilder {

    public Drivetrain drivetrain = new Drivetrain();
    public TestMotor mast = new TestMotor();
    public RevController revController = new RevController();
    public Limelight lemonlight = new Limelight();
    public REVdisplay revBoard = new REVdisplay();

    ArrayList<initableSubsystem> initiableList;

    private static RobotBuilder robotBuilder;

    
    private RobotBuilder(){
        initiableList = new ArrayList<initableSubsystem>();

        initiableList.add(drivetrain);
        initiableList.add(mast);
        initiableList.add(revController);
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