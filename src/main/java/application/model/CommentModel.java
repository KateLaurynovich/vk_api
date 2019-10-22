package application.model;

public class CommentModel {

    PostModel postModel;
    Long ID;
    String text;
    User user;

    public CommentModel(PostModel postModel, Long ID, User user) {
        this.postModel = postModel;
        this.ID = ID;
        this.user = user;
    }

    public PostModel getPostModel() {
        return postModel;
    }

    public void setPostModel(PostModel postModel) {
        this.postModel = postModel;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
