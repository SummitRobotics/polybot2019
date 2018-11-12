package frc.team5468.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Drivetrain extends Subsystem {

    private static int rightFrontDrive, rightBackDrive, leftFrontDrive, leftBackDrive;
    public static WPI_TalonSRX leftDriveMotor, rightDriveMotor;
    public static WPI_VictorSPX leftSlaveMotor, rightSlaveMotor;

    public static SpeedControllerGroup leftDrive, rightDrive;
    public static DifferentialDrive robotDrive;

    public Drivetrain(){
        rightFrontDrive = 21;
        rightBackDrive = 31;
        leftFrontDrive = 22;
        leftBackDrive = 32;
    }

    public void init(){

        leftDriveMotor = new WPI_TalonSRX(leftFrontDrive);
        leftDriveMotor.setInverted(true);

        leftSlaveMotor = new WPI_VictorSPX(leftBackDrive);
        leftSlaveMotor.follow(leftDriveMotor);
        leftSlaveMotor.setInverted(true);

        rightDriveMotor = new WPI_TalonSRX(rightFrontDrive);
        rightDriveMotor.setInverted(false);

        rightSlaveMotor = new WPI_VictorSPX(rightBackDrive);
        rightSlaveMotor.follow(rightDriveMotor);
        rightSlaveMotor.setInverted(false);

        leftDrive = new SpeedControllerGroup(leftDriveMotor, leftSlaveMotor);
        rightDrive = new SpeedControllerGroup(rightDriveMotor, leftDriveMotor);

        robotDrive = new DifferentialDrive(leftDrive, rightDrive);
        robotDrive.setSafetyEnabled(true);
        robotDrive.setExpiration(0.2);
        robotDrive.setMaxOutput(1.0);
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
    public static void stop(){
        leftDriveMotor.set(0);
        rightDriveMotor.set(0);
    }

    //todo - gerabox emergency stop - if motors are busy and encoder position has not changed, emergency stop the drivetrain.

}
