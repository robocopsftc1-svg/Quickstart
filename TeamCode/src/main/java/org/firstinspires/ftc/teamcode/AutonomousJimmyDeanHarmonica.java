package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.pedropathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import static com.pedropathing.api.Paths.*;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
@Autonomous
public class AutonomousJimmyDeanHarmonica extends OpMode {
    //constants
    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();

    //poses (points that are used to crate points to go from a to b)
    //example poses
    private final Pose startPose = p.of(24,24,0);
    private final Pose park = p.of(48,48,90);

    private final Pose controlPose = p.of(36, 60, 45);
    //note to self points entered as follows
    //p.of(x,y,heading);

    private Path park(){
        return line(startPose, park).linear(startPose, park);//line
        //return curve(startPose, controlPose, park).linear(startPose, park); //bezier curve
    }

    @Override
    public void init(){
        //
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
    }
    @Override
    public void start(){
        //
        schedule(follow(follower, park()));
    }
    @Override
    public void loop(){
        //
        follower.update();
        Scheduler.execute();
    }

}
