package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import static org.firstinspires.ftc.teamcode.GyroAuto.States.START;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Basic: GyroAuto", group="Iterative Opmode")

public class GyroAuto extends MyRobot {
    boolean drive;
    MoveParameter mp;
    public States currentState;
    public enum States {
        START,
        FOWARD1,
        GRAB,
        TURN,
        FOWARD2,
        DROP
    }

    @Override
    public void OpModeInit() {
        mp = new MoveParameter();
    }

    @Override
    public void OpModeInit_loop() {

    }

    @Override
    public void OpModeLoop() {
        switch(currentState){
            case START:
                claw1.OpenClaws();
                train1.resetEncoders();
                currentState = States.FOWARD1;
                break;
            case FOWARD1:
                mp.forward = 0.1;
                train1.move(mp);
                if(train1.getRawEncoderValue() <= -1200){
                    currentState = States.GRAB;
                }
                break;
            case GRAB:
                mp.forward = 0;
                train1.move(mp);
                claw1.CloseClaws();
                if(claw1.isClosed()){
                    currentState = States.TURN;
                }
                break;
            case TURN:
                mp.turn = -0.2;
                train1.move(mp);
                if(imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle <= -45){
                    train1.resetEncoders();
                    currentState = States.FOWARD2;
                }
                break;
            case FOWARD2:
                mp.turn = 0;
                mp.forward = 0.1;
                train1.move(mp);
                if(train1.getRawEncoderValue() <= -2400){
                    currentState = States.DROP;
                }
                break;
            case DROP:
                mp.forward = 0;
                train1.move(mp);
                claw1.OpenClaws();
                break;
        }
    }

    @Override
    public void OpModeStart() {
        currentState = START;
    }

    @Override
    public void OpModeStop() {

    }
}
