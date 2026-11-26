package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotInfo.Robot;
@TeleOp(name="TeleOp", group="TeleOp")
public class TeleOpRenewed extends LinearOpMode {
    //initialize


    Robot robotMachine = new Robot(hardwareMap);


    @Override
    public void runOpMode(){
        //init
        telemetry.addData("Robot Activated", robotMachine);


        //wait for play
        waitForStart();

        while(opModeIsActive()){
            //played
            robotMachine.runFlywheel();
            robotMachine.startApril();

        }
    }
}
