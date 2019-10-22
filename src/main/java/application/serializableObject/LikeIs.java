package application.serializableObject;

import framework.logger.MyLogger;

import java.io.Serializable;

public class LikeIs implements Serializable {
    private static final MyLogger LOGGER = new MyLogger();

    private Long liked;
    private Long copied;

    public LikeIs() {
        LOGGER.info("create like");
    }

    public Long getLiked() {
        return liked;
    }

    public void setLiked(Long liked) {
        this.liked = liked;
    }

    public Long getCopied() {
        return copied;
    }

    public void setCopied(Long copied) {
        this.copied = copied;
    }
}
