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
import frc.robot.RobotConstants;

public class Drivetrain extends Subsystem {

    //Defines the motor controllers
    private WPI_TalonSRX leftDriveMotor, rightDriveMotor;
    private WPI_VictorSPX leftSlaveMotor, rightSlaveMotor;

    public SpeedControllerGroup leftDrive, rightDrive;
    public DifferentialDrive robotDrive;

    public ADXRS450_Gyro gyro;

    public PigeonIMU pigeonGyro;
    private TalonSRX pigeonMotorController;
    //Yaw-Pitch-Roll array for use with the Pigeon IMU
    double[] ypr;

    public Drivetrain() {

        leftDriveMotor = new WPI_TalonSRX(RobotConstants.LEFT_FRONT_DRIVE);
        leftSlaveMotor = new WPI_VictorSPX(RobotConstants.LEFT_BACK_DRIVE);
        leftDrive = new SpeedControllerGroup(leftDriveMotor, leftSlaveMotor);
        leftDriveMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0 ,0);
        leftDriveMotor.setSensorPhase(false);
        leftSlaveMotor.follow(leftDriveMotor);

        rightDriveMotor = new WPI_TalonSRX(RobotConstants.RIGHT_FRONT_DRIVE);
        rightSlaveMotor = new WPI_VictorSPX(RobotConstants.RIGHT_BACK_DRIVE);
        rightDrive = new SpeedControllerGroup(rightDriveMotor, rightSlaveMotor);
        rightDriveMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0 , 0);
        rightDriveMotor.setSensorPhase(false);
        rightSlaveMotor.follow(rightDriveMotor);

        robotDrive = new DifferentialDrive(rightDrive, leftDrive);
        robotDrive.setSafetyEnabled(false);

        pigeonMotorController = new TalonSRX(RobotConstants.PIGEONMOTORCONTROLLER);
        pigeonGyro = new PigeonIMU(pigeonMotorController);
        gyro = new ADXRS450_Gyro();
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

    public void stopRobot(){
        robotDrive.tankDrive(0, 0);
    }

    
    public double getPigeonYaw() {
        ypr = new double[3];
        pigeonGyro.getYawPitchRoll(ypr);
        return ypr[0];
    }

    public double getGyroRot(){
        return gyro.getAngle();
    }
    public void resetOldGyro(){
        gyro.reset();
    }

    public void resetPigeonGyro(){
        //pigeonGyro.enterCalibrationMode(PigeonIMU.CalibrationMode.BootTareGyroAccel);
        pigeonGyro.setYaw(0);
        pigeonGyro.setAccumZAngle(0);
    pigeonMotorController.setSelectedSensorPosition(0);
    }

    //This is an abstract Command method, and must be overwritten
    //When the subsystem is idle (no other commands are calling it), the DefaultCommand defined in this method will run
    //For this subsystem, we don't want it to execute any commands when idle. 
    @Override
    protected void initDefaultCommand() {
        
    }

}