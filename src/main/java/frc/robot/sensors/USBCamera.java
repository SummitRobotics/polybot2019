package frc.robot.sensors;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class USBCamera{
    public USBCamera(){

    }
    
    public void init(){
       UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
       camera.setResolution(640, 480);
    }
       
}