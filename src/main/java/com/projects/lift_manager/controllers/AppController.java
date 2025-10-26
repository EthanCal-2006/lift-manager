package com.projects.lift_manager.controllers;

import com.projects.lift_manager.models.Lift;
import com.projects.lift_manager.services.LiftManager;
import com.projects.lift_manager.services.SplitManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

    private final LiftManager liftManager = new LiftManager();
    private final SplitManager splitManager = new SplitManager();
    private final String FILE_PATH = "Lifts.txt"; // saves in app root

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/lifts")
    public String showLifts(Model model) {
        model.addAttribute("lifts", liftManager.getLiftList());
        return "lifts";
    }

    @PostMapping("/lifts/add")
    public String addLift(@RequestParam String name, @RequestParam double weight, @RequestParam int reps) {
        Lift newLift = new Lift(reps, weight, name);
        liftManager.addNewLift(newLift);
        return "redirect:/lifts";
    }

    @PostMapping("/lifts/remove")
    public String removeLift(@RequestParam int index) {
        liftManager.removeLift(index);
        return "redirect:/lifts";
    }

    @PostMapping("/lifts/save")
    public String saveLifts() {
        liftManager.saveToFile(FILE_PATH);
        return "redirect:/lifts";
    }

    @PostMapping("/lifts/load")
    public String loadLifts() {
        liftManager.loadFromFile(FILE_PATH);
        return "redirect:/lifts";
    }

    @GetMapping("/splits")
    public String showSplits(Model model) {
        splitManager.createSplitList(liftManager.getLiftList());
        model.addAttribute("splits", splitManager.getSplitList());
        return "splits";
    }
}
