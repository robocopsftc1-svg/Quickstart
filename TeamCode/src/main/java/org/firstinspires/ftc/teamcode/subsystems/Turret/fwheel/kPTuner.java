package org.firstinspires.ftc.teamcode.subsystems.Turret.fwheel;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="kP", group="TeleOp")
public class kPTuner extends OpMode
{
    Flywheel flywheel = new Flywheel(hardwareMap);

    public static double kV = 0.01;

    public double kS;
    public double kP = 0.0;
    public double goalRPM = 2700;

    double[] increments = {0.000001, 0.00001, 0.0001, 0.001, 0.01};

    int incrementIdx = 2;

    @Override
    public void init()
    {
        flywheel = new Flywheel(hardwareMap);
    }

    @Override
    public void loop()
    {
        if(gamepad1.dpadRightWasPressed() && incrementIdx < 4)
        {
            incrementIdx++;
        }
        else if (gamepad1.dpadLeftWasPressed() && incrementIdx > 0)
        {
            incrementIdx--;
        }

        if(gamepad1.a)
        {
            goalRPM = 2700;
        }
        else if(gamepad1.b)
        {
            goalRPM = 1500; //temp value
        }

        double currentStep = increments[incrementIdx];

        if(gamepad1.dpadUpWasPressed())
        {
            kP +=currentStep;
        }
        if(gamepad1.dpadDownWasPressed())
        {
            kP -=currentStep;
        }

        double feedForward = (kV * goalRPM) + kS;
        double error = goalRPM - flywheel.getRPM();
        double feedback = error * kP;

        flywheel.setPower(feedForward + feedback);

        telemetry.addData("Step", "%.6f", currentStep);
        telemetry.addData("kP","%.6f", kP);
        telemetry.addData("Error", error);
        telemetry.addData("RPM",flywheel.getRPM());
    }
}
