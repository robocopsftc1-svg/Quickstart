package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;

@Autonomous (name="Servo test",group="CRServoDemo")
public class CRServoDemo extends OpMode {
    private CRServo servo;
    public void init(){
        //
        servo = hardwareMap.get(CRServo.class, "servo");
    }
    public void start(){
        //
        servo.setPower(1);
    }
    public void loop(){
        //
    }
}
