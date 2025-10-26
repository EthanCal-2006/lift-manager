package com.projects.lift_manager.models;

public class Lift {
    private double oneRM;
    private int reps;
    private double repsWeight;
    private String exerciseName;

    public Lift(int reps, double repsWeight, String exerciseName) {
        this.reps = reps;
        this.repsWeight = repsWeight;
        this.exerciseName = exerciseName;
        calculateOneRM();
    }

    public Lift() {}

    public void calculateOneRM() {
        this.oneRM = Math.round(repsWeight / (1.0278 - 0.0278 * reps));
    }

    public double getOneRM() { return oneRM; }
    public int getReps() { return reps; }
    public double getRepsWeight() { return repsWeight; }
    public String getExerciseName() { return exerciseName; }
}
