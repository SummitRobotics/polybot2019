package frc.team5468.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.team5468.robot.Sensors.HallEffect;

public class RobotMap {

    public boolean isMain;

    public static int rightFrontDrive, rightBackDrive, leftFrontDrive, leftBackDrive, hallEffectMotor;
    public static int hFXSensor;

    public static TalonSRX leftDrive, rightDrive;
    public static VictorSPX leftSlave, rightSlave, hallFXMotor;

    public static ADXRS450_Gyro gyro;

    public static DigitalInput hallEffect;

    /*
     Builder pattern allows for simplified configuration of objects.

                          Replace
     RobotMap map = new RobotMap(value1, value2, value3);

                            With
     RobotMap map = new RobotMap.Builder()
                            .setValue1(whatever)
                            .setValue2(whatever)
                            .setValue3(whatever)
                            .build();

     Hopefully it should be self evident how this will make configuration easier once configuration options start to pile up
     If you still don't believe me refer to: https://dzone.com/articles/design-patterns-the-builder-pattern
     */

    static class Builder {
        boolean isMain = false;

        public Builder(){}

        Builder isMain(boolean isMain) {
            this.isMain = isMain;
            return this;
        }

        RobotMap build() {
            return new RobotMap(isMain);
        }

    }

    public RobotMap(boolean isMain){
        this.isMain = isMain;
        if(isMain){
            rightFrontDrive = 0;
            rightBackDrive = 0;
            leftFrontDrive = 0;
            leftBackDrive = 0;
            hallEffectMotor = 0;
            hFXSensor = 0;
        }
        else {
            rightFrontDrive = 21;
            rightBackDrive = 31;
            leftFrontDrive = 22;
            leftBackDrive = 32;
            hallEffectMotor = 33;
            hFXSensor = 0;
        }
    }

    public void init(){

        leftDrive = new TalonSRX(leftFrontDrive);
        leftDrive.setInverted(true);

        rightDrive = new TalonSRX(rightFrontDrive);
        rightDrive.setInverted(false);


        leftSlave = new VictorSPX(leftBackDrive);
        leftSlave.follow(leftDrive);

        rightSlave = new VictorSPX(rightBackDrive);
        rightSlave.follow(rightDrive);

        hallFXMotor = new VictorSPX(hallEffectMotor);

        hallEffect = new DigitalInput(hFXSensor);

        gyro = new ADXRS450_Gyro();
        gyro.reset();
        gyro.calibrate();
    }
}