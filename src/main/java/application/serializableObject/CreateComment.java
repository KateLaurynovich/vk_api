package application.serializableObject;


import com.fasterxml.jackson.annotation.JsonProperty;
import framework.logger.MyLogger;

import java.io.Serializable;
import java.util.List;

public class CreateComment implements Serializable {
    private static final MyLogger LOGGER = new MyLogger();

    @JsonProperty("comment_id")
    private Long commentID;
    @JsonProperty("parents_stack")
    private List parentsStack;

    public CreateComment() {
        LOGGER.info("comment create");
    }

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public List getParentsStack() {
        return parentsStack;
    }

    public void setParentsStack(List parentsStack) {
        this.parentsStack = parentsStack;
    }
}
