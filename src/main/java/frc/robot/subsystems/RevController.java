package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RevController extends Subsystem implements initableSubsystem{

    public CANSparkMax sparkMax1, sparkMax2, sparkMax3;
    public SpeedControllerGroup revGearbox;

    public RevController() {
        sparkMax1 = new CANSparkMax(RobotConstants.SPARK_MAX_1, CANSparkMaxLowLevel.MotorType.kBrushless);
        sparkMax2 = new CANSparkMax(RobotConstants.SPARK_MAX_2, CANSparkMaxLowLevel.MotorType.kBrushless);
        sparkMax3 = new CANSparkMax(RobotConstants.SPARK_MAX_3, CANSparkMaxLowLevel.MotorType.kBrushless);

        revGearbox = new SpeedControllerGroup(sparkMax1, sparkMax2, sparkMax3);
    }

    @Override
    public void init() {
        //sparkMax1.setInverted(false);
    }

    @Override
    protected void initDefaultCommand() {
        
    }
    
    

}