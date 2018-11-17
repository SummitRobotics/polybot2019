package frc.team5468.robot.Teleop;

import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotorInputTest extends RobotDriveBase {

    @Override
    public void initSendable(SendableBuilder builder) {

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void stopMotor() {

    }

    public void testValues(double speed, double zRotation){
        speed = limit(speed);
        speed = applyDeadband(speed, m_deadband);

        zRotation = limit(zRotation);
        zRotation = applyDeadband(zRotation, m_deadband);

        double maxInput = Math.copySign(Math.max(Math.abs(speed), Math.abs(zRotation)), speed);
        SmartDashboard.putNumber("Speed", speed);
        SmartDashboard.putNumber("Rotation Value", zRotation);
        SmartDashboard.putNumber("Max Value", maxInput);
    }

}
