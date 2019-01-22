package frc.robot;

public class RobotConstants {
    
    /*This class contains important constants relevant to our robot code*/

    /*------------------------------*/
    /*    PART 1  -  PORT NUMBERS   */
    /*------------------------------*/
    
    //LEFT_FRONT_DRIVE is a Talon SRX
    public static final int LEFT_FRONT_DRIVE = 22;
    //LEFT_BACK_DRIVE_ is a Victor SPX
    public static final int LEFT_BACK_DRIVE = 32;
    //RIGHT_FRONT_DRIVE is a Talon SRX
    public static final int RIGHT_FRONT_DRIVE = 21;
    //RIGHT_BACK_DRIVE is a Victor SPX
    public static final int RIGHT_BACK_DRIVE = 31;

    //TEST_MOTOR is a Victor SPX
    public static final int TEST_MOTOR = 33;

    //This is a hall-effect digital input sensor;
    public static final int HALL_FX = 1;

    //This is the motor controller connected to the Pigeon IMU
    public static final int UNUSED = 23;

    //This is the SPARK Max motor controllers
    public static final int SPARK_MAX_1 = 41;
    public static final int SPARK_MAX_2 = 42;
    public static final int SPARK_MAX_3 = 43;


    /*------------------------------*/
    /*      PART 2   -    OTHER     */
    /*------------------------------*/

    public static final int TICKS_PER_ROTATION = 4096;
    public static final double WHEEL_DIAMETER = 6.0;

    public static final double ROCKET_PORT_HEIGHT = 39.125;
    public static final double HATCH_HEIGHT_TEST = 28.5;
}