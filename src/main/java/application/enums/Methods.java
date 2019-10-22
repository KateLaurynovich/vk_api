package application.enums;

public enum Methods {

    WALL_POST("wall.post"),
    PHOTOS_GET_WALL_UPLOAD_SERVER("photos.getWallUploadServer"),
    DOCS_GET_WALL_UPLOAD_SERVER("docs.getWallUploadServer"),
    PHOTOS_SAVE_WALL_PHOTO("photos.saveWallPhoto"),
    DOCS_SAVE("docs.save"),
    WALL_EDIT("wall.edit"),
    WALL_CREATE_COMMENT("wall.createComment"),
    LIKE_IS_LIKED("likes.isLiked"),
    WALL_DELETE("wall.delete");

    private String title;

    Methods(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
