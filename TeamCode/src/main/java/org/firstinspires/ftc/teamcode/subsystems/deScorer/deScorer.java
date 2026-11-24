package org.firstinspires.ftc.teamcode.subsystems.deScorer;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.HardwareNames;

public class deScorer {
    private final Servo stopper_left;
    private final Servo stopper_right;

    public deScorer(HardwareMap hardwareMap){
        stopper_left = hardwareMap.get(Servo.class, HardwareNames.RAMP_LEFT);
        stopper_right = hardwareMap.get(Servo.class, HardwareNames.RAMP_RIGHT);
    }
}
