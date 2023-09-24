package com.teadevs.bugnet.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teadevs.bugnet.model.Bug;
import com.teadevs.bugnet.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Repository
public class BugnetLocalRepositoryImpl implements BugnetLocalRepository {
    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    JsonUtils jsonUtils;

    @Override
    public void saveComments(Bug bug) throws IOException {
        String data = jsonUtils.toJson(bug.getBugComments());
        FileUtils.write(new File(bug.getBugFileLocation()), data, StandardCharsets.UTF_8);
        logger.info("Data saved successfully");
    }
}
