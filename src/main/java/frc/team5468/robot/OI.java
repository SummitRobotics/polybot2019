package frc.team5468.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

public class OI {

    double deadzoneDefault = 0.15;

    XboxController controller = new XboxController(0);

    public OI(){

    }


    //Now here's a bunch of methods for calling all these values.
    public double getLeftJoystickX(){
        return controller.getX(GenericHID.Hand.kLeft);
    }
    public double getLeftJoystickY(){
        return controller.getY(GenericHID.Hand.kLeft);
    }
    public double getRightJoystickX(){
        return controller.getX(GenericHID.Hand.kRight);
    }
    public double getRightJoystickY() {
        return controller.getY(GenericHID.Hand.kRight);
    }


    public double getLeftTrigger(){
        return controller.getTriggerAxis(GenericHID.Hand.kLeft);
    }
    public double getRightTrigger(){
        return controller.getTriggerAxis(GenericHID.Hand.kRight);
    }


    public boolean isButtonA() {
        return controller.getAButton();
    }
    public boolean isButtonB() {
        return controller.getBButton();
    }
    public boolean isButtonX() {
        return controller.getXButton();
    }
    public boolean isButtonY() {
        return controller.getYButton();
    }


    public double joystickDeadzone(double stickVal, double deadzone){
        if (Math.abs(stickVal) < deadzone){
            return 0;
        }
        else{
            return stickVal;
        }
    }
    public double joystickDeadzone (double stickVal){
        if(Math.abs(stickVal) < deadzoneDefault){
            return 0;
        }
        else{
            return stickVal;
        }
    }

    public double holdButton (boolean button){
        if(button){
            return 1;
        }
        else{
            return 0;
        }
    }


}
