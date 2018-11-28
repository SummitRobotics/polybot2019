package frc.team5468.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Drivetrain extends Subsystem {

    private static int rightFrontDrive, rightBackDrive, leftFrontDrive, leftBackDrive;
    public static WPI_TalonSRX leftDriveMotor, rightDriveMotor;
    public static WPI_VictorSPX leftSlaveMotor, rightSlaveMotor;

    public static SpeedControllerGroup leftDrive, rightDrive;
    public static DifferentialDrive robotDrive;

    private static final double TICKS_PER_ROTATION = 4096;
    private static final double WHEEL_DIAMETER = 6.0;

    public Drivetrain(){
        rightFrontDrive = 21;
        rightBackDrive = 31;
        leftFrontDrive = 22;
        leftBackDrive = 32;
    }

    public void init(){

        leftDriveMotor = new WPI_TalonSRX(leftFrontDrive);
        //leftDriveMotor.setInverted(true);
        leftDriveMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0 ,0);
        leftDriveMotor.setSensorPhase(true);

        leftSlaveMotor = new WPI_VictorSPX(leftBackDrive);
        leftSlaveMotor.follow(leftDriveMotor);
        //leftSlaveMotor.setInverted(true);

        rightDriveMotor = new WPI_TalonSRX(rightFrontDrive);
        //rightDriveMotor.setInverted(false);
        rightDriveMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0,0);

        rightSlaveMotor = new WPI_VictorSPX(rightBackDrive);
        rightSlaveMotor.follow(rightDriveMotor);
        //rightSlaveMotor.setInverted(false);

        leftDrive = new SpeedControllerGroup(leftDriveMotor, leftSlaveMotor);
        rightDrive = new SpeedControllerGroup(rightDriveMotor, rightDriveMotor);


        robotDrive = new DifferentialDrive(leftDrive, rightDrive);
        robotDrive.setSafetyEnabled(true);
        //todo - motor safety
        //robotDrive.setExpiration(1.0);
        //robotDrive.setMaxOutput(1.0);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public static double getLeftEncoderPos(){
        return leftDriveMotor.getSelectedSensorPosition(0);
    }
    public static double getRightEncoderPos(){
        return rightDriveMotor.getSelectedSensorPosition(0);
    }
    //return feet per second
    public static double getLeftEncoderVel(){
        return (ticksToInches(leftDriveMotor.getSelectedSensorVelocity(0)) /10);
    }
    public static double getRightEncoderVel(){
        return (ticksToInches(rightDriveMotor.getSelectedSensorVelocity(0)) /10);
    }

    public static void stop(){
        leftDriveMotor.set(0);
        rightDriveMotor.set(0);
    }

    public static void zeroEncoders(){
        Drivetrain.leftDriveMotor.setSelectedSensorPosition(0,0,0);
        Drivetrain.rightDriveMotor.setSelectedSensorPosition(0,0,0);
    }

    public static double ticksToInches(double tick){
        return (tick / TICKS_PER_ROTATION) * (WHEEL_DIAMETER * Math.PI);
    }
    public static double inchesToTicks(double inch){
        return (inch / (WHEEL_DIAMETER * Math.PI) * TICKS_PER_ROTATION);
    }



    //todo - gerabox emergency stop - if motors are busy and encoder position has not changed, emergency stop the drivetrain.

}
