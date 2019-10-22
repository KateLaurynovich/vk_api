package application.model;


import framework.logger.MyLogger;

public class User {

    private static final MyLogger LOGGER = new MyLogger();

    public User(Long userID) {
        this.userID = userID;
    }

    private Long userID;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getUserId(){
        return userID;
    }
}
