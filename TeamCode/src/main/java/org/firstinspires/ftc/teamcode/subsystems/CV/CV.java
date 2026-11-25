package org.firstinspires.ftc.teamcode.subsystems.CV;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

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
}
