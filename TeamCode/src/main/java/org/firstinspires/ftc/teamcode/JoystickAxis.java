package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class JoystickAxis  {



    double scale;

    public enum Axis{leftX, leftY, rightX, rightY, leftTrig, rightTrig}
    public Axis currentAxis;
    public Gamepad controller;

    public JoystickAxis(Axis axis, Gamepad controller){
        currentAxis = axis;
        this.controller = controller;
    }

    public JoystickAxis setScale(double scale) {
        this.scale = scale;
        return this;
    }

    public double getRawValue(){

        switch(currentAxis){
            case leftX:
                return controller.left_stick_x;


            case leftY:
                return controller.left_stick_y;


            case rightX:
                return controller.right_stick_x;


            case rightY:
                return controller.right_stick_y;


            case leftTrig:
                return controller.left_trigger;


            case rightTrig:
                return controller.right_trigger;

                default:
                    return 0;

        }

    }

    public double getValue(){

        return getRawValue() * scale;
    }
}
