package com.projects.lift_manager.services;

import com.projects.lift_manager.models.Lift;
import com.projects.lift_manager.models.Split;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SplitManager {
    private List<Split> splitList = new ArrayList<>();

    public void createSplitList(List<Lift> lifts) {
        splitList.clear();
        for (Lift lift : lifts)
            splitList.add(new Split(lift));
    }

    public List<Split> getSplitList() { return splitList; }
}