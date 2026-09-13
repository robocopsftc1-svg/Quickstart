package org.firstinspires.ftc.teamcode.pedro;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.follower.Follower;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.pedropathing.paths.PathConstraints;
import com.pedropathing.ftc.FollowerBuilder;
public class Constants {

    //public static FollowerConstants followerConstants = new FollowerConstants()
    //    .mass(10);


    public static MecanumConfig driveConfig = new MecanumConfig(
            c -> {
                c.frontLeftName.set("left_front");
                c.backLeftName.set("left_back");
                c.frontRightName.set("right_front");
                c.backRightName.set("right_back");

                c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
                c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
            }
    );


    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 67);


    public static Follower create(HardwareMap h) {
        return new FollowerBuilder(followerConstants, h)
                .mecanumDrivetrain(driveConfig)
                .pathConstraints(pathConstraints)
                .build();


    }
}