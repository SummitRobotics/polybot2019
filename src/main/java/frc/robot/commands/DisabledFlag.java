package frc.robot.commands;

public class DisabledFlag{
    private boolean isInterrupting = false;
    private static DisabledFlag disabledFlag;

    public DisabledFlag(){

    }

    public static DisabledFlag getInstance() {
        if (disabledFlag == null) {
            disabledFlag = new DisabledFlag();
        }
        return disabledFlag;
    }

    public void setTrue(){
        isInterrupting = true;
    }
    public void setFalse(){
        isInterrupting = false;
    }

    public boolean getInterrupt(){
        return isInterrupting;
    }

}