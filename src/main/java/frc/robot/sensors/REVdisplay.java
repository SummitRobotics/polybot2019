package frc.robot.sensors;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class REVdisplay{
    private REVDigitBoardDriver revBoard = new REVDigitBoardDriver();
    private double battVolt;
    private final String teamNum = "5468";
    private double amp;
    private boolean buttonStatusA, previousStateA, currentStateA;
    private boolean buttonStatusB, previousStateB, currentStateB;

    public REVdisplay(){
        
    }

    public void init(){
        revBoard.clear();
    }

    public void disable(){
        revBoard.clear();
    }

    public void run(){
        battVolt = RobotController.getBatteryVoltage();
        amp = RobotController.getInputCurrent();

        if(!toggleA()){
            revBoard.display(teamNum);
        }
        else if(!toggleB()){
            revBoard.display(amp);
        }
        else{
            revBoard.display(battVolt);
        }

    }

    //TODO - Proper Toggle
    public boolean toggleA(){
        previousStateA = currentStateA;
        currentStateA = revBoard.getButtonA();

        if(currentStateA && !previousStateA){
            buttonStatusA = !buttonStatusA;
        }
        return buttonStatusA;
    }

    public boolean toggleB(){
        previousStateB = currentStateB;
        currentStateB = revBoard.getButtonB();

        if(currentStateB && !previousStateB){
            buttonStatusB = !buttonStatusB;
        }
        return buttonStatusB;
    }

}