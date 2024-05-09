package com.ejo.glowlib.math;

public class MultiVector {

    protected final double[] values;

    public MultiVector(double... values) {
        this.values = values;
    }

    public MultiVector getAdded(MultiVector vec) {
        if (values.length != vec.values.length) return null;
        double[] vals = new double[values.length];
        for (int i = 0; i < values.length; i++) vals[i] = values[i] + vec.values[i];
        return new MultiVector(vals);
    }

    public MultiVector getSubtracted(MultiVector vec) {
        if (values.length != vec.values.length) return null;
        double[] vals = new double[values.length];
        for (int i = 0; i < values.length; i++) vals[i] = values[i] - vec.values[i];
        return new MultiVector(vals);
    }

    public MultiVector getScaled(MultiVector vec) {
        if (values.length != vec.values.length) return null;
        double[] vals = new double[values.length];
        for (int i = 0; i < values.length; i++) vals[i] = values[i] * vec.values[i];
        return new MultiVector(vals);
    }

    public MultiVector getMultiplied(double multiplier) {
        double[] vals = new double[values.length];
        for (int i = 0; i < values.length; i++) vals[i] = values[i] * multiplier;
        return new MultiVector(vals);
    }


    public double getDot(MultiVector vec) {
        if (values.length != vec.values.length) return -1;
        double sum = 0;
        for (int i = 0; i < values.length; i++) sum += values[i] * vec.values[i];
        return sum;
    }

    public MultiVector getProjection(MultiVector onto) {
        return onto.getMultiplied(getDot(onto) / Math.pow(onto.getMagnitude(),2));
    }

    public double getMagnitude() {
        double sum = 0;
        for (int i = 0; i < values.length; i++) sum += values[i] * values[i];
        return Math.sqrt(sum);
    }

    public MultiVector getUnitVector() {
        return getMultiplied(1 / getMagnitude());
    }

    public double[] getValues() {
        return values;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MultiVector newVec)) return false;
        try {
            for (int i = 0; i < values.length; i++) {
                if (values[i] != newVec.values[i]) return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        String result = "<";
        for (int i = 0; i < values.length; i++) result += values[i] + "|";
        return result + ">";
    }


}
