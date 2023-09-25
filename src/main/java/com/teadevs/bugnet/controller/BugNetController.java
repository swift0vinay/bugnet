package com.teadevs.bugnet.controller;

import com.teadevs.bugnet.model.bug.Bug;
import com.teadevs.bugnet.service.BugnetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/api/v1/bug/all")
    public List<Bug> getAllBugs() {
        try {
            return bugNetService.getAllBugs();
        } catch (Exception e) {
            System.out.println("Failed to get bug");
            throw new RuntimeException("Failed to get bug");
        }
    }

    @GetMapping("/api/v1/bug/user/{id}")
    public List<Bug> getBugsByUserId(@PathVariable String id) {
        try {
            return bugNetService.getBugsByUserId(id);
        } catch (Exception e) {
            System.out.println("Failed to get bug");
            throw new RuntimeException("Failed to get bug");
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

}
