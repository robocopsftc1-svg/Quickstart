package org.firstinspires.ftc.teamcode.subsystems.CV;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.pedropathing.math.Pose;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.subsystems.HardwareNames;

public class CV {
    Limelight3A computerVision;
    LLResult result;

    private static final double METERS_TO_INCHES = 39.3701;
    private static final double FIELD_HALF_SIZE_INCHES = 72.0;
    private static final double MAX_ANGULAR_VEL_RAD = Math.toRadians(180);

    public CV(HardwareMap hardwareMap){
        computerVision = hardwareMap.get(Limelight3A.class, HardwareNames.LIMELIGHT);
        computerVision.setPollRateHz(100);
        computerVision.start();
    }

    public void scanApril(){
        computerVision.pipelineSwitch(0); //change to scan for april
    }

    public void scanBalls(){
        computerVision.pipelineSwitch(1); //scan for balls in the field
    }
    public void getMT1(){
        if (result != null && result.isValid()) {
            Pose3D botpose = result.getBotpose();
            if (botpose != null) {
                double x = botpose.getPosition().x;
                double y = botpose.getPosition().y;
                telemetry.addData("MT1 Location", "(" + x + ", " + y + ")");
            }
        }
    }
    public void getDataStale(){
        long staleness = result.getStaleness();
        if (staleness < 100) { // Less than 100 milliseconds old
            telemetry.addData("Data", "Good");
        } else {
            telemetry.addData("Data", "Old (" + staleness + " ms)");
        }
    }

    public Pose megaTag2(double hdgRad, double angularVelRad){
        double hdgDeg = Math.toDegrees(hdgRad);
        if(Math.abs(angularVelRad) > MAX_ANGULAR_VEL_RAD){
            return null;
        }
        LLResult result = computerVision.getLatestResult();
        if(result == null || !result.isValid()){
            return null;
        }
        Pose3D mt2Pose = result.getBotpose_MT2();
        if (mt2Pose == null || (mt2Pose.getPosition().x == 0 && mt2Pose.getPosition().y == 0)) {
            return null;
        }
        double pedroX = (mt2Pose.getPosition().x * METERS_TO_INCHES) + FIELD_HALF_SIZE_INCHES;
        double pedroY = (mt2Pose.getPosition().y * METERS_TO_INCHES) + FIELD_HALF_SIZE_INCHES;

        return new Pose(pedroX, pedroY, hdgRad);
    }
}
