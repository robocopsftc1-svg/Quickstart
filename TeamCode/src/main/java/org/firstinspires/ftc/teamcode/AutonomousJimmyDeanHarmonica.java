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
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

@Autonomous (name="Auto", group = "OpMode")
public class AutonomousJimmyDeanHarmonica extends OpMode {
    //constants
    private Follower follower;
    private Intake intakeMachine;
    private final PoseFactory p = PoseFactory.degrees();

    //poses (points that are used to crate points to go from a to b)
    //example poses
    private final Pose startPose = p.of(84.7,132.3,270);
    private final Pose park = p.of(84.7,132.3,90);
    private final Pose scorePose = p.of(84.08, 101.26, 270);
    //necessary for curve
    private final Pose controlPose = p.of(36, 60, 45);
    //note to self points entered as follows
    //p.of(x,y,heading);
    //our actual paths made from poses
    private Path park(){
        return line(startPose, park).linear(startPose, park);//line
        //return curve(startPose, controlPose, park).linear(startPose, park); //bezier curve
    }
    private Path startToScore() {
        return line(startPose, scorePose).linear(startPose, scorePose);
    }
    private Path scoreToPark() {
        return line(scorePose, park).linear(scorePose, park);
    }
    //command
    private Command autoRoutine() {
        return sequential(


                follow(follower, startToScore()),
                // Add mechanism commands here.
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
        intakeMachine = new Intake(hardwareMap);
    }
    @Override
    public void start(){
        //
        schedule(autoRoutine());
    }//h
    @Override
    public void loop(){
        //
        follower.update();
        Scheduler.execute();
        intakeMachine.update();


        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.addData("Follower Mode", follower.mode());
        telemetry.update();
    }

}
