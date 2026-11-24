package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
@TeleOp(name = "AprilTag Limelight Test")
public class AprilTagLimelight extends OpMode{
    private Limelight3A limelight;
    private IMU imu;

    private double tx;
    private double ty;
    private double ta;
    private double walldistance;
    private double camAngle;
    private double stickerHeight = 42;


    @Override
    public void init(){
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));
    }
    @Override
    public void start(){
        limelight.start();
    }

    @Override
    public void loop(){
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        limelight.updateRobotOrientation(orientation.getYaw());
        LLResult llResult = limelight.getLatestResult();
        if(llResult != null && llResult.isValid()){
            Pose3D botpose = llResult.getBotpose_MT2();
            tx = llResult.getTx();
            ty = llResult.getTy();
            ta = llResult.getTa();
            telemetry.addData("Tx", llResult.getTx());
            telemetry.addData("Ty", llResult.getTy());
            telemetry.addData("Ta", llResult.getTa());
            telemetry.addData("botpose", botpose.toString());
            telemetry.addData("Yaw", botpose.getOrientation().getYaw());

        }
        else{
            telemetry.addData("No April Tag",0);
        }


    }
}
