package com.teadevs.bugnet.service;


import com.teadevs.bugnet.model.Bug;
import com.teadevs.bugnet.repository.BugnetRepository;
import com.teadevs.bugnet.utils.BugnetConstants;
import com.teadevs.bugnet.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class BugdataGeneratorImpl implements BugdataGenerator {
    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    BugnetRepository bugnetRepository;

    @Autowired
    JsonUtils jsonUtils;

    @Override
    public long generateId() {
        return bugnetRepository.count() + 1;
    }

    @Override
    public String generateFileLocation(Bug bug) {
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
            boolean rs = file.createNewFile();
            if (!rs) {
                logger.warn("Failed to create file {}", file.getParent());
                throw new Exception("Failed to create file");
            }
            logger.info("File {} created successfully", filePath);
            return filePath;
        } catch (Exception e) {
            logger.warn("Failed to create file: {} {0}", filePath, e);
        }
        return null;
    }
}
