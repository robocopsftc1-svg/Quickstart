package org.firstinspires.ftc.teamcode.TeleOps;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.RobotInfo.Robot;
@TeleOp(name="TeleOpRenewewed", group="TeleOp")
public class TeleOpRenewed extends LinearOpMode {
    //initialize


    Robot robotMachine;
    Follower follower;
    double forward = -gamepad1.left_stick_y;
    double lateral = gamepad1.left_stick_x;
    double turn = gamepad1.right_stick_x;
    boolean fieldCentric = false;
    boolean lastButtonState = false;

    @Override
    public void runOpMode(){
        //init

        robotMachine = new Robot(hardwareMap);
        telemetry.addData("Robot Activated", robotMachine);
        follower = Constants.create(hardwareMap);

        //wait for play
        waitForStart();

        while(opModeIsActive()){
            //played
            follower.update();
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

            if(gamepad1.y && !fieldCentric){
                ManualDrive.fieldCentric(forward, lateral, turn, follower.pose().heading());
                fieldCentric = true;

            }
            if(gamepad1.y && fieldCentric){
                follower.manual(forward, lateral, turn);
                fieldCentric = false;
            }



            Pose visionPose = robotMachine.megaTag2(
                    follower.pose().heading(),
                    follower.velocity().omega
            );

            if (visionPose != null) {
                follower.setPose(visionPose);
            }
        }
    }
}
