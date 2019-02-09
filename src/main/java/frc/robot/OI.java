package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.commands.Move.MoveByEncoder;
import frc.robot.commands.Move.MoveByGyro;
import frc.robot.commands.Vision.TargetAlignment;

public class OI {


    double deadzoneDefault = 0.15;

    XboxController controller = new XboxController(0);
    Button button = new Button(){
        @Override
        public boolean get() {
            return controller.getAButton();
        }
    };
    Button overide = new Button(){
    
        @Override
        public boolean get() {
            return controller.getXButton();
        }
    };

    Button interruptAll = new Button(){
        @Override
        public boolean get(){
            return controller.getBumper(GenericHID.Hand.kLeft) && controller.getBumper(GenericHID.Hand.kRight);
        }
    };

    private static OI instance;

    public OI(){
        button.whileHeld(new TargetAlignment(0.8));
    }

    public static OI getInstance(){
        if(instance == null){
            instance = new OI();
        }
        return instance;
    }


    //Now here's a bunch of methods for calling all these values.
    public double getLeftJoystickX() {
        return controller.getX(GenericHID.Hand.kLeft);
    }
    public double getLeftJoystickY() {
        return controller.getY(GenericHID.Hand.kLeft);
    }
    public double getRightJoystickX() {
        return controller.getX(GenericHID.Hand.kRight);
    }
    public double getRightJoystickY() {
        return controller.getY(GenericHID.Hand.kRight);
    }


    
    public double getLeftTrigger() {
        return controller.getTriggerAxis(GenericHID.Hand.kLeft);
    }
    public double getRightTrigger() {
        return controller.getTriggerAxis(GenericHID.Hand.kRight);
    }

    public double makeCurve(double input){
        return(Math.pow(2, input) -1);
    }

    public double getForwardPower(){
        return makeCurve(getRightTrigger()) - makeCurve(getLeftTrigger());
    }
    public double getRotationalPower(){
        return -Math.copySign(makeCurve(Math.abs(getLeftJoystickX())), getLeftJoystickX());
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

   


    /*public double joystickDeadzone(double stickVal, double deadzone) {
        if (Math.abs(stickVal) < deadzone){
            return 0;
        } else {
            return stickVal;
        }
    }
    public double joystickDeadzone (double stickVal) {
        if(Math.abs(stickVal) < deadzoneDefault) {
            return 0;
        }
        else {
            return stickVal;
        }
    }*/

    public double holdButton (boolean button) {
        if (button){
            return 1;
        } else {
            return 0;
        }
    }

}
