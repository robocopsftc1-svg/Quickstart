package org.firstinspires.ftc.teamcode.RobotInfo;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.Intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Turret.Shooter;

public class Robot {
    Intake intake;
    Shooter shooter;
    //add code to make limelight a mechanism class!!!

    public Robot(HardwareMap hardwareMap){
        intake = new Intake(hardwareMap);
        shooter = new Shooter(hardwareMap);
    }
    //teleop
    //runintake

    //shoot

    //open/close stopper

    //autonomous


}
