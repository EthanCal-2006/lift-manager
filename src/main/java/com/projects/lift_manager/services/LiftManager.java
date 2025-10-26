package com.projects.lift_manager.services;

import com.projects.lift_manager.models.Lift;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class LiftManager {
    private List<Lift> liftList = new ArrayList<>();

    public List<Lift> getLiftList() {
        return liftList;
    }

    public void addNewLift(Lift lift) {
        liftList.add(lift);
    }

    public void removeLift(int index) {
        if (index > 0 && index <= liftList.size())
            liftList.remove(index - 1);
    }

    public void clearList() {
        liftList.clear();
    }

    // ----------- File Save/Load Methods -----------

    public void saveToFile(String filePath) {
        if (filePath == null || filePath.isEmpty()) return;

        Path path = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (Lift lift : liftList) {
                writer.write("-=-=-=-=-=-=-=-=-=-=-=-=-");
                writer.newLine();
                writer.write("Exercise name = " + lift.getExerciseName());
                writer.newLine();
                writer.write("One rep max = " + lift.getOneRM());
                writer.newLine();
                writer.write("Entered Reps = " + lift.getReps());
                writer.newLine();
                writer.write("Entered Reps weight = " + lift.getRepsWeight());
                writer.newLine();
                writer.write("-=-=-=-=-=-=-=-=-=-=-=-=-");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String filePath) {
        if (filePath == null || filePath.isEmpty()) return;

        List<Lift> loadedLifts = new ArrayList<>();
        StringBuilder block = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.equals("-=-=-=-=-=-=-=-=-=-=-=-=-")) {
                    if (block.length() > 0) {
                        appendLiftFromBlock(block.toString(), loadedLifts);
                        block.setLength(0);
                    }
                } else {
                    block.append(line).append("\n");
                }
            }
            if (block.length() > 0) appendLiftFromBlock(block.toString(), loadedLifts);
        } catch (IOException e) {
            e.printStackTrace();
        }

        liftList.clear();
        liftList.addAll(loadedLifts);
    }

    private void appendLiftFromBlock(String block, List<Lift> lifts) {
        String name = "";
        double oneRepMax = 0.0;
        int reps = 0;
        double repsWeight = 0.0;

        String[] lines = block.split("\n");
        for (String line : lines) {
            if (line.startsWith("Exercise name =")) {
                name = line.split("= ")[1].trim();
            } else if (line.startsWith("One rep max =")) {
                oneRepMax = Double.parseDouble(line.split("= ")[1].trim());
            } else if (line.startsWith("Entered Reps =")) {
                reps = Integer.parseInt(line.split("= ")[1].trim());
            } else if (line.startsWith("Entered Reps weight =")) {
                repsWeight = Double.parseDouble(line.split("= ")[1].trim());
            }
        }

        Lift lift = new Lift(reps, repsWeight, name);
        lifts.add(lift);
    }
}
