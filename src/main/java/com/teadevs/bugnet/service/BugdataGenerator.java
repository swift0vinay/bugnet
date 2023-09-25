package com.teadevs.bugnet.service;

import com.teadevs.bugnet.model.bug.Bug;

import java.util.List;
import java.util.Map;

public interface BugdataGenerator {

    public void generateId(Bug bug);

    public void generateFileLocation(Bug bug);
    
    public Map<String, List<String>> generateBugDiff(Bug original, Bug request);
}
