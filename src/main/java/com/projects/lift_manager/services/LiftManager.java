
package com.projects.lift_manager.services;

import com.projects.lift_manager.models.Lift;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LiftManager {
    private List<Lift> lifts = new ArrayList<>();
    private final String FILE_PATH = "lifts.ser";

    public LiftManager() {
        loadLifts();
    }

    public List<Lift> getLifts() {
        return lifts;
    }

    public void addLift(String name, double weight, int reps) {
        lifts.add(new Lift(reps, weight, name));
    }

    public void addLift(Lift lift) {lifts.add(lift);}

    public void removeLift(int userIndex) {
        int idx = userIndex - 1; // convert to 0-based
        if (idx >= 0 && idx < lifts.size()) {
            lifts.remove(idx);
        }
    }

    public void saveLifts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(lifts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadLifts() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                lifts = (List<Lift>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                lifts = new ArrayList<>();
            }
        }
    }
}