package application.enums;

public enum  Like {
    LIKED("1"),
    NOT_LIKED("0");

    private String title;

    Like(String title) {
        this.title = title;
    }

    public Long getTitle() {
        return Long.valueOf(title);
    }
}
