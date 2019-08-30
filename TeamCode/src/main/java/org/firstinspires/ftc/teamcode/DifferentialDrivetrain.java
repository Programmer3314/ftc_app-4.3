package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.ArrayList;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;
import static org.firstinspires.ftc.teamcode.AllRobots.HMap;
import static org.firstinspires.ftc.teamcode.AllRobots.tele;

public class DifferentialDrivetrain {
    ArrayList<DcMotorEx> leftMotors = new ArrayList<DcMotorEx>();
    ArrayList<DcMotorEx> rightMotors = new ArrayList<DcMotorEx>();
    public double forwardScale, turnScale, strafeScale;
    public double forwardVelocityScale, turnVelocityScale, strafeVelocityScale;
    public double ticksPerInch;

    public DifferentialDrivetrain(String... motors){
        int side = motors.length / 2;
        for(int m = 0; m < side; m++){
            DcMotorEx someMotor  = HMap.get(DcMotorEx.class, motors[m]);
            leftMotors.add(someMotor);
        }

        for(int m = side; m < motors.length; m++){
            DcMotorEx someMotor  = HMap.get(DcMotorEx.class, motors[m]);
            someMotor.setDirection(REVERSE);
            rightMotors.add(someMotor);
        }
        forwardScale = 1;
        turnScale = 1;
        strafeScale = 1;



        resetEncoders();
    }
    public void move( MoveParameter mP ){
        double leftPower;
        double rightPower;
        double leftVelocity;
        double rightVelocity;

        switch(mP.mode){

            case Power:
                leftPower = Range.clip(mP.forward * forwardScale - mP.turn * turnScale, -1.0, 1.0) ;
                rightPower = Range.clip(mP.forward * forwardScale + mP.turn * turnScale, -1.0, 1.0) ;

                for(DcMotorEx M: leftMotors){
                    M.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    M.setPower(leftPower);
                }

                for(DcMotorEx M: rightMotors){
                    M.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    M.setPower(rightPower);
                }
                break;
            case Velocity:
                leftVelocity = mP.forward * forwardVelocityScale - mP.turn * turnVelocityScale ;
                rightVelocity = mP.forward * forwardVelocityScale + mP.turn * turnVelocityScale;

                tele.addData("Left Velocity", leftVelocity);
                tele.addData("Right Velocity", rightVelocity);

                for(DcMotorEx M: leftMotors){
                    M.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
                    M.setVelocity(leftVelocity, AngleUnit.RADIANS);
                }

                for(DcMotorEx M: rightMotors){
                    M.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
                    M.setVelocity(rightVelocity, AngleUnit.RADIANS);
                }
                break;
            case Distance:
                tele.addData("Mode", "MpMode not supported.");
                break;
        }




    }

    public double getRawEncoderValue(){
        return (leftMotors.get(0).getCurrentPosition() + rightMotors.get(0).getCurrentPosition()) / 2;
    }

    public double getEncoderValueInInches(){
        return getRawEncoderValue() / ticksPerInch;
    }

    public void resetEncoders(){
        leftMotors.get(0).setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotors.get(0).setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotors.get(0).setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotors.get(0).setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public DifferentialDrivetrain setPowerScale(double forward, double turn, double strafe){
        forwardScale = forward;
        turnScale = turn;
        strafeScale = strafe;
        return this;
    }

    public DifferentialDrivetrain setVelocityScale(double forward, double turn, double strafe){
        forwardVelocityScale = forward;
        turnVelocityScale = turn;
        strafeVelocityScale = strafe;
        return this;
    }

    public DifferentialDrivetrain setTicksPerInch(double conversion){
        ticksPerInch = conversion;
        return this;
    }

}
