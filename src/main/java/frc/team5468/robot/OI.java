package frc.team5468.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

public class OI {

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
    public double getRightJoystickY(){
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
}
