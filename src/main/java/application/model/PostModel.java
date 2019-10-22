package application.model;

import application.enums.Attachment;
import application.request.Attachments;
import framework.logger.MyLogger;

public class PostModel {
    private static final MyLogger LOGGER = new MyLogger();

    private String name;
    private Long id;
    private String text;
    private User user;
    private Attachments attachments = Attachments.create();

    public PostModel(String name, Long id, String text, User user) {
        this.name = name;
        this.id = id;
        this.text = text;
        this.user = user;
    }

    public PostModel(String text, User user, String name) {
        this.name = name;
        this.text = text;
        this.user = user;
    }

    public String getName() {
        LOGGER.info("get name = ", name);
        return name;
    }

    public Long getID() {
        LOGGER.info("get postVK_ID = ", id);
        return id;
    }

    public void setId(Long id) {
        LOGGER.info("set postVK_ID = ", id);
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public Attachments getAttachments() {
        return attachments;
    }

    public void setAttachments(Attachments attachments) {
        this.attachments = attachments;
    }
}
