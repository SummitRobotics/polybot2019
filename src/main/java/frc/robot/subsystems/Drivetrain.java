package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends Subsystem{

    //Defines the motor controllers
    private static WPI_TalonSRX leftDriveMotor, rightDriveMotor;
    private static WPI_VictorSPX leftSlaveMotor, rightSlaveMotor;

    //Defines motor controller groupings. There's a speed controller per drive-side and a differentialdrive for grouping said speed controllers 
    //This makes it so we only have to assign power to one object instead of 4+. 
    private static SpeedControllerGroup leftDrive, rightDrive;
    public static DifferentialDrive robotDrive;

    public void init(){
        //This configures our motor controller objects and assigns them to the CAN objects on the robot.

        /*Left Motor Config*/

        //Creates motor object and configures it to use a relative magnetic encoder for feedback.
        leftDriveMotor = new WPI_TalonSRX(RobotConstants.LEFT_BACK_DRIVE);
        leftDriveMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0 ,0);
        //Does NOT invert the direction of the encoder
        leftDriveMotor.setSensorPhase(false);

        //Creates the slave motor and assigns it to follow all given input from the drive motor.
        leftSlaveMotor = new WPI_VictorSPX(RobotConstants.LEFT_FRONT_DRIVE);
        leftSlaveMotor.follow(leftDriveMotor);

        //Groups all left-side motors together as one object.
        leftDrive = new SpeedControllerGroup(leftDriveMotor, leftSlaveMotor);


        /*Right Motor Config*/

        //Creates motor object and configures it to use a relative magnetic encoder for feedback.
        rightDriveMotor = new WPI_TalonSRX(RobotConstants.RIGHT_BACK_DRIVE);
        rightDriveMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0 , 0);
        //Inverts the phase of the encoder
        rightDriveMotor.setSensorPhase(true);

        //Creates the slave motor and assigns it to follow all given input from the drive motor.
        rightSlaveMotor = new WPI_VictorSPX(RobotConstants.RIGHT_FRONT_DRIVE);
        rightSlaveMotor.follow(rightDriveMotor);

        //Groups all right-side motors together as one object.
        rightDrive = new SpeedControllerGroup(rightDriveMotor, rightSlaveMotor);

        /*Drivetrain Object Config*/
        //This groups the leftDrive and rightDrive Motor Controller Groups into one unifed motor controller object 
        //This object also automatically inverts motor direction, so make sure to not invert right-side motors as you would normally do.
        robotDrive = new DifferentialDrive(leftDrive, rightDrive);
        //This disables the watchdog safety which will disable the motor if its input is not updated for a given timeout period. 
        //TODO - Configure Safety Watchdog
        robotDrive.setSafetyEnabled(false);
        
    }

    //These methods are used for returning the current value of the relative encoders on the left and right Drive Talons. 
    public static double getLeftEncoderPos(){
        return leftDriveMotor.getSelectedSensorPosition(0);
    }
    public static double getRightEncoderPos(){
        return rightDriveMotor.getSelectedSensorPosition(0);
    }

    //These methods get the velocities (in feet per second) of the left and right drive Talons, based on the encoder ticks per second.
    //To convert units, we use the ticksToInches method, shown below
    public static double getLeftEncoderVel(){
        return (ticksToInches(leftDriveMotor.getSelectedSensorVelocity(0)) /10);
    }
    public static double getRightEncoderVel(){
        return (ticksToInches(rightDriveMotor.getSelectedSensorVelocity(0)) /10);
    }

    //This method returns the number of inches a robot will have traveled, given an input of encoder ticks
    public static double ticksToInches(double tick){
        return (tick / RobotConstants.TICKS_PER_ROTATION * (RobotConstants.WHEEL_DIAMETER * Math.PI));
    }
    //This method does what the above one does, but in reverse. 
    public static double inchesToTicks(double inch){
        return (inch / (RobotConstants.WHEEL_DIAMETER * Math.PI) * RobotConstants.TICKS_PER_ROTATION);
    }

    //This is an abstract Command method, and must be overwritten
    //When the subsystem is idle (no other commands are calling it), the DefaultCommand defined in this method will run
    //For this subsystem, we don't want it to execute any commands when idle. 
    @Override
    protected void initDefaultCommand() {
        
    }

}