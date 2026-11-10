package org.firstinspires.ftc.teamcode;

import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name="Parker", group="Auto")
public class AutonomousParker extends OpMode {

    private Follower follower;

    private final PoseFactory p = PoseFactory.degrees();

    private final Pose startPose = p.of(133.1, 66.3, 180);
    private final Pose park = p.of(132.5, 33.44, 180);

    private Path park(){
        return line(startPose, park).linear(startPose, park);
    }
    private Command autoRoutine(){
        //
        return sequential(
                //
                follow(follower, park())
        );
    }
    @Override
    public void init(){
        //
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
        follower.update();
    }
    @Override
    public void start(){
        //
        schedule(autoRoutine());
    }
    @Override
    public void loop(){
        //
        follower.update();
        Scheduler.execute();

        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.addData("Follower Mode", follower.mode());
        telemetry.update();
    }
}
