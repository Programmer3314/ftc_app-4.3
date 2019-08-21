package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class MyRobot extends AllRobots {
    public DifferentialDrivetrain train1;
    public Claws claw1;
    public BNO055IMU imu;


//    public DcMotor frontLeftDrive = null;
//    public DcMotor frontRightDrive = null;
//    public DcMotor backRightDrive = null;
//    public DcMotor backLeftDrive = null;

    public void MyRobotInit(){
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
//        frontLeftDrive  = hardwareMap.get(DcMotor.class, "frontLeftMotor");
//        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightMotor");
//        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftMotor");
//        backRightDrive = hardwareMap.get(DcMotor.class, "backRightMotor");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
//        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
//        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
//        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
//        backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        train1 = new DifferentialDrivetrain("frontLeftMotor", "backLeftMotor", "frontRightMotor", "backRightMotor")
                .setPowerScale(-1,-1,-1);
        claw1 = new Claws("LClawMotor", "RClawMotor", 11, -30);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        claw1.StartUp();
        OpModeInit();
    }

    public abstract void OpModeInit();

    public void MyRobotInit_loop(){

        OpModeInit_loop();
    }
    public abstract void OpModeInit_loop();

    public void MyRobotLoop(){

        OpModeLoop();
        claw1.Update();
    }
    public abstract void OpModeLoop();

    public void MyRobotStart(){

        OpModeStart();
    }
    public abstract void OpModeStart();

    public void MyRobotStop(){

        OpModeStop();
    }
    public abstract void OpModeStop();




}
