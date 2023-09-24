package com.teadevs.bugnet.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teadevs.bugnet.model.Bug;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public interface BugnetLocalRepository {

    public void saveComments(Bug bug) throws IOException;
}
