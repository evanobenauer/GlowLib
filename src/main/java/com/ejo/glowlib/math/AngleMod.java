package com.ejo.glowlib.math;

/**
 * The angle class is a simple container for an angle in radians with useful conversions and functions
 * to make mathematics with angles much simpler
 */
public class AngleMod extends Angle {


    public AngleMod(double angle, boolean isDegrees) {
        super(angle, isDegrees);
    }

    /**
     * @param angle the angle given strictly in RADIANS
     */
    public AngleMod(double angle) {
        this(angle, false);
    }

    public AngleMod() {
        this(0, false);//No parameters, make the angle zero
    }


    public AngleMod add(Angle angle) {
        this.angle += angle.getRadians();
        return this;
    }

    public AngleMod add(double angle, boolean isDegrees) {
        this.angle += new Angle(angle,isDegrees).getRadians();
        return this;
    }

    public AngleMod subtract(Angle angle) {
        this.angle -= angle.getRadians();
        return this;
    }

    public AngleMod subtract(double angle, boolean isDegrees) {
        this.angle -= new Angle(angle,isDegrees).getRadians();
        return this;
    }

    public AngleMod multiply(double mul) {
        this.angle *= mul;
        return this;
    }

    /**
     * Simplifies the angle to be between 0 and 2PI
     * @return
     */
    public AngleMod simplify() {
        while (this.angle > Math.PI * 2) {
            this.angle -= Math.PI * 2;
        }
        while (this.angle < 0) {
            this.angle += Math.PI * 2;
        }
        return this;
    }

    public AngleMod setAngle(double angle) {
        this.angle = angle;
        return this;
    }

    public AngleMod setAngle(double angle, boolean isDegrees) {
        this.angle = isDegrees ? angle * 180 / PI : angle;
        return this;
    }
}