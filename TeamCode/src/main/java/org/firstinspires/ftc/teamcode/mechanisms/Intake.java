package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.hardware.PwmControl;
import org.firstinspires.ftc.teamcode.HardwareNames;


//intake structure
//the intake spins up the outer/inner stage (left and right)
//intake takes in ball
//ball is in intake until the stopper is opened (servo)
//addendum
//now only one motor. we'll just call it "left"

//states
//START
//BALL_INTAKE
//BALL_WAIT
//BALL_DEPLOY
//STOP
//BALL_EXPEND (outtake)


//note to self
//period of stopper is 0.522 seconds
//so, 90 degrees is 0.1305 seconds

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
        START,
        BALL_INTAKE,
        //BALL_WAIT,
        BALL_DEPLOY,
        BALL_EXPEND,
        STOP

    }
    public IntakeState currentState = IntakeState.START;



    private static final DcMotorSimple.Direction LEFT_DIRECTION  = DcMotorSimple.Direction.REVERSE;
    private static final DcMotorSimple.Direction RIGHT_DIRECTION = DcMotorSimple.Direction.FORWARD;

    public final Servo ramp_left;
    public final Servo ramp_right;

    private final Servo stopper;
    private final DcMotor left;
    //private final DcMotor right;
    private double power = 0.0;

    public Intake(HardwareMap hardwareMap)
    {
        left  = hardwareMap.get(DcMotor.class, HardwareNames.INTAKE_LEFT);
        //right = hardwareMap.get(DcMotor.class, HardwareNames.INTAKE_RIGHT);
        stopper = hardwareMap.get(Servo.class, HardwareNames.STOPPER);
        ((ServoImplEx) stopper).setPwmRange(new PwmControl.PwmRange(500, 2500));
        left.setDirection(LEFT_DIRECTION);
        ramp_left=hardwareMap.get(Servo.class, HardwareNames.RAMP_LEFT);
        ramp_right=hardwareMap.get(Servo.class, HardwareNames.RAMP_RIGHT);

        //right.setDirection(RIGHT_DIRECTION);

        for (DcMotor m: new DcMotor[]{left})
        {
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    // Pull pollen in
    public void in()
    {
        setPower(INTAKE_POWER);
        ramp_left.setPosition(OPEN_POSITION);
        ramp_right.setPosition(OPEN_POSITION);
        stopper.setPosition(CLOSED_POSITION);
        //add ramp and stopper positions in the future
        stopperTimer.reset();
        if(stopperTimer.seconds() > 5){
            stopper.setPosition(0.6);
        }
    }

    // Spit pollen out (clears jams).
    public void out()
    {
        setPower(-REVERSE_POWER);
        stopper.setPosition(OPEN_POSITION);
        ramp_left.setPosition(OPEN_POSITION);
        ramp_right.setPosition(OPEN_POSITION);
        stopperTimer.reset();
        if(stopperTimer.seconds() > 5){
            stopper.setPosition(0.6);
        }
    }

    public void stop()
    {
        setPower(0.0);
        ramp_left.setPosition(CLOSED_POSITION);
        ramp_right.setPosition(CLOSED_POSITION);
        stopper.setPosition(CLOSED_POSITION);
    }

    public double getPower()
    {
        return power;
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
    public void update(){
        //
        switch (currentState){
            //
            case START:
                left.setPower(0);
                stopper.setPosition(CLOSED_POSITION);
                //right.setPower(0);

            case BALL_INTAKE:
                left.setPower(INTAKE_POWER);
                ramp_left.setPosition(OPEN_POSITION);
                ramp_right.setPosition(OPEN_POSITION);
                stopper.setPosition(CLOSED_POSITION);
                //right.setPower(INTAKE_POWER);
            case BALL_DEPLOY:
                left.setPower(INTAKE_POWER);
                //right.setPower(INTAKE_POWER);
                stopper.setPosition(OPEN_POSITION);
                //now we have to add stopper logic


                //open

                stopper.setPosition(0.3);

                stopperTimer.reset();

                //close
//                if(stopperTimer.seconds() >= 5){
//                    stopperTimer.reset();
//                    stopper.setPosition(0.47);
//
//                }
            case BALL_EXPEND:
                left.setPower(-REVERSE_POWER);
                ramp_left.setPosition(OPEN_POSITION);
                ramp_right.setPosition(OPEN_POSITION);
                //right.setPower(-REVERSE_POWER);
                //open
                //stopperTimer.reset();
                stopper.setPosition(OPEN_POSITION);

                //stopperTimer.reset();

                //close


            case STOP:
                ramp_left.setPosition(CLOSED_POSITION);
                ramp_right.setPosition(CLOSED_POSITION);
                stopper.setPosition(CLOSED_POSITION);
                left.setPower(0);
        }
    }


}