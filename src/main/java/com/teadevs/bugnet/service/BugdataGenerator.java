package com.teadevs.bugnet.service;

import com.teadevs.bugnet.model.Bug;

public interface BugdataGenerator {

    long generateId() ;

    String generateFileLocation(Bug bug);
}
