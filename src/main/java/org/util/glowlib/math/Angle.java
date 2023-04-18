package org.util.glowlib.math;

/**
 * The angle class is a simple container for an angle in radians with useful conversions and functions
 * to make mathematics with angles much simpler
 */
public class Angle {


    public static final double PI = MathE.PI;

    /**
     * Angle value given in Radians
     */
    private double angle;


    public Angle(double angle, boolean isDegrees) {
        if (isDegrees) this.angle = angle * 180 / PI;
        else this.angle = angle;
    }

    /**
     * @param angle the angle given strictly in RADIANS
     */
    public Angle(double angle) {
        this(angle,false);
    }

    public Angle() {
        this(0,false);//No parameters, make the angle zero
    }


    //TODO: IDK if i want to keep these. It may be unnecessary to modify the angle class after creation similar to vector
    // For now they will be set to private so they are not used:
    @Deprecated
    private void setAngleRadians(double angle) {
        this.angle = angle;
    }
    @Deprecated
    private void setAngleDegrees(double angle) {
        this.angle = angle * 180 / PI;
    }


    public double getRadians() {
        return angle;
    }

    public double getDegrees() {
        return getRadians() * 180 / PI;
    }


    public Vector getDirectionVector() {
        return getDirectionVector(false);
    }

    /**
     * Returns a unit vector pointing in the direction that the angle tells it. It points on the plane that the angle
     * lies, which would be specified by the user during calculations
     * @param shouldRound
     * @return
     */
    public Vector getDirectionVector(boolean shouldRound) {
        double x = shouldRound ? MathE.roundDouble(Math.cos(getRadians()),6) : Math.cos(getRadians());
        double y = shouldRound ? MathE.roundDouble(Math.sin(getRadians()),6) : Math.sin(getRadians());
        return new Vector(x,y);
    }


    @Override
    public String toString() {
        return "[Angle: " + getRadians() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Angle angle)) return false;
        return angle.getRadians() == getRadians();
    }
}