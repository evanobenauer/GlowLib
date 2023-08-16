package com.ejo.glowlib.math;

/**
 * The vector class is a container class for coordinates in Cartesian, Cylindrical, and Polar, including their 2D
 * variants. The class contains useful methods for calculating new vectors
 */
public class Vector {

    public static final Vector I = new Vector(1,0,0);
    public static final Vector J = new Vector(0,1,0);
    public static final Vector K = new Vector(0,0,1);
    public static final Vector NULL = new Vector(0,0,0);

    protected double x, y, z;

    /**
     * A vector without inputted parameters will default to the NULL vector <0,0,0>
     */
    public Vector() {
        this(0,0,0);
    }

    /**
     * Cartesian Coordinates
     * 3D
     */
    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Cartesian Coordinates
     * 2D
     */
    public Vector(double x, double y) {
        this(x,y,0);
    }


    /**
     * Cylindrical Coordinates
     * 3D
     */
    public Vector(double radius, Angle theta, double z) {
        this.x = radius * Math.cos(theta.getRadians());
        this.y = radius * Math.sin(theta.getRadians());
        this.z = z;
    }

    /**
     * Polar Coordinates
     * 2D
     */
    public Vector(double radius, Angle angle) {
        this(radius,angle,0);
    }


    /**
     * Spherical Coordinates
     * 3D
     */
    public Vector(double radiusRho, Angle theta, Angle phi) {
        this.x = radiusRho * Math.cos(theta.getRadians() * Math.sin(phi.getRadians()));
        this.y = radiusRho * Math.sin(theta.getRadians() * Math.sin(phi.getRadians()));
        this.z = radiusRho * Math.cos(phi.getRadians());
    }


    public Vector set(Vector vector) {
        this.x = vector.getX();
        this.y = vector.getY();
        this.z = vector.getZ();
        return this;
    }

    public Vector setCartesian(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector setCartesian(double x, double y) {
        return setCartesian(x,y,0);
    }

    public Vector setCylindrical(double radius, Angle theta, double z) {
        this.x = radius * Math.cos(theta.getRadians());
        this.y = radius * Math.sin(theta.getRadians());
        this.z = z;
        return this;
    }

    public Vector setPolar(double radius, Angle theta) {
        return setCylindrical(radius,theta,0);
    }

    public Vector setSpherical(double radiusRho, Angle theta, Angle phi) {
        this.x = radiusRho * Math.cos(theta.getRadians() * Math.sin(phi.getRadians()));
        this.y = radiusRho * Math.sin(theta.getRadians() * Math.sin(phi.getRadians()));
        this.z = radiusRho * Math.cos(phi.getRadians());
        return this;
    }

    public Vector add(Vector vec) {
        this.x += vec.getX();
        this.y += vec.getY();
        this.z += vec.getZ();
        return this;
    }

    public Vector multiply(double multiplier) {
        this.x *= multiplier;
        this.y *= multiplier;
        this.z *= multiplier;
        return this;
    }

    public Vector cross(Vector vec) {
        this.x = getY() * vec.getZ() - getZ() * vec.getY();
        this.y = -getX() * vec.getZ() + getZ() * vec.getX();
        this.z = getX() * vec.getY() - getY() * vec.getX();
        return this;
    }


    public Vector getAdded(Vector vec) {
        return new Vector(getX() + vec.getX(), getY() + vec.getY(), getZ() + vec.getZ());
    }

    public Vector getAdded(double x, double y, double z) {
        return getAdded(new Vector(x,y,z));
    }

    public Vector getAdded(double x, double y) {
        return getAdded(x,y,0);
    }

    public Vector getMultiplied(double multiplier) {
        return new Vector(getX() * multiplier,getY() * multiplier, getZ() * multiplier);
    }

    public Vector getCross(Vector vec) {
        return new Vector(
                getY() * vec.getZ() - getZ() * vec.getY(),
                -getX() * vec.getZ() + getZ() * vec.getX(),
                getX() * vec.getY() - getY() * vec.getX());
    }

    public double getDot(Vector vec) {
        return getX() * vec.getX() + getY() * vec.getY() + getZ() * vec.getZ();
    }


    public double getMagnitude() {
        return Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
    }

    public Vector getUnitVector() {
        return getMultiplied(1/getMagnitude());
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }


    public double getRadius() {
        return Math.sqrt(getX() * getX() + getY() * getY());
    }

    public Angle getTheta() {
        return new Angle(Math.atan(getY()/getX()));
    }


    public double getRhoRadius() {
        return getMagnitude();
    }

    public Angle getPhi() {
        return new Angle(Math.acos(getZ()/getMagnitude()));
    }


    @Override
    public String toString() {
        return "<" + getX() + "|" + getY() + "|" + getZ() + ">";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector newVec)) return false;
        try {
            return getX() == newVec.getX() && getY() == newVec.getY() && getZ() == newVec.getZ();
        } catch (Exception e) {
            return false;
        }
    }

}