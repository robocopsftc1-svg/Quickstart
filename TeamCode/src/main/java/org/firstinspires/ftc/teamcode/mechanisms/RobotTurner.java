//
//package org.firstinspires.ftc.teamcode.mechanisms;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//
//public class RobotTurner {
//
//    private DcMotor frontLeft, backLeft, frontRight, backRight;
//    private LinearOpMode opMode;
//
//    public RobotTurner(
//            DcMotor fl, DcMotor bl,
//            DcMotor fr, DcMotor br,
//            LinearOpMode opMode) {
//
//        frontLeft = fl;
//        backLeft = bl;
//        frontRight = fr;
//        backRight = br;
//        this.opMode = opMode;
//    }
//
//    public void turnToBucket(Turret turret, boolean bucket) {
//
//        double tolerance = 2.0; // degrees
//        double minPower = 0.12;
//        double maxPower = 0.35;
//
//        while (opMode.opModeIsActive()) {
//
//            // Recalculate the remaining angle each loop
//            double error = turret.getAngle(bucket);
//
//            // Normalize to the shortest turn: -180 to +180
//            error = ((error + 180) % 360 + 360) % 360 - 180;
//
//            if (Math.abs(error) <= tolerance) {
//                break;
//            }
//
//            // Slow down as we approach the target
//            double power = Math.min(
//                    maxPower,
//                    Math.max(minPower, Math.abs(error) * 0.01)
//            );
//
//            // Turn in the direction of the remaining angle
//            power *= Math.signum(error);
//
//            // Rotaanum wheels
//            frontLeft.setPower(power);
//            backLeft.setPower(power);
//            frontRight.setPower(-power);
//            backRight.setPower(-power);
//
//            opMode.idle();
//        }
//
//        // Always stop after finishing or leaving the OpMode
//        frontLeft.setPower(0);
//        backLeft.setPower(0);
//        frontRight.setPower(0);
//        backRight.setPower(0);
//    }
//}
