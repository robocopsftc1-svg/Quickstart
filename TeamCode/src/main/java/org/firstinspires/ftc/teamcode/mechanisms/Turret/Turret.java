package org.firstinspires.ftc.teamcode.mechanisms.Turret;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;

public class Turret
{
    private GoBildaPinpointDriver pinpoint;

    public Turret(GoBildaPinpointDriver pinpoint) {
        this.pinpoint = pinpoint;
    }
    double x =pinpoint.getPosX(DistanceUnit.METER);//+disance

    double y=pinpoint.getPosY(DistanceUnit.METER);
    double heading=pinpoint.getHeading(UnnormalizedAngleUnit.DEGREES);

    public double getAngle (boolean bucket)
    {
        double x2;
        double y2;
        if (bucket) {
            x2 = (1);//replace with actual number
            y2 = (1);//replace with actual number
        }
        else
        {
            x2 = (2);//replace with actual number
            y2 = (2);//replace with actual number
        }
        double dx = x2 - x;
        double dy = y2 - y;
        return Math.toDegrees(Math.atan2(dy, dx)) - heading;

    }
    public double getDistance(boolean bucket)//might need to alter to m instead of mm
    {
        double x2;
        double y2;
        if (bucket)
        {
            x2 = (1);//replace with actual number x pos 1
            y2 = (1);//replace with actual number y pos 1
        }
        else
        {
            x2 = (2);//replace with actual number x pos 2
            y2 = (2);//replace with actual number y pos 2
        }
        double dx = x2 - x;
        double dy = y2 - y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    public double getLaunchAngle(double v0, double dx, double dy)// replace v0 with a number soon?
    {
        double a = 9.81;

        double value = Math.pow(v0, 4) - a * (a * dx * dx + 2 * dy * v0 * v0);
        if (value < 0 || dx <= 0) {
            return 0;
        }

        double n = v0 * v0 + Math.sqrt(value);
        double d = a * dx;
        double angle = Math.atan(n / d);
        return Math.toDegrees(angle);
    }
    public void setAngle()
    {

    }

}