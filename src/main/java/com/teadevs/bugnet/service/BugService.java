package com.teadevs.bugnet.service;

import com.teadevs.bugnet.model.Bug;
import com.teadevs.bugnet.model.BugComment;

public interface BugService {

    public void addBug(Bug bug);

    public void addComment(BugComment bugComment);

}
