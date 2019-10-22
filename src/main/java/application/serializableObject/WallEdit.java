package application.serializableObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import framework.logger.MyLogger;

import java.io.Serializable;

public class WallEdit implements Serializable {

    private static final MyLogger LOGGER = new MyLogger();

    @JsonProperty("post_id")
    private Long postId;

    public WallEdit() {
        LOGGER.info("Wall Edit Post create");
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}


