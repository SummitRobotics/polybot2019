package frc.robot.testsubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotConstants;

public class TestSystem extends Subsystem {

    public TalonSRX testMotor;

    private static TestSystem instance;

    private TestSystem(){
        testMotor = new TalonSRX(RobotConstants.TEST_MOTOR);
        configPID();
        testMotor.setSelectedSensorPosition(0);

    }
    public static TestSystem getInstance(){
        if(instance == null){
            instance = new TestSystem();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        
    }

    private void configPID(){
        testMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        testMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        testMotor.setSensorPhase(false);
        testMotor.setInverted(false);

        testMotor.configNominalOutputForward(0);
        testMotor.configNominalOutputReverse(0);
        testMotor.configPeakOutputForward(1);
        testMotor.configPeakOutputReverse(1);

        testMotor.configPeakCurrentLimit((int)40);
        testMotor.configContinuousCurrentLimit((int)30);

        testMotor.configAllowableClosedloopError(0, (int)15);

        testMotor.config_kF(0, 0);
        testMotor.config_kP(0, 1);
        testMotor.config_kI(0, 0);
        testMotor.config_kD(0, 0);
    }

    public double getEncoderPosition(){
        return testMotor.getSelectedSensorPosition();
    }

    /*public int getAbsoluteResetPosition(){
        int absolutePosition = testMotor.getSensorCollection().getPulseWidthPosition();

        absolutePosition &= 0xFFF;
        if(false){
            absolutePosition *= -1;
        }
        if(false){
            absolutePosition *= 1;
        }
        return absolutePosition;
    }*/

    public boolean setArm(double angle){
        double target = (angle/360) * 4096;
        testMotor.set(ControlMode.Position, target);
        return testMotor.getClosedLoopError() == 0;
    }

}