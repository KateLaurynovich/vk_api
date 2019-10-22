package application.enums;

public enum  TopCategory {
    PROFILE("myprofile"),
    EDIT("edit"),
    SETTING("setting"),
    SUPPORT("support"),
    LOGOUT("logout");

    private String title;

    TopCategory(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
