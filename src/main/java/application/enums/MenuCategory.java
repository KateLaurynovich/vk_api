package application.enums;

public enum  MenuCategory {
    PROFILE("pr"),
    NEWS("nwsf"),
    MESSAGES("msg"),
    FRIENDS("fr");

    private String title;

    MenuCategory(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
