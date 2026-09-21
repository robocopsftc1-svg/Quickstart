package org.firstinspires.ftc.teamcode.pedro;
//import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.follower.Follower;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.pedropathing.paths.PathConstraints;
//import com.pedropathing.ftc.FollowerBuilder;

import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.algorithm.Foresight;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.localization.Localizer;
import  com.pedropathing.controllers.Controller;

public class Constants {

    //no need for mass input
    //constants.java is split between foresight: the algorithm,our drivetrain config and localization/odometry config
    //once autotuner is done it'll give us copy and paste values here and we'll be good to go




    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("left_front");
        c.frontRightName.set("right_front");
        c.backLeftName.set("left_back");
        c.backRightName.set("right_back");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });

    //public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 67);


    public static Follower create(HardwareMap h) {
        //return new FollowerBuilder(followerConstants, h)
                //.mecanumDrivetrain(driveConfig)
                //.pathConstraints(pathConstraints)
                //.build();

    return null;
    //}
}}