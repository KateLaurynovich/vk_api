package steps;

import application.enums.Parameters;
import application.model.PostModel;
import application.model.User;
import application.request.Files;
import application.request.Photo;


import application.serializableObject.PhotoParam;
import framework.logger.MyLogger;

public class SaveFile {
    private static final MyLogger LOGGER = new MyLogger();

    public static PhotoParam savePhoto(PostModel postModel, String path, Parameters parameters, User user) {
        LOGGER.info("save photo");
        return Photo.saveWallPhoto(postModel,Photo.getUpload(postModel, path, parameters, user), user);
    }

    public static Long saveFile(PostModel postModel, String path, Parameters parameters, User user) {
        LOGGER.info("save file");
        return Files.docsSave(postModel, Files.getUpload(postModel, path, parameters, user), user).getId();
    }
}