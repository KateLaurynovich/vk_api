package application.serializableObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import framework.logger.MyLogger;


import java.io.Serializable;

public class FileParam implements Serializable {
    private static final MyLogger LOGGER = new MyLogger();

    private Long id;
    @JsonProperty("owner_id")
    private Long ownerId;
    private Integer size;
    private String title;
    private String ext;
    private String url;
    private Integer date;
    private Integer type;

    public FileParam() {
        LOGGER.info("Create PhotoParam");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
