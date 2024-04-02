package com.phoenix.simulationengine.util;

public class Vector {

    public double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void sub(Vector v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void mult(double value) {
        this.x *= value;
        this.y *= value;
    }

    public void div(double value) {
        if (value != 0) {
            this.x /= value;
            this.y /= value;

        } else {
            this.x = 0;
            this.x = 0;
        }
    }

    public double mag() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }

    public void normalize() {
        double m = mag();
        if (m != 0) {
            this.x /= m;
            this.y /= m;
        }

    }

    public double heading() {
        return Math.atan2(this.y, this.x);
    }

    public void setMag(double len) {
        normalize();
        mult(len);
    }

    public Vector copy() {
        return new Vector(this.x, this.y);
    }

    public static double dist(Vector v1, Vector v2) {
        double dx = v1.x - v2.x;
        double dy = v1.y - v2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double angleBetween(Vector v1, Vector v2) {
        return Math.acos(v1.dot(v2) / (v1.mag() * v2.mag()));
    }

    public double dot(Vector v) {
        return this.x * v.x + this.y * v.y;
    }

    public static Vector fromAngle(double angle) {
        return new Vector(Math.cos(angle), Math.sin(angle));
    }

    public static Vector add(Vector v1, Vector v2) {
        return new Vector(v1.x + v2.x, v1.y + v2.y);
    }

    public static Vector sub(Vector v1, Vector v2) {
        return new Vector(v1.x - v2.x, v1.y - v2.y);
    }

    public void limit(double max) {
        double mSq = x * x + y * y;
        if (mSq > max * max) {
            double m = Math.sqrt(mSq);
            x = x / m * max;
            y = y / m * max;
        }
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

}
