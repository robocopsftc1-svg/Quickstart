package org.firstinspires.ftc.teamcode.subsystems.Intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.hardware.PwmControl;

import org.firstinspires.ftc.teamcode.subsystems.HardwareNames;
public class Intake
{
    ElapsedTime stopperTimer = new ElapsedTime();
    public static final double INTAKE_POWER  = 1;
    public static final double REVERSE_POWER = 0.5;

    public static final double CLOSED_POSITION = 0.80;
    public static final double OPEN_POSITION = 0.90;

    private static final int PWM_MIN_US = 500;
    private static final int PWM_MAX_US = 2500;


    public enum IntakeState {

        BALL_INTAKE,

        BALL_EXPEND,
        STOP

    }
    public IntakeState currentState = IntakeState.STOP;






    private final DcMotor left;
    private final DcMotor right;

    private double power = 0.0;

    public Intake(HardwareMap hardwareMap)
    {
        left  = hardwareMap.get(DcMotor.class, HardwareNames.INTAKE_LEFT);
        right = hardwareMap.get(DcMotor.class, HardwareNames.INTAKE_RIGHT);






        for (DcMotor m: new DcMotor[]{left,right})
        {
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    // Pull pollen in
    public void in()
    {
        left.setPower(INTAKE_POWER);
        right.setPower(INTAKE_POWER);

    }

    // Spit pollen out (clears jams).
    public void out()
    {
        left.setPower(-REVERSE_POWER);
        right.setPower(-REVERSE_POWER);

    }

    public void stop()
    {
        left.setPower(0);
        right.setPower(0);

    }

    public double getPower()
    {
        return left.getPower();
    }

    // Run each side separately (used by IntakeTest to check each motor's direction)
    public void setPowers(double leftPower, double rightPower)
    {
        left.setPower(leftPower);
        //right.setPower(rightPower);
        power = Math.abs(leftPower) >= Math.abs(rightPower) ? leftPower : rightPower;
    }

    private void setPower(double p)
    {
        setPowers(p, p);
    }

    //now we have the FSM loop to be able to cycle the system
    public void intakeUpdate(){
        //
        switch (currentState){


            case BALL_INTAKE:
                left.setPower(INTAKE_POWER);
                right.setPower(INTAKE_POWER);
                break;


            case BALL_EXPEND:
                left.setPower(-REVERSE_POWER);
                right.setPower(-REVERSE_POWER);
                break;



            case STOP:


                left.setPower(0);
                right.setPower(0);
                break;
        }
    }


}