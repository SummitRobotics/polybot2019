package frc.team5468.robot.Teleop;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.team5468.robot.RobotMap;
import frc.team5468.robot.OI;
import frc.team5468.robot.Sensors.HallEffect;

public class Teleop_DefaultDrive {

    OI gamepad;

    HallEffect hallsense;

    public void init(){

        gamepad = new OI();

        hallsense = new HallEffect(RobotMap.hallEffect);

        RobotMap.gyro.reset();
    }

    public void periodic(){

        double leftPower = ((gamepad.getRightTrigger() - gamepad.getLeftTrigger()) + gamepad.getLeftJoystickX());
        double rightPower = ((gamepad.getRightTrigger() - gamepad.getLeftTrigger()) - gamepad.getLeftJoystickX());

        RobotMap.leftDrive.set(ControlMode.PercentOutput, leftPower);
        RobotMap.rightDrive.set(ControlMode.PercentOutput, rightPower);

        hallsense.increment();
        RobotMap.hallFXMotor.set(ControlMode.PercentOutput, holdButton(gamepad.isButtonA()));

    }

    public double holdButton(boolean button){
        if(button){
            return 1;
        }
        else{
            return 0;
        }
    }
}
