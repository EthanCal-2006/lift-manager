package com.projects.lift_manager.models;

public class Split {
    private Lift lift;
    private double eightyPercentMax;
    private double sixtyPercentMax;
    private double fortyPercentMax;

    public Split(Lift lift) {
        this.lift = lift;
        this.eightyPercentMax = roundToNearestFive(lift.getOneRM() * 0.8);
        this.sixtyPercentMax = roundToNearestFive(lift.getOneRM() * 0.6);
        this.fortyPercentMax = roundToNearestFive(lift.getOneRM() * 0.4);
    }

    private double roundToNearestFive(double val) {
        return val - (val % 5);
    }

    public Lift getLift() { return lift; }
    public double getEightyPercentMax() { return eightyPercentMax; }
    public double getSixtyPercentMax() { return sixtyPercentMax; }
    public double getFortyPercentMax() { return fortyPercentMax; }
}
