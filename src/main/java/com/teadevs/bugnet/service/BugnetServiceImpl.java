package com.teadevs.bugnet.service;

import com.teadevs.bugnet.model.Bug;
import com.teadevs.bugnet.model.BugComment;
import com.teadevs.bugnet.repository.BugnetLocalRepository;
import com.teadevs.bugnet.repository.BugnetRepository;
import com.teadevs.bugnet.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        try {
            long id = bugdataGenerator.generateId();
            bug.setId(id);
            String fileLocation = bugdataGenerator.generateFileLocation(bug);
            if (fileLocation == null) {
                throw new Exception("Invalid file location");
            }
            bug.setBugFileLocation(fileLocation);
            /*
            1. Bug details are stored in db.
            2. Bug comments are stored in files.
             */
            bugnetLocalRepository.saveComments(bug);
            bugnetRepository.save(bug);
            logger.info("Successfully create bug {}", bug.getId());
        } catch (Exception e) {
            logger.warn("Failed to create bug {0}", e);
//            throw new DatabaseException(e);
        }
    }

    @Override
    public void updateBug(Bug bug) {
        System.out.println(bug);
    }

    @Override
    public List<Bug> getBugs() {
        List<Bug> bugs = bugnetRepository.findAll();
        for (Bug bug : bugs) {
            bug.setBugComments(getComments(bug.getBugFileLocation()));
        }
        return bugs;
    }

    @Override
    public List<BugComment> getComments(String filePath) {
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
            return Arrays.asList(jsonUtils.readJson(filePath, BugComment[].class));
        } catch (Exception e) {
        }
        return new ArrayList<>();
    }
}
