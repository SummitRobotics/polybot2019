package frc.old.robot;

//Use this class to output motor speeds and other diagnostic information to the SmartDashboard

import frc.old.robot.Subsystems.Drivetrain;

public class MotorOutputDiagnostic {

    public void printEncoderPos(){
        Drivetrain.leftDriveMotor.getSelectedSensorPosition(0);
        Drivetrain.rightDriveMotor.getSelectedSensorPosition(0);
    }
    public void printMotorOutputs(){
        Drivetrain.leftDriveMotor.get();
    }
}
