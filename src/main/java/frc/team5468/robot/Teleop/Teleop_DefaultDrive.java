package frc.team5468.robot.Teleop;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5468.robot.RobotMap;
import frc.team5468.robot.OI;

//Depreciated, use Teleop_Arcade_Differential.
public class Teleop_DefaultDrive {

    OI gamepad;

    double deadzone = 0.15;

    public void init(){

        gamepad = new OI();

        RobotMap.gyro.reset();
    }

    public void periodic(){

        double leftPower = (((gamepad.joystickDeadzone(gamepad.getRightTrigger(), deadzone)
                - gamepad.joystickDeadzone(gamepad.getLeftTrigger(), deadzone))
                + gamepad.joystickDeadzone(gamepad.getLeftJoystickX(), deadzone)));


        double rightPower = (((gamepad.joystickDeadzone(gamepad.getRightTrigger(), deadzone)
                - gamepad.joystickDeadzone(gamepad.getLeftTrigger(), deadzone))
                - gamepad.joystickDeadzone(gamepad.getLeftJoystickX(), deadzone)));

        //Individual addressing of left and right sids Talons to be depreciated in the future
        RobotMap.leftDriveMotor.set(ControlMode.PercentOutput, leftPower);
        RobotMap.rightDriveMotor.set(ControlMode.PercentOutput, rightPower);

        RobotMap.hallFXMotor.set(ControlMode.PercentOutput, gamepad.holdButton(gamepad.isButtonA()));

        //To be depreciated.
        SmartDashboard.putNumber("LEFT ENCODER", RobotMap.leftDriveMotor.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("RIGHT ENCODER", RobotMap.rightDriveMotor.getSelectedSensorPosition(0));

    }
}
