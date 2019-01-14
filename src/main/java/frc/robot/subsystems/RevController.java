package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.command.Subsystem;

public class RevController extends Subsystem implements initableSubsystem{

    public static CANSparkMax sparkMaxTest;

    @Override
    public void init() {
        sparkMaxTest = new CANSparkMax(RobotConstants.SPARK_MAX_TEST, CANSparkMaxLowLevel.MotorType.kBrushed);
        sparkMaxTest.setInverted(false);
    }

    @Override
    protected void initDefaultCommand() {
        
    }
    
    

}