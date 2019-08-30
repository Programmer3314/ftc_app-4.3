/* Copyright (close) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;



@TeleOp(name="Basic: First OpMode", group="Iterative Opmode")
//@Disabled
public class FirstOpMode extends MyRobot
{
    public MoveParameter moveParam;
    public Orientation angles;
    public JoystickAxis forward;
    public JoystickAxis turn;
    public JoystickAxis strafe;

    @Override
    public void OpModeInit() {
        moveParam = new MoveParameter();
        forward = new JoystickAxis(JoystickAxis.Axis.leftY, gamepad1).setScale(-10);
        turn = new JoystickAxis(JoystickAxis.Axis.rightX, gamepad1).setScale(-10);
        strafe = new JoystickAxis(JoystickAxis.Axis.leftX, gamepad1).setScale(-10);

    }
    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void OpModeInit_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void OpModeStart() {

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void OpModeLoop() {
        // Setup a variable for each drive wheel to save power level for telemetry
        double leftPower;
        double rightPower;
        angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);


        // Send calculated power to wheels
        moveParam.strafe = strafe.getValue();
        moveParam.forward = forward.getValue();
        moveParam.turn = turn.getValue();
        moveParam.mode = MoveParameter.Modes.Velocity;
        train1.move(moveParam);

        if(gamepad1.a){
            claw1.CloseClaws();
        }

        if(gamepad1.b){
           claw1.OpenClaws();
        }

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("LeftMotor Encoder Value", "Encoder " + Double.toString(train1.getRawEncoderValue()));
        telemetry.addData("XXX", "heading: " + angles.firstAngle);

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void OpModeStop() {
    }

}
