package org.firstinspires.ftc.teamcode.RobotInfo;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.Intake.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Turret.Shooter;

public class Robot {
    Intake intake;
    Shooter shooter;
    //add code to make limelight a mechanism class!!!

    public Robot(HardwareMap hardwareMap){
        intake = new Intake(hardwareMap);
        shooter = new Shooter(hardwareMap);
    }



}
