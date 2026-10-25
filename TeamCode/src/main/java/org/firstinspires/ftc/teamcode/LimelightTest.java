package.org.firstinpires.ftc.teamcode;


import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


public class LimelightTest extends OpMode{
    private  Limelight3A limelight;
    private IMU imu;
    @Override
    public void init(){
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipeLineSwitch();// need to figure out which pipeline april tag is
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.
                UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
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
        if(llResult != null && llResult.isValid()) {
            Pose3D botPose = llResult.getBotpose();
            telemetry.addData("The camera position in relation to the robot. Camera is (0,0,0) when deadcenter.");
            telemetry.addData("Tx", llResult.getTx());
            telemetry.addData("Ty", llResult.getTy());
            telemetry.addData("Ta", llResult.getTa());
            telemetry.addData("Sets the center of the field as (0,0,0). Tells where the robot is on the field");

        }
        if( llResult != null && result.isValid()) {
            Pose3D botpose = result.getBotpose();
            if (botpose != null) {
                double botx = botpose.getPosition().x;
                double boty = botpose.getPosition().y;

                telemetry.addData("botx", botx);
                telemertry.addData("boty", boty);
            }
        }
        else{
            telemetry.addData("No april tag");
        }
        telemetry.update();

    }

}