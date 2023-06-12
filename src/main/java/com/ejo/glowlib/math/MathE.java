package com.ejo.glowlib.math;

import com.ejo.glowlib.util.StringUtil;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO: This class is still very much a work in progress. It is not professional or organized. Fix that
/**
 * The mathematics class is a class with many functions for various different calculations that may be needed
 * while programming
 */
public class MathE {

    public static final double PI = Math.PI;

    public static boolean isEven(int num) {
        return (num % 2 == 0);
    }

    public static double roundDouble(double number, int sigFigs) {
        String num = String.format("%." + sigFigs + "f",number);
        return Double.parseDouble(num);
    }

    public static double calculateAverage(ArrayList<Double> values) {
        double avg = 0;
        for (double val : values) {
            avg += val;
        }
        avg /= values.size();
        return avg;
    }

    /**
     * Quadratic Formula
     */
    public static ArrayList<String> calculateQuadraticFormula(double a, double b, double c) {
        ArrayList<String> values = new ArrayList<>();
        double sqrt = java.lang.Math.sqrt((java.lang.Math.pow(b, 2) - 4 * a * c));
        String x1 = (-b + sqrt) / (2 * a) + "";
        String x2 = (-b - sqrt) / (2 * a) + "";

        if (Double.toString(sqrt).equalsIgnoreCase("NaN")) {
            sqrt = java.lang.Math.sqrt(-(java.lang.Math.pow(b, 2) - 4 * a * c));
            x1 = -b / (2 * a) + " + " + sqrt / (2 * a) + "i";
            x2 = -b / (2 * a) + " - " + sqrt / (2 * a) + "i";
        }
        values.add(x1);
        values.add(x2);
        return values;
    }

    public static ArrayList<String> calculateQuadraticFormula(double a, double b, double c, int sigfigs) {
        ArrayList<String> xs = calculateQuadraticFormula(a, b, c);
        String format = "%." + sigfigs + "f";
        if (StringUtil.isStringDouble(xs.get(0)) && StringUtil.isStringDouble(xs.get(1))) {
            xs.set(0, String.format(format, Double.parseDouble(xs.get(0))));
            xs.set(1, String.format(format, Double.parseDouble(xs.get(1))));
        } else {
            for (int i = 0; i < xs.size(); i++) {
                Pattern pattern = Pattern.compile("(\\-?\\d+\\.?\\d*)\\s*[\\+\\-]\\s*(\\d+\\.?\\d*)i");
                Matcher matcher = pattern.matcher(xs.get(i));
                matcher.find();
                double real;
                double imaginary;
                try {
                    real = Double.parseDouble(matcher.group(1));
                    imaginary = Double.parseDouble(matcher.group(2));
                } catch (Exception e) {
                    System.out.println("String pattern failed to recognize number");
                    break;
                }
                String sign = i == 0 ? "+" : "-";
                xs.set(i, String.format(format, real) + " " + sign + " " + String.format(format, imaginary) + "i");
            }
        }
        return xs;
    }


    /**
     * Third degree polynomial solver
     */
    public static double[] solveCubicPolynomial(double a, double b, double c, double d) {
        double[] roots = new double[3];

        double f = ((3 * c / a) - (b * b / (a * a))) / 3.0;
        double g = (((2 * Math.pow(b, 3)) / (Math.pow(a, 3))) - ((9 * b * c) / (Math.pow(a, 2))) + ((27 * d) / a)) / 27.0;
        double h = (Math.pow(g, 2) / 4.0) + (Math.pow(f, 3) / 27.0);

        if (h > 0) {
            double r = -(g / 2.0) + Math.sqrt(h);
            double s = Math.cbrt(r);
            double t = -(g / 2.0) - Math.sqrt(h);
            double u = Math.cbrt(t);

            roots[0] = s + u - (b / (3.0 * a));
            return roots;
        } else if (h == 0) {
            double r = -(g / 2.0);
            double s = Math.cbrt(r);

            roots[0] = (2.0 * s) - (b / (3.0 * a));
            roots[1] = -s - (b / (3.0 * a));
            return roots;
        } else {
            double i = Math.sqrt(-(Math.pow(f, 3) / 27.0));
            double j = Math.acos(-(g / (2.0 * i)));
            double k = Math.cbrt(2.0 * i);

            roots[0] = (k * Math.cos(j / 3.0)) - (b / (3.0 * a));
            roots[1] = (k * Math.cos((j + 2.0 * Math.PI) / 3.0)) - (b / (3.0 * a));
            roots[2] = (k * Math.cos((j - 2.0 * Math.PI) / 3.0)) - (b / (3.0 * a));
            return roots;
        }
    }
    
    /**
     * Fourth degree polynomial solver
     */
    public static double[] solveFourthDegreePolynomial(double a, double b, double c, double d, double e) {
        double[] roots = new double[4];

        // Step 1: Convert to a depressed quartic
        double p = (8 * a * c - 3 * Math.pow(b, 2)) / (8 * Math.pow(a, 2));
        double q = (Math.pow(b, 3) - 4 * a * b * c + 8 * Math.pow(a, 2) * d) / (8 * Math.pow(a, 3));
        double r = (-3 * Math.pow(b, 4) + 256 * Math.pow(a, 3) * e - 64 * a * b * d + 16 * Math.pow(a, 2) * c) / (256 * Math.pow(a, 4));

        // Step 2: Solve the resolvent cubic
        double[] resolventCoeffs = {1.0, -2 * p, p * p - 4 * r, -q * q};
        double[] resolventRoots = solveCubicPolynomial(resolventCoeffs[0], resolventCoeffs[1], resolventCoeffs[2], resolventCoeffs[3]);

        // Step 3: Find the roots of the quartic
        double y = resolventRoots[0];
        double discriminant = p * p / 4 - r - y;
        double s = Math.sqrt(discriminant);
        double t = -p / 2;

        double u = t + s;
        double v = t - s;

        double w = Math.sqrt(Math.abs(2 * y - p + 2 * Math.sqrt(discriminant)));
        double x = Math.sqrt(Math.abs(2 * y - p - 2 * Math.sqrt(discriminant)));

        roots[0] = (-b - (u + w) / 2) / (4 * a);
        roots[1] = (-b - (u - w) / 2) / (4 * a);
        roots[2] = (-b - (v + x) / 2) / (4 * a);
        roots[3] = (-b - (v - x) / 2) / (4 * a);

        return roots;
    }


    /**
     * Vector Calculations
     */
    public static Vector calculateCrossProduct(Vector a, Vector b) {
        return new Vector(
                a.getY() * b.getZ() - a.getZ() * b.getY(),
                -a.getX() * b.getZ() + a.getZ() * b.getX(),
                a.getX() * b.getY() - a.getY() * b.getX());
    }

    public static double calculateDotProduct(Vector a, Vector b) {
        return a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ();
    }

}
