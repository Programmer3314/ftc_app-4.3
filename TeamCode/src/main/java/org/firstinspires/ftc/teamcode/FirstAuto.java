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
        telemetry.addData("Motors Encoder Value In Inches ", "Encoder: " + Double.toString(train1.getEncoderValueInInches()));
        telemetry.addData("Left Encoder", "Encoder: " + Double.toString(train1.leftMotors.get(0).getCurrentPosition()));
        telemetry.addData("right Encoder", "Encoder: " + Double.toString(train1.rightMotors.get(0).getCurrentPosition()));
        telemetry.addData("raw Encoder", "Encoder: " + Double.toString(train1.getRawEncoderValue()));

        if(drive){
            mp.forward = -0.2;
            train1.move(mp);
            drive = train1.getEncoderValueInInches() < 9.15;
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
