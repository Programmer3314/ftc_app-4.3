package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class AllRobots extends OpMode {
    public void init(){
        MyRobotInit();
    }
    public abstract void MyRobotInit();
}
