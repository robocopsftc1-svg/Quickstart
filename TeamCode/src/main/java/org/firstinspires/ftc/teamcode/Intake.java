package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.HardwareNames;

public class Intake{

    public static final double INTAKE_POWER  = 1;
    public static final double REVERSE_POWER = 1;

    private static final DcMotorSimple.Direction LEFT_DIRECTION  = DcMotorSimple.Direction.REVERSE;
    private static final DcMotorSimple.Direction RIGHT_DIRECTION = DcMotorSimple.Direction.FORWARD;

    private final DcMotor left;
    private final DcMotor right;
    private double power = 0.0;


    public Intake(HardwareMap hardwareMap)
    {
        left  = hardwareMap.get(DcMotor.class, HardwareNames.INTAKE_LEFT);
        right = hardwareMap.get(DcMotor.class, HardwareNames.INTAKE_RIGHT);

        left.setDirection(LEFT_DIRECTION);
        right.setDirection(RIGHT_DIRECTION);

        for (DcMotor m: new DcMotor[]{left, right})
        {
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }





    // Pull pollen in
    public void in(DcMotor side)
    {
        setPower(INTAKE_POWER, side);
    }

    // Spit pollen out (clears jams).
    public void out(DcMotor side)
    {
        setPower(-REVERSE_POWER, side);
    }

    public void stop()
    {
        setPower(0.0, left);
        setPower(0.0, right);
    }

    public double getPower()
    {
        return power;
    }

    private void setPower(double p, DcMotor side)
    {
        power = p;
        if(side == left){
            left.setPower(p);
        }
        else if(side == right){
            right.setPower(p);
        }

//        left.setPower(p);
//        right.setPower(p);
    }
}
