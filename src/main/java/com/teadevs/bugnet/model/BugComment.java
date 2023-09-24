package com.teadevs.bugnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BugComment {

    @JsonProperty
    private String bugId;

    private Date commentDate;

    @JsonProperty
    private String commenter;

    @JsonProperty
    private String comment;

    /*
    what about changes?
     */

    public String getBugId() {
        return bugId;
    }

    public void setBugId(String bugId) {
        this.bugId = bugId;
    }

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
}
