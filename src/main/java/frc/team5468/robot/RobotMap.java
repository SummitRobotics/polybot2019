package frc.team5468.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.team5468.robot.Sensors.HallEffect;

public class RobotMap {

    public boolean isMain = false;

    public static int rightFrontDrive, rightBackDrive, leftFrontDrive, leftBackDrive, hallEffectMotor;
    public static int hFXSensor;

    public static TalonSRX leftDrive, rightDrive;
    public static VictorSPX leftSlave, rightSlave, hallFXMotor;

    public static ADXRS450_Gyro gyro;

    public static DigitalInput hallEffect;

    public RobotMap(){
        if(isMain){
            rightFrontDrive = 0;
            rightBackDrive = 0;
            leftFrontDrive = 0;
            leftBackDrive = 0;
            hallEffectMotor = 0;
            hFXSensor = 0;
        }
        else {
            rightFrontDrive = 21;
            rightBackDrive = 31;
            leftFrontDrive = 22;
            leftBackDrive = 32;
            hallEffectMotor = 33;
            hFXSensor = 0;
        }
    }

    public void init(){

        leftDrive = new TalonSRX(leftFrontDrive);
        leftDrive.setInverted(true);

        rightDrive = new TalonSRX(rightFrontDrive);
        rightDrive.setInverted(false);


        leftSlave = new VictorSPX(leftBackDrive);
        leftSlave.follow(leftDrive);
        leftSlave.setInverted(true);

        rightSlave = new VictorSPX(rightBackDrive);
        rightSlave.follow(rightDrive);
        rightSlave.setInverted(false);

        hallFXMotor = new VictorSPX(hallEffectMotor);

        hallEffect = new DigitalInput(hFXSensor);

        gyro = new ADXRS450_Gyro();
        gyro.reset();
        gyro.calibrate();
    }
}
