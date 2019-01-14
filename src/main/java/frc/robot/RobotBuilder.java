package frc.robot;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.RevController;
import frc.robot.subsystems.TestMotor;
import frc.robot.subsystems.initableSubsystem;

import java.util.ArrayList;


public class RobotBuilder{
    public static Drivetrain drivetrain = new Drivetrain();
    //public static TestMotor mast = new TestMotor();
    public static RevController revController = new RevController();
    ArrayList<initableSubsystem> list;
    
    public RobotBuilder(){
        list = new ArrayList<initableSubsystem>();

        list.add(drivetrain);
        list.add(revController);
    }

    public void init(){
        for(int i = 0; i < list.size(); i++){
            list.get(i).init();
        }
    }
}