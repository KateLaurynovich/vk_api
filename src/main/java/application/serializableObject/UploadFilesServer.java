package application.serializableObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import framework.logger.MyLogger;

public class UploadFilesServer {
    private static final MyLogger LOGGER = new MyLogger();

    @JsonProperty("upload_url")
    private String uploadURL;

    public UploadFilesServer() {
        LOGGER.info("upload file server create");
    }

    public String getUploadUrl() {
        return uploadURL;
    }

    public void setUploadUrl(String uploadURL) {
        this.uploadURL = uploadURL;
    }
}
