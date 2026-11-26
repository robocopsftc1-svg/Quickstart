package org.firstinspires.ftc.teamcode.pedro;
//import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.pedropathing.paths.PathConstraints;
//import com.pedropathing.ftc.FollowerBuilder;

import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.algorithm.Foresight;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.localization.Localizer;
import  com.pedropathing.controllers.Controller;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

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
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(5.787559569351317);
        c.yPodOffset.set(-1.8182572792834186);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });
    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.27092503971859777);
                Controller secondaryTranslationalForward = Controller.proportional(0.10009956292563339);
                Controller primaryTranslationalLateral = Controller.proportional(0.37637178780195885);
                Controller secondaryTranslationalLateral = Controller.proportional(0.13905931875343425);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.018982987156171416));
                c.brake.set(Controller.proportionalFeedforward(0.016135539082745703));

                c.headingFeedback.set(Controller.proportional(3.488081911516388));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.05186376526041396, 0.007046170665137537));

                c.linearBrakeCoefficients.set(Matrix.diag(0.08649834080269556, 0.07946108038808129));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0018927408064213342, 0.0012233063109937112));

                c.maxAchievableForwardVelocity.set(52.58925467211217);
                c.maxAchievableStrafeVelocity.set(42.90202111128971);
                c.naturalForwardDeceleration.set(35.125160859002285);
                c.naturalStrafeDeceleration.set(64.96695061970388);
            }
    );
    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig), // 1. Swap with your localizer (e.g., ThreeWheelLocalizer)
                new Mecanum(h, drivetrainConfig),         // 2. Swap with your drivetrain type
                new Foresight(foresightConfig)
        );

   // return null;
    //}
}}