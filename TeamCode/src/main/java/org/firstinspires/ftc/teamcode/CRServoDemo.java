package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
//double for setposition: target angle / total angle that servo can turn
@Autonomous (name="Servo test",group="CRServoDemo")
public class CRServoDemo extends OpMode {
    private Servo servo;
    public void init(){
        //
        servo = hardwareMap.get(Servo.class, HardwareNames.STOPPER);
    }
    public void start(){
        //
        servo.setPosition(0.3);
    }
    public void loop(){
        //
    }
}
