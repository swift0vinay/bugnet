package com.teadevs.bugnet.model;

import java.util.Date;
import java.util.List;

public class Bug {

    private long id;

    private String summary;

    private List<BugComment> bugComments;

    private String assignedTo;

    private Date eta;

    private BugStatus bugStatus;

    private boolean regression;

    private BugSeverity severity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<BugComment> getBugComments() {
        return bugComments;
    }

    public void setBugComments(List<BugComment> bugComments) {
        this.bugComments = bugComments;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Date getEta() {
        return eta;
    }

    public void setEta(Date eta) {
        this.eta = eta;
    }

    public BugStatus getBugStatus() {
        return bugStatus;
    }

    public void setBugStatus(BugStatus bugStatus) {
        this.bugStatus = bugStatus;
    }

    public boolean isRegression() {
        return regression;
    }

    public void setRegression(boolean regression) {
        this.regression = regression;
    }

    public BugSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(BugSeverity severity) {
        this.severity = severity;
    }

    
}
