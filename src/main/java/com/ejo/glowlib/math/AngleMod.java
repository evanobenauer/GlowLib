package com.ejo.glowlib.math;

/**
 * The angle class is a simple container for an angle in radians with useful conversions and functions
 * to make mathematics with angles much simpler
 */
public class AngleMod extends Angle {


    public AngleMod(double angle, boolean isDegrees) {
        super(angle,isDegrees);
    }

    /**
     * @param angle the angle given strictly in RADIANS
     */
    public AngleMod(double angle) {
        this(angle,false);
    }

    public AngleMod() {
        this(0,false);//No parameters, make the angle zero
    }


    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setAngle(double angle,boolean isDegrees) {
        this.angle = isDegrees ? angle * 180 / PI : angle;
    }
}