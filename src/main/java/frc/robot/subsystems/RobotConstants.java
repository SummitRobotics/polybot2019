package frc.robot.subsystems;

public class RobotConstants {
    
    /*This class contains important constants relevant to our robot code*/
    /*Nothing in your program should modify these constants*/

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


    /*------------------------------*/
    /*      PART 2   -    OTHER     */
    /*------------------------------*/

    public static final int TICKS_PER_ROTATION = 4096;
    public static final double WHEEL_DIAMETER = 6.0;
}