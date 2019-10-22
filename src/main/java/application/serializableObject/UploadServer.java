package application.serializableObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import framework.logger.MyLogger;

import java.io.Serializable;

public class UploadServer implements Serializable {
    private static final MyLogger LOGGER = new MyLogger();

    @JsonProperty("upload_url")
    private String uploadUrl;
    @JsonProperty("album_id")
    private String albumId;
    @JsonProperty("user_id")
    private String userId;

    public UploadServer() {
        LOGGER.info("upload server create");
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
