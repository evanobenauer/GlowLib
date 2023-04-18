package org.util.glowlib.math;

/**
 * This is a modifiable version of the vector class. Variables can be changed and set for unique scenarios
 * if it is necessary within the program. It is good to have this option for potentially calculating positions
 * outside of loops to save on memory in order to not create a new vector every loop
 */
public class VectorMod extends Vector {

    /**
     * Cartesian Coordinates
     * 3D
     */
    public VectorMod(double x, double y, double z) {
        super(x,y,z);
    }

    /**
     * Cartesian Coordinates
     * 2D
     */
    public VectorMod(double x, double y) {
        this(x,y,0);
    }

    /**
     * Cylindrical Coordinates
     * 3D
     */
    public VectorMod(double radius, Angle theta, double z) {
        super(radius,theta,z);
    }

    /**
     * Polar Coordinates
     * 2D
     */
    public VectorMod(double radius, Angle angle) {
        this(radius,angle,0);
    }

    /**
     * Spherical Coordinates
     * 3D
     */
    public VectorMod(double radiusRho, Angle theta, Angle phi) {
        super(radiusRho,theta,phi);
    }

    /**
     * Create a VectorMod from a normal vector
     * @param vector
     */
    public VectorMod(Vector vector) {
        this(vector.getX(),vector.getY(),vector.getZ());
    }


    public VectorMod set(Vector vector) {
        this.x = vector.getX();
        this.y = vector.getY();
        this.z = vector.getZ();
        return this;
    }

    public VectorMod add(Vector vec) {
        this.x += vec.getX();
        this.y += vec.getY();
        this.z += vec.getZ();
        return this;
    }

    public VectorMod multiply(double multiplier) {
        this.x *= multiplier;
        this.y *= multiplier;
        this.z *= multiplier;
        return this;
    }

    public VectorMod cross(Vector vec) {
        this.x = getY() * vec.getZ() - getZ() * vec.getY();
        this.y = -getX() * vec.getZ() + getZ() * vec.getX();
        this.z = getX() * vec.getY() - getY() * vec.getX();
        return this;
    }


    public VectorMod setCartesian(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.theta = new Angle(Math.atan(y/x));
        this.phi = new Angle(Math.acos(z/getMagnitude()));
        return this;
    }

    public VectorMod setCartesian(double x, double y) {
        return setCartesian(x,y,0);
    }

    public VectorMod setCylindrical(double radius, Angle theta, double z) {
        this.x = radius * Math.cos(theta.getRadians());
        this.y = radius * Math.sin(theta.getRadians());
        this.z = z;

        this.theta = theta;
        this.phi = new Angle(Math.acos(z/getMagnitude()));
        return this;
    }

    public VectorMod setSpherical(double radiusRho, Angle theta, Angle phi) {
        this.x = radiusRho * Math.cos(theta.getRadians() * Math.sin(phi.getRadians()));
        this.y = radiusRho * Math.sin(theta.getRadians() * Math.sin(phi.getRadians()));
        this.z = radiusRho * Math.cos(phi.getRadians());

        this.theta = theta;
        this.phi = phi;
        return this;
    }

}