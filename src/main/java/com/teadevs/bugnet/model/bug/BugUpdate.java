package com.teadevs.bugnet.model.bug;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class BugUpdate {
    
    private Date commentDate;
    
    private String commenter;
    
    private String comment;
    
    /*
    what about changes?
     */
    private Map<String, List<String>> changes;
    
    public Date getCommentDate() {
        return commentDate;
    }
    
    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
    
    public String getCommenter() {
        return commenter;
    }
    
    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public Map<String, List<String>> getChanges() {
        return changes;
    }
    
    public void setChanges(Map<String, List<String>> changes) {
        this.changes = changes;
    }
    
    @Override
    public String toString() {
        return "BugUpdate{" +
                ", commentDate=" + commentDate +
                ", commenter='" + commenter + '\'' +
                ", comment='" + comment + '\'' +
                ", changes=" + changes +
                '}';
    }
    
}
