package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Basic: FirstAuto", group="Iterative Opmode")
public class FirstAuto extends MyRobot {
    boolean drive;
    MoveParameter mp;

    @Override
    public void OpModeInit() {
        drive = true;
        mp = new MoveParameter();
    }

    @Override
    public void OpModeInit_loop() {

    }

    @Override
    public void OpModeLoop() {
        telemetry.addData("LeftMotor Encoder Value", "Encoder " + Double.toString(train1.getEncoderValue()));

        if(drive){
            mp.forward = -0.2;
            train1.move(mp);
            if(train1.getEncoderValue() <= -1200){
                drive = false;
            }
            telemetry.addData("DriveMode","Driving");
        }else{
            mp.forward = 0;
            train1.move(mp);
            claw1.CloseClaws();
            telemetry.addData("DriveMode","Stopped/ClosingClaw");
        }
    }

    @Override
    public void OpModeStart() {
        claw1.OpenClaws();
    }

    @Override
    public void OpModeStop() {

    }
}
