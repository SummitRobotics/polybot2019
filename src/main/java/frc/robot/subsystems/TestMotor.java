package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TestMotor extends Subsystem implements initableSubsystem {

    public static WPI_VictorSPX testMotor;

    private static DigitalInput hallFX;

    @Override
    public void init() {
        testMotor = new WPI_VictorSPX(RobotConstants.TEST_MOTOR);
        hallFX = new DigitalInput(RobotConstants.HALL_FX);

    }

    @Override
    protected void initDefaultCommand() {
        
    }


}