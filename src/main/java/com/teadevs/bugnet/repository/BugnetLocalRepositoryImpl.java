package com.teadevs.bugnet.repository;

import com.teadevs.bugnet.exceptions.DatabaseException;
import com.teadevs.bugnet.model.bug.Bug;
import com.teadevs.bugnet.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Repository
public class BugnetLocalRepositoryImpl implements BugnetLocalRepository {
    
    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    
    @Autowired
    MongoTemplate mongoTemplate;
    
    @Autowired
    JsonUtils jsonUtils;
    
    @Override
    public void saveComments(Bug bug)  {
        try {
            File file = new File(bug.getBugFileLocation());
            if (file.exists()) {
                FileUtils.forceDelete(file);
            }
            String data = jsonUtils.toJson(bug.getBugUpdates());
            FileUtils.write(new File(bug.getBugFileLocation()), data, StandardCharsets.UTF_8);
            logger.info("Data saved successfully");
        }
        catch (IOException e) {
            logger.warn("Failed to write data {0}",e);
            throw new DatabaseException("Failed to write data");
        }
    }
    
}