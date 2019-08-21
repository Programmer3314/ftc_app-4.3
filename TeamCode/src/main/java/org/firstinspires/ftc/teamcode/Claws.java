package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import static org.firstinspires.ftc.teamcode.AllRobots.HMap;
import static org.firstinspires.ftc.teamcode.AllRobots.tele;


public class Claws {

    public DcMotorEx leftClawMotor, rightClawMotor;
    int close;
    int open;

    public Claws(String lClawMotor, String rClawMotor, int leftClose, int leftOpen){
        leftClawMotor = HMap.get(DcMotorEx.class, lClawMotor);
        rightClawMotor = HMap.get(DcMotorEx.class, rClawMotor);

        close = leftClose;
        open = leftOpen;
    }

    public void StartUp(){

        leftClawMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightClawMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        leftClawMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftClawMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftClawMotor.setTargetPosition(0);
        leftClawMotor.setPower(0.3);

        rightClawMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightClawMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightClawMotor.setTargetPosition(0);
        rightClawMotor.setPower(0.3);
    }

    public void CloseClaws(){
            leftClawMotor.setTargetPosition(close);   //11
            leftClawMotor.setPower(0.3);

            rightClawMotor.setTargetPosition(-close);
            rightClawMotor.setPower(0.3);

    }

    public void OpenClaws(){
            leftClawMotor.setTargetPosition(open); //30
            leftClawMotor.setPower(0.3);

            rightClawMotor.setTargetPosition(-open);
            rightClawMotor.setPower(0.3);

    }


    public void Update(){
        tele.addData("LClawMotorPosition", leftClawMotor.getCurrentPosition());
        tele.addData("RClawMotorPosition", rightClawMotor.getCurrentPosition());
    }

    public boolean isClosed(){
        return (Math.abs(leftClawMotor.getCurrentPosition() - close) <= 5)
                &&
                (Math.abs(rightClawMotor.getCurrentPosition() + close) <= 5);
    }

    public boolean isOpened(){
        return (Math.abs(leftClawMotor.getCurrentPosition() - open) <= 5)
                &&
                (Math.abs(rightClawMotor.getCurrentPosition() + open) <= 5);
    }
}