package org.firstinspires.ftc.teamcode.mechanisms;

import android.health.connect.datatypes.units.Power;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.HardwareNames;

//for now the code should be for the temporary fixed angle shooter with a single motor spinning the wheel out and the turret rotates
//motor that shoots: shooter_driver
//motor than spins turret:shooter_spin

//states:
//hold
//fire
//stop
public class Shooter {
    public static final double SHOOTER_POWER = 0.55;
    private static final DcMotorSimple.Direction SHOOTER_DRIVER = DcMotorSimple.Direction.FORWARD;
    private static final DcMotorSimple.Direction SHOOTER_SPIN = DcMotorSimple.Direction.FORWARD;
    public enum ShooterState {
        //
        HOLD, FIRE, STOP
    }
    public ShooterState currentShooterState = ShooterState.HOLD;
    private DcMotor shooter_driver;
    private DcMotor shooter_driver_two;
    private DcMotor shooter_spin;
    private double power = 0.0;


    public Shooter(HardwareMap hardwareMap){
        shooter_driver = hardwareMap.get(DcMotor.class, HardwareNames.SHOOTER_DRIVER);
        shooter_driver_two = hardwareMap.get(DcMotor.class, "second_shooter");
        shooter_spin = hardwareMap.get(DcMotor.class, HardwareNames.SHOOTER_SPIN);
    }

    //public void spinShooting() {setPower(SHOOTER_POWER);}
    private void setPower(){
        //
        shooter_driver.setPower(SHOOTER_POWER);
    }
    public void update(){
        //
        switch(currentShooterState){
            case HOLD:
                //
                shooter_driver.setPower(0);
                shooter_driver_two.setPower(0);
            case FIRE:
                shooter_driver.setPower(SHOOTER_POWER);
                shooter_driver_two.setPower(1);
            case STOP:
                shooter_driver.setPower(0);
                shooter_driver_two.setPower(0);

        }
    }
}
