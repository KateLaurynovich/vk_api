package application.enums;

public enum Parameters {

    TOKEN("access_token"),
    VERSION("v"),
    OWNER_ID("owner_id"),
    MESSAGE("message"),
    GROUP_ID("group_id"),
    SERVER("server"),
    HASH("hash"),
    POST_ID("post_id"),
    ATTACHMENTS("attachments"),
    TYPE("type"),
    PHOTO("photo"),
    DOC("doc"),
    FILE("file"),
    USER_ID("user_id"),
    ITEM_ID("item_id"),
    NO("null");

    private String title;

    Parameters(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
