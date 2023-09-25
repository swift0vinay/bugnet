package com.teadevs.bugnet.service;


import com.teadevs.bugnet.model.bug.Bug;
import com.teadevs.bugnet.model.bug.BugFields;
import com.teadevs.bugnet.repository.BugnetRepository;
import com.teadevs.bugnet.utils.BugnetConstants;
import com.teadevs.bugnet.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BugdataGeneratorImpl implements BugdataGenerator {
    
    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    
    @Autowired
    BugnetRepository bugnetRepository;
    
    @Autowired
    JsonUtils jsonUtils;
    
    @Override
    public void generateId(Bug bug) {
        long id = bugnetRepository.count() + 1;
        bug.setId(id);
    }
    
    @Override
    public void generateFileLocation(Bug bug) {
        String filePath = BugnetConstants.LOCAL_DATABASE_FOLDER + File.separator + bug.getId() + ".json";
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                boolean rs = file.getParentFile().mkdir();
                if (!rs) {
                    logger.warn("Failed to create dir {}", file.getParent());
                    throw new Exception("Failed to create dir");
                }
                logger.info("Directory {} created successfully", file.getParent());
            }
            bug.setBugFileLocation(filePath);
        } catch (Exception e) {
            logger.warn("Failed to create file: {} {0}", filePath, e);
        }
    }
    
    @Override
    public Map<String, List<String>> generateBugDiff(Bug original, Bug request) {
        Map<String, List<String>> diff = new HashMap<>();
        if (request.getSummary() != null && !StringUtils.pathEquals(original.getSummary(), request.getSummary())) {
            diff.put(BugFields.SUMMARY.name(), Arrays.asList(new String[]{original.getSummary(), request.getSummary()}));
            original.setSummary(request.getSummary());
        }
        if (request.getRegression() != null && !original.getRegression().equals(request.getRegression())) {
            diff.put(BugFields.REGRESSION.name(), Arrays.asList(new String[]{original.getRegression().name(), request.getRegression().name()}));
            original.setRegression(request.getRegression());
        }
        if (request.getBugStatus() != null && !original.getBugStatus().equals(request.getBugStatus())) {
            diff.put(BugFields.BUG_STATUS.name(), Arrays.asList(new String[]{original.getBugStatus().name(), request.getBugStatus().name()}));
            original.setBugStatus(request.getBugStatus());
        }
        if (request.getSeverity() != null && !original.getSeverity().equals(request.getSeverity())) {
            diff.put(BugFields.SEVERITY.name(), Arrays.asList(new String[]{original.getSeverity().name(), request.getSeverity().name()}));
            original.setSeverity(request.getSeverity());
        }
        if (request.getEta() != null && !original.getEta().equals(request.getEta())) {
            diff.put(BugFields.ETA.name(), Arrays.asList(new String[]{original.getEta().toString(), request.getEta().toString()}));
            original.setEta(request.getEta());
        }
        if (request.getAssignedTo() != null && !StringUtils.pathEquals(original.getAssignedTo(), request.getAssignedTo())) {
            diff.put(BugFields.ASSIGNED_TO.name(), Arrays.asList(new String[]{original.getAssignedTo(), request.getAssignedTo()}));
            original.setAssignedTo(request.getAssignedTo());
        }
        return diff;
    }
    
}
