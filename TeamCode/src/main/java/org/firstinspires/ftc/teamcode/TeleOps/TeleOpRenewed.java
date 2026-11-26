package org.firstinspires.ftc.teamcode.TeleOps;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.RobotInfo.Robot;
@TeleOp(name="TeleOp", group="TeleOp")
public class TeleOpRenewed extends LinearOpMode {
    //initialize


    Robot robotMachine = new Robot(hardwareMap);
    Follower follower;
    double forward = -gamepad1.left_stick_y;
    double lateral = gamepad1.left_stick_x;
    double turn = gamepad1.right_stick_x;

    @Override
    public void runOpMode(){
        //init
        telemetry.addData("Robot Activated", robotMachine);
        follower = Constants.create(hardwareMap);

        //wait for play
        waitForStart();

        while(opModeIsActive()){
            //played
            robotMachine.runFlywheel();
            robotMachine.startApril();
            if(gamepad1.right_bumper){
                robotMachine.runIntake();
            }

            if (gamepad1.right_trigger_pressed) {
                robotMachine.shoot();
            }

            if(gamepad1.left_bumper){
                robotMachine.expel();
            }
            follower.manual(forward, lateral, turn);
            follower.update();

        }
    }
}
