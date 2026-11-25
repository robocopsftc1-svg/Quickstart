package org.firstinspires.ftc.teamcode.subsystems.Stopper;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.HardwareNames;

public class Stopper {

    private final Servo stopper;

    public Stopper(HardwareMap hardwareMap){
        //
        stopper = hardwareMap.get(Servo.class, HardwareNames.STOPPER);
    }



    public void open(){
        stopper.setPosition(0.971);
    }

    public void close(){
        stopper.setPosition(1);
    }
}
