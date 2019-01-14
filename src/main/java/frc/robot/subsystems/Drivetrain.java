package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends Subsystem implements initableSubsystem {

    //Defines the motor controllers
    private WPI_TalonSRX leftDriveMotor, rightDriveMotor;
    private WPI_VictorSPX leftSlaveMotor, rightSlaveMotor;

    //Defines motor controller groupings. There's a speed controller per drive-side and a differentialdrive for grouping said speed controllers 
    //This makes it so we only have to assign power to one object instead of 4+. 
    private SpeedControllerGroup leftDrive, rightDrive;
    public DifferentialDrive robotDrive;

    //defines the motor controller which handles the feedback from the gyro
    
    //Defines the robot gyro
    private ADXRS450_Gyro gyro;

    //Defines the other robot gyro
    public PigeonIMU gyro2;

    //Defines the motor controller which handles Pigeon gyro input
    private TalonSRX Unused;

    public Drivetrain() {

        leftDriveMotor = new WPI_TalonSRX(RobotConstants.LEFT_FRONT_DRIVE);
        leftSlaveMotor = new WPI_VictorSPX(RobotConstants.LEFT_BACK_DRIVE);
        leftDrive = new SpeedControllerGroup(leftDriveMotor, leftSlaveMotor);

        rightDriveMotor = new WPI_TalonSRX(RobotConstants.RIGHT_FRONT_DRIVE);
        rightSlaveMotor = new WPI_VictorSPX(RobotConstants.RIGHT_BACK_DRIVE);
        rightDrive = new SpeedControllerGroup(rightDriveMotor, rightSlaveMotor);

        robotDrive = new DifferentialDrive(leftDrive, rightDrive);
        gyro = new ADXRS450_Gyro();
    }

    public void init() {

        leftDriveMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0 ,0);
        leftDriveMotor.setSensorPhase(false);
        leftSlaveMotor.follow(leftDriveMotor);

        rightDriveMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0 , 0);
        rightDriveMotor.setSensorPhase(true);
        rightSlaveMotor.follow(rightDriveMotor);

        robotDrive.setSafetyEnabled(false);

        gyro.calibrate();

        Unused = new TalonSRX(RobotConstants.UNUSED);

        gyro2 = new PigeonIMU(Unused);
        gyro2.enterCalibrationMode(PigeonIMU.CalibrationMode.BootTareGyroAccel);
    }

    //These methods are used for returning the current value of the relative encoders on the left and right Drive Talons. 
    public double getLeftEncoderPos() {
        return leftDriveMotor.getSelectedSensorPosition(0);
    }
    public double getRightEncoderPos() {
        return rightDriveMotor.getSelectedSensorPosition(0);
    }

    //This resets the encoder values to 0
    public void zeroEncoders() {
        leftDriveMotor.setSelectedSensorPosition(0);
        rightDriveMotor.setSelectedSensorPosition(0);
    }

    //These methods get the velocities (in feet per second) of the left and right drive Talons, based on the encoder ticks per second.
    //To convert units, we use the ticksToInches method, shown below
    public double getLeftEncoderVel() {
        return (ticksToInches(leftDriveMotor.getSelectedSensorVelocity(0)) /10);
    }
    public double getRightEncoderVel() {
        return (ticksToInches(rightDriveMotor.getSelectedSensorVelocity(0)) /10);
    }

    //This method returns the number of inches a robot will have traveled, given an input of encoder ticks
    public double ticksToInches(double tick) {
        return (tick / RobotConstants.TICKS_PER_ROTATION * (RobotConstants.WHEEL_DIAMETER * Math.PI));
    }
    //This method does what the above one does, but in reverse. 
    public double inchesToTicks(double inch) {
        return (inch / (RobotConstants.WHEEL_DIAMETER * Math.PI) * RobotConstants.TICKS_PER_ROTATION);
    }

    /*The two methods below interface with the gyro so we can publically access the rate and angle of the sensor.*/
    /*They're configured in a way which will allow the robot to continue running even if the gyro is disconnected.*/
    /*This is known as the "Fuck you, Daniel" logic block*/
    /*Right now, if the gyro is disconnected it will just return a value of 0 and print an error statement*/
    /*This means that the robot could spin infinitely, unless a timeout flag is added to the gyro command*/
    /*In the future, this should ideally throw some sort of error flag and disable gyro-based commands*/

    //Returns current angle of the gyro
    //TODO - Backup Encoder-Only Auton
    public double getGyroRotation() {
        if (gyro.isConnected()) {
            return gyro.getAngle();
        } else {
            //TODO - More elegant error flag system.
            System.out.println("ERROR: Gyro not connected. Defaulting to 0 angle...");
            return 0;
        }
    }

    //Returns current rate of the gyro.
    public double getGyroRate() {
        if (gyro.isConnected()) {
            return gyro.getRate();
        } else {
            System.out.println("ERROR: Gyro not connected. Defaulting to 0 rate...");
            return 0;
        }
    }


    public double getPigeonYaw() {
        double[] ypr = new double[3];
        gyro2.getYawPitchRoll(ypr);
        return ypr[0];
    }

    public void resetGyro2(){
        gyro2.setYaw(0);
        gyro2.setAccumZAngle(0);
        gyro2.enterCalibrationMode(PigeonIMU.CalibrationMode.BootTareGyroAccel);
        //test
    }

    //This is an abstract Command method, and must be overwritten
    //When the subsystem is idle (no other commands are calling it), the DefaultCommand defined in this method will run
    //For this subsystem, we don't want it to execute any commands when idle. 
    @Override
    protected void initDefaultCommand() {
        
    }

}