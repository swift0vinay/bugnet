package com.teadevs.bugnet.service;

import com.teadevs.bugnet.model.Bug;
import com.teadevs.bugnet.model.BugComment;

import java.util.List;

public interface BugnetService {

    public void createBug(Bug bug);

    public void updateBug(Bug bug);

    public List<Bug> getBugs();

    public List<BugComment> getComments(String fileLocation);
}
