// Consider this a teaser for now

version = 1.0

TalonSRX LeftFrontDrive = 22
TalonSRX RightFrontDrive = 21

VictorSPX LeftBackDrive = 32
VictorSPX RightBackDrive = 31

VictorSPX hallFXMotor = 33

ADXRS450 gyro

DigitalInput hall = 0

LeftBackDrive FOLLOWS LeftFrontDrive
RightBackDrive FOLLOWS LeftFrontDrive

