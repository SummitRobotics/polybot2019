package frc.team5468.robot.Teleop;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.team5468.robot.RobotMap;
import frc.team5468.robot.OI;

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

        RobotMap.leftDrive.set(ControlMode.PercentOutput, leftPower);
        RobotMap.rightDrive.set(ControlMode.PercentOutput, rightPower);

        RobotMap.hallFXMotor.set(ControlMode.PercentOutput, gamepad.holdButton(gamepad.isButtonA()));

    }
}
