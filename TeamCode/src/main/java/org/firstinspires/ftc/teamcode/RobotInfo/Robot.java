package org.firstinspires.ftc.teamcode.RobotInfo;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.CV.CV;
import org.firstinspires.ftc.teamcode.subsystems.Intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Stopper.Stopper;
import org.firstinspires.ftc.teamcode.subsystems.Turret.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.deScorer.deScorer;
//add flywheel class and pid to that
public class Robot {
    Intake intake;
    Shooter shooter;
    deScorer deScorer;
    Stopper stopper;
    CV limelight;

    public enum robotStates {
        STOP,
        SHOOT,
        INTAKE,
        FLOWER,
        EXPEL
    }
    public robotStates currentState = robotStates.STOP;



    //add code to make limelight a mechanism class!!!

    public Robot(HardwareMap hardwareMap){
        intake = new Intake(hardwareMap);
        shooter = new Shooter(hardwareMap);
        deScorer = new deScorer(hardwareMap);
        stopper = new Stopper(hardwareMap);
        limelight = new CV(hardwareMap);


    }
    //teleop

    public void runIntake(){

        intake.in();

    }
    public void stopIntake(){
        intake.stop();
    }

    public void closeStopper(){
        stopper.close();
    }
    public void expel(){
        intake.out();
        stopper.open();
    }
    public void runFlywheel(){
        shooter.runFlywheel();
    }
    //shoot
    public void shoot(){
        runIntake();
        stopper.open();
    }
    public void flowerCollect(){
        deScorer.down();
        runIntake();
    }
    public void gateUp(){
        deScorer.up();
    }

    //autonomous

    public void robotUpdate(){
        switch(currentState){
            case STOP:
                stopIntake();

            case EXPEL:
                expel();
            case SHOOT:
                shoot();
            case FLOWER:
                flowerCollect();
            case INTAKE:
                runIntake();
                closeStopper();
                gateUp();
        }
    }


}
