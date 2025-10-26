package com.projects.lift_manager.controllers;

import com.projects.lift_manager.models.Lift;
import com.projects.lift_manager.services.LiftManager;
import com.projects.lift_manager.services.SplitManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

    private final LiftManager liftManager;
    private final SplitManager splitManager;
    @Value("${lift.file.path}")
    private String filePath;
    // saves in app root

    public AppController(LiftManager liftManager, SplitManager splitManager) {
        this.liftManager = liftManager;
        this.splitManager = splitManager;
    }
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/lifts")
    public String showLifts(Model model) {
        model.addAttribute("lifts", liftManager.getLifts());
        return "lifts";
    }

    @PostMapping("/lifts/add")
    public String addLift(@RequestParam String name, @RequestParam double weight, @RequestParam int reps) {
        Lift newLift = new Lift(reps, weight, name);
        liftManager.addLift(newLift);
        return "redirect:/lifts";
    }

    @PostMapping("/lifts/remove")
    public String removeLift(@RequestParam int index) {
        // subtract 1 to convert 1-based user input to 0-based list index
        liftManager.removeLift(index); // index is 1-based
        return "redirect:/lifts";
    }

    @PostMapping("/lifts/save")
    public String saveLifts() {
        liftManager.saveLifts();
        return "redirect:/lifts";
    }

    @PostMapping("/lifts/load")
    public String loadLifts() {
        liftManager.loadLifts();
        return "redirect:/lifts";
    }

    @GetMapping("/splits")
    public String showSplits(Model model) {
        splitManager.createSplitList(liftManager.getLifts());
        model.addAttribute("splits", splitManager.getSplitList());
        return "splits";
    }
}
