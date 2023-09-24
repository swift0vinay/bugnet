package com.teadevs.bugnet.controller;

import com.teadevs.bugnet.model.Bug;
import com.teadevs.bugnet.service.BugnetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BugNetController {

    @Autowired
    BugnetService bugNetService;

    @PostMapping("/api/v1/bug/create")
    public void createBug(@RequestBody Bug bug) {
        try {
            bugNetService.createBug(bug);
        } catch (Exception e) {
            System.out.println("Failed to add bug");
            throw new RuntimeException("Failed to add bug");
        }
    }

    @PostMapping("/api/v1/bug/update")
    public void updateBug(@RequestBody Bug bug) {
        try {
            bugNetService.updateBug(bug);
        } catch (Exception e) {
            System.out.println("Failed to add bug");
            throw new RuntimeException("Failed to add bug");
        }
    }


    @GetMapping("/api/v1/bug/all")
    public List<Bug> getBugs() {
        try {
            return bugNetService.getBugs();
        } catch (Exception e) {
            System.out.println("Failed to get bug");
            throw new RuntimeException("Failed to get bug");
        }
    }
}
