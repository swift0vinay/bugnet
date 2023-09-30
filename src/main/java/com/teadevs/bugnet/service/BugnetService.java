package com.teadevs.bugnet.service;

import com.teadevs.bugnet.model.bug.Bug;
import com.teadevs.bugnet.model.bug.BugUpdate;

import java.util.List;

public interface BugnetService {
    
    public void createBug(Bug bug);
    
    public List<BugUpdate> getComments(String fileLocation);
    
    public List<Bug> getAllBugs();
    
    public Bug getBugById(long id);
    
    public List<Bug> getBugsByUserId(String id);
    
    public void updateBug(Bug bug);
    
}
