package frc.team5468.robot;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;

public class RobotMap {

    public boolean isMain = false;

    public static int hallEffectMotor;
    public static int hFXSensor;

    public static ADXRS450_Gyro gyro;

    public static DigitalInput hallEffect;

    public RobotMap(){
        if(isMain){
            hallEffectMotor = 0;
            hFXSensor = 0;
        }
        else {
            hallEffectMotor = 33;
            hFXSensor = 0;
        }
    }

    public void init(){
        //hallFXMotor = new WPI_VictorSPX(hallEffectMotor);

        hallEffect = new DigitalInput(hFXSensor);

        gyro = new ADXRS450_Gyro();
        gyro.reset();
        gyro.calibrate();
    }
}
