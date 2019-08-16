package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class MyRobot extends AllRobots {
    public DifferentialDrivetrain train1;
    public Claws claw1;


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
        train1 = new DifferentialDrivetrain("frontLeftMotor", "backLeftMotor", "frontRightMotor", "backRightMotor");
        claw1 = new Claws("LClawMotor", "RClawMotor", 11, -30);

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
