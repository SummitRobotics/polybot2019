package frc.team5468.robot;

//Use this class to output motor speeds and other diagnostic information to the SmartDashboard

import frc.team5468.robot.Subsystems.Drivetrain;

public class MotorOutputDiagnostic {

    public void printEncoderPos(){
        Drivetrain.leftDriveMotor.getSelectedSensorPosition(0);
        Drivetrain.rightDriveMotor.getSelectedSensorPosition(0);
    }
    public void printMotorOutputs(){
        Drivetrain.leftDriveMotor.get();
    }
}
