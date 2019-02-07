package frc.robot.sensors;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class Limelight implements PIDSource{
    private NetworkTable table;
    private NetworkTableEntry tx, ty, ta, ts;

    private final double CAMERA_HEIGHT = 21.5;
    private final double CAMERA_ANGLE = 21.86;

    private PIDSourceType pidSource = PIDSourceType.kDisplacement;

    public Limelight(){
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        ts = table.getEntry("ts");
        
    }
    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
        pidSource = this.pidSource;
    }
    @Override
    public PIDSourceType getPIDSourceType() {
        return pidSource;
    }
    
    public double getX(){
        return tx.getDouble(0.0);
    }

    public double getY(){
        return ty.getDouble(0.0);
    }
    public double getArea(){
        return ta.getDouble(0.0);
    }
    public double getSkew(){
        return ts.getDouble(0.0);
    }
    
    @Override
    public double pidGet() {
        return getX();
    }

    public void disableLights(){
        table.getEntry("ledMode").setNumber(1);
    }
    public void enableLights(){
        table.getEntry("ledMode").setNumber(0);
    }

    public void setPipeline(double pipeline){
        if(!(pipeline < 0) || !(pipeline > 9)){
            table.getEntry("pipeline").setNumber(pipeline);
        }
    }
    public double getDistance(double targetHeight){
        double deltaHeight = targetHeight - CAMERA_HEIGHT;
        SmartDashboard.putNumber("deltaheight", deltaHeight);
        SmartDashboard.putNumber("ANGLE", CAMERA_ANGLE + getY());
        SmartDashboard.putNumber("Tangent", Math.tan(Math.toRadians(CAMERA_ANGLE + getY())));
        return (targetHeight - CAMERA_HEIGHT) / Math.tan(Math.toRadians(CAMERA_ANGLE + getY()));
    }
}