package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;

@TeleOp(name = "Drive", group = "TeleOp")
public class message extends LinearOpMode
{

    private static final double SLOW_MODE_SCALE = 0.35;

    // Match timing (seconds). Teleop is 2:00; flowers open with 60 s left.
    private static final double TELEOP_LENGTH_S = 120.0;
    private static final double FLOWERS_OPEN_REMAINING_S = 60.0;
    private static final double FINAL_WARNING_REMAINING_S = 30.0;
    private boolean flowersWarned = false;
    private boolean finalWarned = false;

    private DcMotor shooter_driver;

    private final ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontLeft, backLeft, frontRight, backRight;
    private Intake intake;

    @Override
    public void runOpMode() {
        frontLeft  = hardwareMap.get(DcMotor.class, HardwareNames.FRONT_LEFT);
        backLeft   = hardwareMap.get(DcMotor.class, HardwareNames.BACK_LEFT);
        frontRight = hardwareMap.get(DcMotor.class, HardwareNames.FRONT_RIGHT);
        backRight  = hardwareMap.get(DcMotor.class, HardwareNames.BACK_RIGHT);
        shooter_driver = hardwareMap.get(DcMotor.class, HardwareNames.SHOOTER_DRIVER);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);


        shooter_driver.setDirection(DcMotorSimple.Direction.FORWARD);
        for (DcMotor m : new DcMotor[]{frontLeft, backLeft, frontRight, backRight}) {
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        intake = new Intake(hardwareMap);


        telemetry.addData("Status", "Initialized - press START");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            double axial   = -gamepad1.left_stick_y;
            double lateral =  gamepad1.left_stick_x;
            double yaw     =  gamepad1.right_stick_x;

            double flPower = axial + lateral + yaw;
            double frPower = axial - lateral - yaw;
            double blPower = axial - lateral + yaw;
            double brPower = axial + lateral - yaw;

            double max = Math.max(Math.max(Math.abs(flPower), Math.abs(frPower)),
                                  Math.max(Math.abs(blPower), Math.abs(brPower)));
            if (max > 1.0) {
                flPower /= max;
                frPower /= max;
                blPower /= max;
                brPower /= max;
            }

            boolean slow = gamepad1.right_bumper;
            double scale = slow ? SLOW_MODE_SCALE : 1.0;

            frontLeft.setPower(flPower * scale);
            frontRight.setPower(frPower * scale);
            backLeft.setPower(blPower * scale);
            backRight.setPower(brPower * scale);

            double remaining = TELEOP_LENGTH_S - runtime.seconds();
            if (!flowersWarned && remaining <= FLOWERS_OPEN_REMAINING_S) {
                flowersWarned = true;
                gamepad1.rumbleBlips(2);
            }
            if (!finalWarned && remaining <= FINAL_WARNING_REMAINING_S) {
                finalWarned = true;
                gamepad1.rumbleBlips(3);
            }

            if (gamepad1.a) {
                intake.out();
            } else if (gamepad1.b) {
                intake.in();
            } else {
                intake.stop();
            }

            if(gamepad1.left_trigger_pressed){
                shooter_driver.setPower(-gamepad1.left_trigger);
            }
            else{
                shooter_driver.setPower(0);
            }


            telemetry.addData("Time left", "%.0f s", Math.max(0, remaining));
            telemetry.addData("Slow mode", slow ? "ON" : "off");
            telemetry.addData("Intake power", "%.2f", intake.getPower());
            telemetry.addData("Front L/R", "%.2f, %.2f", flPower * scale, frPower * scale);
            telemetry.addData("Back  L/R", "%.2f, %.2f", blPower * scale, brPower * scale);
            telemetry.update();
        }
    }
}
