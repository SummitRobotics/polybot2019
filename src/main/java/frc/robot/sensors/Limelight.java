package frc.robot.sensors;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight{
    NetworkTable table;
    NetworkTableEntry tx, ty, ta, ts;
    //Target heights in inches
    public final double ROCKET_PORT_HEIGHT = 39.125;
    public final double HATCH_HEIGHT = 31.5;

    private final double CAMERA_HEIGHT = 19.75;
    private final double CAMERA_ANGLE = 0.0;

    public Limelight(){
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        ts = table.getEntry("ts");
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
        double deltaHeight = CAMERA_HEIGHT - targetHeight;
        return deltaHeight / Math.tan(CAMERA_ANGLE + getSkew());
    }
}