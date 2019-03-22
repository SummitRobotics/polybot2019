package frc.robot.testsubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateShit extends Command{

    private TestSystem testSystem = TestSystem.getInstance();

    private double angle;
    private boolean state;

    public RotateShit(double angle){
        setInterruptible(false);
        requires(testSystem);
        this.angle = angle;
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void execute() {
        state = testSystem.setArm(angle);
        SmartDashboard.putBoolean("State of Arm", state);
    }
    @Override
    protected boolean isFinished() {
        return state;
    }
    @Override
    protected void end() {
        super.end();
        //testSystem.testMotor.set(ControlMode.PercentOutput, 0);
    }
}