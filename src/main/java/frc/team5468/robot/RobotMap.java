package frc.team5468.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team5468.robot.Sensors.HallEffect;

public class RobotMap {

    public boolean isMain = false;

    public static int rightFrontDrive, rightBackDrive, leftFrontDrive, leftBackDrive, hallEffectMotor;
    public static int hFXSensor;

    public static WPI_TalonSRX leftDriveMotor, rightDriveMotor;
    public static WPI_VictorSPX leftSlaveMotor, rightSlaveMotor, hallFXMotor;

    public static SpeedControllerGroup leftDrive, rightDrive;
    public static DifferentialDrive robotDrive;

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

        //Config for left Talon
        leftDriveMotor = new WPI_TalonSRX(leftFrontDrive);
        leftDriveMotor.setInverted(true);

        //Config for right Victor, sets it to follow leftTalon.
        //Follow and Inversion may be made redundant by DifferentialDrive, may be depreciated in the future.
        leftSlaveMotor = new WPI_VictorSPX(leftBackDrive);
        leftSlaveMotor.follow(leftDriveMotor);
        leftSlaveMotor.setInverted(true);

        //Instantiates a speed controller group using the above motors, pairing them together.
        leftDrive = new SpeedControllerGroup(leftDriveMotor, leftSlaveMotor);

        //Config for right Talon
        rightDriveMotor = new WPI_TalonSRX(rightFrontDrive);
        rightDriveMotor.setInverted(false);

        //Config for right Victor, see above.
        rightSlaveMotor = new WPI_VictorSPX(rightBackDrive);
        rightSlaveMotor.follow(rightDriveMotor);
        rightSlaveMotor.setInverted(false);

        //Right speed controller group
        rightDrive = new SpeedControllerGroup(rightDriveMotor, rightSlaveMotor);

        //Creates a drive object which groups and inverts our speed controller groups.
        robotDrive = new DifferentialDrive(leftDrive, rightDrive);

        hallFXMotor = new WPI_VictorSPX(hallEffectMotor);

        hallEffect = new DigitalInput(hFXSensor);

        gyro = new ADXRS450_Gyro();
        gyro.reset();
        gyro.calibrate();
    }
}
