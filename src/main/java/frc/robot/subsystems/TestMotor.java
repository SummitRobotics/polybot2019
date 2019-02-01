package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotConstants;

public class TestMotor extends Subsystem implements initableSubsystem {

    public WPI_VictorSPX testMotor;
    public Servo testServo;

    //TODO - implement rotation incrementer for... funsies? 
    //Anyone can play with this if they want, I'm too lazy to - Aidan B.
    private DigitalInput hallFX;

    @Override
    public void init() {
        testMotor = new WPI_VictorSPX(RobotConstants.TEST_MOTOR);
        hallFX = new DigitalInput(RobotConstants.HALL_FX);
        
        //Example code of creating a servo object given specific boundaries. 
        testServo = new Servo(0);
        //DANIEL --- These are the boundaries you need to change
        testServo.setBounds(2.0, 0.0, 1.5, 0.00, 1.0);

    }

    @Override
    protected void initDefaultCommand() {
        
    }


}