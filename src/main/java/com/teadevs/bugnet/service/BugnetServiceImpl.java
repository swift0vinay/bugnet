package com.teadevs.bugnet.service;

import com.teadevs.bugnet.exceptions.BugnetException;
import com.teadevs.bugnet.exceptions.DatabaseException;
import com.teadevs.bugnet.model.bug.*;
import com.teadevs.bugnet.repository.BugnetLocalRepository;
import com.teadevs.bugnet.repository.BugnetRepository;
import com.teadevs.bugnet.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Component
public class BugnetServiceImpl implements BugnetService {
    
    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    
    @Autowired
    BugnetRepository bugnetRepository;
    
    @Autowired
    BugnetLocalRepository bugnetLocalRepository;
    
    @Autowired
    BugdataGenerator bugdataGenerator;
    
    @Autowired
    JsonUtils jsonUtils;
    
    @Override
    public void createBug(Bug bug) {
        bugdataGenerator.generateId(bug);
        bugdataGenerator.generateFileLocation(bug);
        bug.setBugStatus(BugStatus.NEW);
        bug.validate();
        if (bug.getRegression() == null) {
            bug.setRegression(RegressionStatus.UNKNOWN);
        }
        
        if (bug.getSeverity() == null) {
            bug.setSeverity(BugSeverity.UNKNOWN);
        }
            /*
            1. Bug details are stored in db.
            2. Bug comments are stored in files.
             */
        bugnetLocalRepository.saveComments(bug);
        bugnetRepository.save(bug);
        logger.info("Successfully create bug {}", bug.getId());
    }
    
    @Override
    public ArrayList<BugUpdate> getComments(String filePath) {
        try {
            if (filePath == null) {
                logger.warn("Invalid file path");
                throw new FileNotFoundException("Cannot find file");
            }
            File file = new File(filePath);
            if (!file.exists()) {
                logger.warn("Cannot find file {}", filePath);
                throw new FileNotFoundException("Cannot find file");
            }
            return new ArrayList<>(Arrays.asList(jsonUtils.readJson(filePath, BugUpdate[].class)));
        } catch (FileNotFoundException e) {
            throw new DatabaseException("Cannot find file: " + filePath);
        }
    }
    
    @Override
    public List<Bug> getAllBugs() {
        List<Bug> bugs = bugnetRepository.findAll();
        for (Bug bug : bugs) {
            bug.setBugUpdates(getComments(bug.getBugFileLocation()));
        }
        return bugs;
    }
    
    @Override
    public List<Bug> getBugsByUserId(String id) {
        List<Bug> bugs = bugnetRepository.findByAssignedToEquals(id);
        for (Bug bug : bugs) {
            bug.setBugUpdates(getComments(bug.getBugFileLocation()));
        }
        return bugs;
    }
    
    @Override
    public Bug getBugById(long id) {
        Bug bug = bugnetRepository.findById(id).orElse(null);
        if (bug == null) {
            return null;
        }
        bug.setBugUpdates(getComments(bug.getBugFileLocation()));
        return bug;
    }
    
    @Override
    public void updateBug(Bug bug) {
        /*
        Check for differences.
        Keep track of those diffs
         */
        Bug original = getBugById(bug.getId());
        if (original == null) {
            logger.warn("Bug {} not found", bug.getId());
            throw new BugnetException("Bug not found with id: " + bug.getId());
        }
        bug.setBugFileLocation(original.getBugFileLocation());
        
        BugUpdate bugUpdate = new BugUpdate();
        if (!bug.getBugUpdates().isEmpty()) {
            BugUpdate req = bug.getBugUpdates().get(0);
            bugUpdate.setCommenter(req.getCommenter());
            bugUpdate.setCommentDate(req.getCommentDate());
            bugUpdate.setComment(req.getComment());
        }
        Map<String, List<String>> diff = bugdataGenerator.generateBugDiff(original, bug);
        
        bugUpdate.setChanges(diff);
        original.getBugUpdates().add(bugUpdate);
        
        bugnetLocalRepository.saveComments(original);
        bugnetRepository.save(original);
        
    }
    
}