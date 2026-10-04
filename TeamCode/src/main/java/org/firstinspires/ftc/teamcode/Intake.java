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

        for (DcMotor m: new DcMotor[]{left, right})+
        {
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }





    // Pull pollen in
    public void in()
    {
        setPower(INTAKE_POWER);
    }

    // Spit pollen out (clears jams).
    public void out()
    {
        setPower(-REVERSE_POWER);
    }

    public void stop()
    {
        setPower(0.0);
    }

    public double getPower()
    {
        return power;
    }

    private void setPower(double p)
    {
        power = p;
        left.setPower(p);
        right.setPower(p);
    }
}
