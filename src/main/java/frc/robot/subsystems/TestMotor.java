package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TestMotor extends Subsystem implements initableSubsystem {

    public WPI_VictorSPX testMotor;
    public Servo testServo;

    private DigitalInput hallFX;

    @Override
    public void init() {
        testMotor = new WPI_VictorSPX(RobotConstants.TEST_MOTOR);
        hallFX = new DigitalInput(RobotConstants.HALL_FX);
        testServo = new Servo(0);
        testServo.setBounds(2.0, 0.003, 1.7, 0.00, 1.4);

    }

    @Override
    protected void initDefaultCommand() {
        
    }


}