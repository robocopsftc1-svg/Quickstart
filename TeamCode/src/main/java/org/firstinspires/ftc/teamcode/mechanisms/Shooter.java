package org.firstinspires.ftc.teamcode.mechanisms;

import android.health.connect.datatypes.units.Power;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.HardwareNames;

//for now the code should be for the temporary fixed angle shooter with a single motor spinning the wheel out and the turret rotates
//motor that shoots: shooter_driver
//motor than spins turret:shooter_spin
public class Shooter {
    public static final double SHOOTER_POWER = 1;
    private static final DcMotorSimple.Direction SHOOTER_DRIVER = DcMotorSimple.Direction.FORWARD;
    private static final DcMotorSimple.Direction SHOOTER_SPIN = DcMotorSimple.Direction.FORWARD;

    private DcMotor shooter_driver;
    private DcMotor shooter_spin;
    private double power = 0.0;


    public Shooter(HardwareMap hardwareMap){
        shooter_driver = hardwareMap.get(DcMotor.class, HardwareNames.SHOOTER_DRIVER);
        shooter_spin = hardwareMap.get(DcMotor.class, HardwareNames.SHOOTER_SPIN);
    }

    //public void spinShooting() {setPower(SHOOTER_POWER);}
    private void setPower(){
        //
        shooter_driver.setPower(SHOOTER_POWER);
    }

}
