package org.firstinspires.ftc.teamcode;

public class MoveParameter {
    public double turn;
    public double forward;
    public double strafe;

    public enum Modes{
        Power, Velocity, Distance
    }
    public Modes mode = Modes.Power;

}


