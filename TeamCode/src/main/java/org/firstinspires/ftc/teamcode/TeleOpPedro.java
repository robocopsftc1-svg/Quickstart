package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp (name = "TeleOP Pedro", group = "Linear OpMode")
public class TeleOpPedro extends LinearOpMode {
    //initialize hardware variables
    private ElapsedTime runtime = new ElapsedTime();
    //    private DcMotor frontLeftDrive = null;
//    private DcMotor backLeftDrive = null;
//    private DcMotor frontRightDrive = null;
//    private DcMotor backRightDrive = null;
    private DcMotor intakeLeft = null;
    private DcMotor intakeRight = null;
    private Follower follower;

    private double axial;  // Note: pushing stick forward gives negative value
    private double lateral;
    private double yaw;


    //waitForStart();
    @Override
    public void runOpMode(){
        //
        follower = Constants.create(hardwareMap);
        intakeLeft = hardwareMap.get(DcMotor.class, "intake_left");
        intakeRight = hardwareMap.get(DcMotor.class, "intake_right");

        Intake intake = new Intake(hardwareMap);
        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        //------------------------------------------------------
        while (opModeIsActive()){
            //
            double forward = -gamepad1.left_stick_y;
            double lateral = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;
            follower.manual(forward, lateral, turn);
            follower.update();
        }
    }
}
