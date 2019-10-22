package application.serializableObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import framework.logger.MyLogger;

import java.io.Serializable;
import java.util.List;

public class PhotoParam implements Serializable {
    private static final MyLogger LOGGER = new MyLogger();

    private Long id;
    @JsonProperty("album_id")
    private Long albumId;
    @JsonProperty("owner_id")
    private Long ownerId;
    private List sizes;
    private String text;
    private Long date;
    @JsonProperty("access_key")
    private String access_key;

    public PhotoParam() {
        LOGGER.info("Create PhotoParam");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List getSizes() {
        return sizes;
    }

    public void setSizes(List sizes) {
        this.sizes = sizes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getAccess_key() {
        return access_key;
    }

    public void setAccess_key(String access_key) {
        this.access_key = access_key;
    }
}
