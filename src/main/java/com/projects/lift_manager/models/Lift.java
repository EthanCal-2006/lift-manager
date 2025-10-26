package com.projects.lift_manager.models;

import java.io.Serial;
import java.io.Serializable;

public class Lift implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
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

    private static final double EPLEY_CONSTANT_A = 1.0278;
    private static final double EPLEY_CONSTANT_B = 0.0278;

    public void calculateOneRM() {
        if (reps <= 0 || repsWeight <= 0) {
            this.oneRM = 0;
        } else {
            this.oneRM = Math.round(repsWeight / (EPLEY_CONSTANT_A - EPLEY_CONSTANT_B * reps));
        }
    }



    public double getOneRM() { return oneRM; }
    public int getReps() { return reps; }
    public double getRepsWeight() { return repsWeight; }
    public String getExerciseName() { return exerciseName; }

    public void setReps(int reps) {
        this.reps = reps;
        calculateOneRM();
    }

    public void setRepsWeight(double repsWeight) {
        this.repsWeight = repsWeight;
        calculateOneRM();
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
    @Override
    public String toString() {
        return String.format("%s - %.2f lbs x %d reps (1RM: %.2f kg)",
                exerciseName, repsWeight, reps, oneRM);
    }


}