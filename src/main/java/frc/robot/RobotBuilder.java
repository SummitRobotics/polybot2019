package frc.robot;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.TestMotor;
import frc.robot.subsystems.initableSubsystem;

import java.util.ArrayList;


public class RobotBuilder{
    public static Drivetrain drivetrain = Drivetrain.GetInstance();
    public static TestMotor mast = new TestMotor();
    ArrayList<initableSubsystem> list;
    
    public RobotBuilder(){
        list = new ArrayList<initableSubsystem>();

        list.add(drivetrain);
    }

    public void init(){
        for(int i = 0; i < list.size(); i++){
            list.get(i).init();
        }
    }
}