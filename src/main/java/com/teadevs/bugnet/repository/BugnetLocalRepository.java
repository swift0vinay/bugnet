package com.teadevs.bugnet.repository;

import com.teadevs.bugnet.model.bug.Bug;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public interface BugnetLocalRepository {

    public void saveComments(Bug bug);
}
