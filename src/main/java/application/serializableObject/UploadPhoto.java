package application.serializableObject;

import framework.logger.MyLogger;

import java.io.Serializable;


public class UploadPhoto implements Serializable {
    private static final MyLogger LOGGER = new MyLogger();

    private Long server;
    private String photo;
    private String hash;

    public UploadPhoto() {
        LOGGER.info("upload photo create");
    }

    public UploadPhoto(Long server, String json, String hash) {
        this.server = server;
        this.photo = json;
        this.hash = hash;
    }

    public Long getServer() {
        return server;
    }

    public String getPhoto() {
        return photo;
    }

    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return "UploadPhoto{"
                + "server=" + server
                + ", json=" + photo
                + ", hash='" + hash + '\'' + '}';
    }
}
