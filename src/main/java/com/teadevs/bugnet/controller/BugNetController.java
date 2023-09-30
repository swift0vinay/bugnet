package com.teadevs.bugnet.controller;

import com.teadevs.bugnet.exceptions.DatabaseException;
import com.teadevs.bugnet.model.bug.Bug;
import com.teadevs.bugnet.service.BugnetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class BugNetController {
    
    @Autowired
    BugnetService bugNetService;
    
    @PostMapping("/api/v1/bug/create")
    public void createBug(@RequestBody Bug bug) {
        bugNetService.createBug(bug);
    }
    
    
    @GetMapping("/api/v1/bug/all")
    public List<Bug> getAllBugs() {
        return bugNetService.getAllBugs();
    }
    
    @GetMapping("/api/v1/bug/user/{id}")
    public List<Bug> getBugsByUserId(@PathVariable String id) {
        return bugNetService.getBugsByUserId(id);
    }
    
    
    @PostMapping("/api/v1/bug/update")
    public void updateBug(@RequestBody Bug bug) {
        bugNetService.updateBug(bug);
    }
    
}
