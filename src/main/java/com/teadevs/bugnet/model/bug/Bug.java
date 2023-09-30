package com.teadevs.bugnet.model.bug;

import com.teadevs.bugnet.exceptions.BugnetException;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.ArrayList;
import java.util.Date;

@Document(collection = "bugs")
public class Bug {
    
    @Id
    @Field(value = "id", targetType = FieldType.INT64)
    private long id;
    
    @Field(value = "summary", targetType = FieldType.STRING)
    private String summary;
    
    /*
    1st item is description.
     */
    @Transient
    private ArrayList<BugUpdate> bugUpdates;
    
    @Field(value = "fileLocation", targetType = FieldType.STRING)
    private String bugFileLocation;
    
    @Field(value = "assignedTo", targetType = FieldType.STRING)
    private String assignedTo;
    
    private Date eta;
    
    private BugStatus bugStatus;
    
    private RegressionStatus regression;
    
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
    
    public ArrayList<BugUpdate> getBugUpdates() {
        return bugUpdates;
    }
    
    public void setBugUpdates(ArrayList<BugUpdate> bugUpdates) {
        this.bugUpdates = bugUpdates;
    }
    
    public String getBugFileLocation() {
        return bugFileLocation;
    }
    
    public void setBugFileLocation(String bugFileLocation) {
        this.bugFileLocation = bugFileLocation;
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
    
    public RegressionStatus getRegression() {
        return regression;
    }
    
    public void setRegression(RegressionStatus regression) {
        this.regression = regression;
    }
    
    public BugSeverity getSeverity() {
        return severity;
    }
    
    public void setSeverity(BugSeverity severity) {
        this.severity = severity;
    }
    
    public void validate() {
        if (this.id == 0) {
            throw new BugnetException("Invalid bug id");
        }
        if (this.getSummary() == null || this.getSummary().isBlank()) {
            throw new BugnetException("Invalid summary");
        }
        if (this.getAssignedTo() == null) {
            throw new BugnetException("Assignee is required");
        }
        if (this.getBugFileLocation() == null || this.getBugFileLocation().isBlank()) {
            throw new BugnetException("Invalid file location");
        }
    }
    
    @Override
    public String toString() {
        return "Bug{" +
                "id=" + id +
                ", summary='" + summary + '\'' +
                ", bugUpdates=" + bugUpdates +
                ", assignedTo='" + assignedTo + '\'' +
                ", eta=" + eta +
                ", bugStatus=" + bugStatus +
                ", regression=" + regression +
                ", severity=" + severity +
                '}';
    }
    
}