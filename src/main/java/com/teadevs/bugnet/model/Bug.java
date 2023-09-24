package com.teadevs.bugnet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;
import java.util.List;

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
    private List<BugComment> bugComments;

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

    public List<BugComment> getBugComments() {
        return bugComments;
    }

    public void setBugComments(List<BugComment> bugComments) {
        this.bugComments = bugComments;
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

    @Override
    public String toString() {
        return "Bug{" +
                "id=" + id +
                ", summary='" + summary + '\'' +
                ", bugComments=" + bugComments +
                ", assignedTo='" + assignedTo + '\'' +
                ", eta=" + eta +
                ", bugStatus=" + bugStatus +
                ", regression=" + regression +
                ", severity=" + severity +
                '}';
    }
}
