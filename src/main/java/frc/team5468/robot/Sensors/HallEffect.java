package frc.team5468.robot.Sensors;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.team5468.robot.RobotMap;

public class HallEffect {


    double count = 0;
    DigitalInput hallFX;

    public HallEffect(DigitalInput hallEffect){
        hallFX = hallEffect;
    }

    public void increment(){
        if((RobotMap.hallFXMotor.getMotorOutputPercent() > 0) && (hallFX.get() == true)){
            count++;
        }
        else if ((RobotMap.hallFXMotor.getMotorOutputPercent() < 0) && (hallFX.get() == true)){
            count--;
        }
    }
}
