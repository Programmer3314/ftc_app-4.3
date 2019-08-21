package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class AllRobots extends OpMode {

    protected ElapsedTime runtime = new ElapsedTime();
    public static HardwareMap HMap;
    public static Telemetry tele;

    public final void init(){

        telemetry.addData("Status", "Initializing");
        tele = telemetry;

        HMap = hardwareMap;
        MyRobotInit();

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }
    public abstract void MyRobotInit();

    public final void init_loop(){

        MyRobotInit_loop();
    }
    public abstract void MyRobotInit_loop();

    public final void loop(){
        MyRobotLoop();
    }
    public abstract void MyRobotLoop();

    public final void start(){
        runtime.reset();
        MyRobotStart();
    }
    public abstract void MyRobotStart();

    public final void stop(){
        MyRobotStop();
    }
    public abstract void MyRobotStop();
}
