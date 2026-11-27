package org.firstinspires.ftc.teamcode.RobotInfo;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.CV.CV;
import org.firstinspires.ftc.teamcode.subsystems.Intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Stopper.Stopper;
import org.firstinspires.ftc.teamcode.subsystems.Turret.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.deScorer.deScorer;
import org.firstinspires.ftc.teamcode.subsystems.Turret.fwheel.Flywheel;
//add flywheel class and pid to that
public class Robot {
    Intake intake;
    Shooter shooter;
    deScorer deScorer;
    Stopper stopper;
    CV limelight;
    Flywheel flywheel;
    ElapsedTime shootCount;
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
        flywheel = new Flywheel(hardwareMap);
        shootCount = new ElapsedTime();

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

    public double getIntakePower(){
        return intake.getPower();
    }
    public void expel(){
        intake.out();
        stopper.open();
    }
    public void runFlywheel(){
        flywheel.setMotorRPM(4080);
    }
    //shoot
    public void shoot(){
        runIntake();

        shootCount.reset();
        if(shootCount.seconds() >= 0.5){
            stopper.open();
        }

    }
    public void flowerCollect(){
        deScorer.down();
        runIntake();
    }
    public void gateUp(){
        deScorer.up();
    }


    public void startApril(){
        limelight.scanApril();
    }

    public Pose megaTag2(double a, double b){
        return limelight.megaTag2(a,b);
    }

    //autonomous

    public void robotUpdate(){
        switch(currentState){
            case STOP:
                stopIntake();
                closeStopper();
                flowerCollect();
                break;

            case EXPEL:
                expel();
                break;
            case SHOOT:
                shoot();
                break;
            case FLOWER:
                flowerCollect();
                break;
            case INTAKE:
                runIntake();
                closeStopper();
                gateUp();
                break;
        }
    }


}
