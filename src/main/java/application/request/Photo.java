package application.request;

import application.enums.Methods;
import application.enums.Parameters;
import application.model.PostModel;
import application.model.User;
import application.serializableObject.PhotoParam;
import application.serializableObject.UploadPhoto;
import application.serializableObject.UploadServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.api.HttpConnection;
import framework.api.UploadFile;
import framework.logger.MyLogger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class Photo {
    private static final MyLogger LOGGER = new MyLogger();

    public static UploadServer getWallUploadServer(PostModel postModel, User user) {
        Params params = Params.create()
                .add(Parameters.GROUP_ID.getTitle(), postModel.getUser().getUserId().toString());
        String link = BaseRequestVK.baseRequestVK(Methods.PHOTOS_GET_WALL_UPLOAD_SERVER, params, user);
        JSONObject jsonResp = HttpConnection.getStringResponse(link);
        JSONObject jsonObject = (JSONObject) jsonResp.get("response");
        StringReader reader = new StringReader(jsonObject.toString());
        UploadServer uploadServer = new UploadServer();
        ObjectMapper mapper = new ObjectMapper();
        try {
            uploadServer = mapper.readValue(reader, UploadServer.class);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.info("upload server  = ", uploadServer);
        return uploadServer;
    }

    public static UploadPhoto getUpload(PostModel postModel, String path, Parameters parameters, User user) {
        JSONObject jsonResp = UploadFile.getStringResponse(getWallUploadServer(postModel, user).getUploadUrl(), path, parameters);
        StringReader reader = new StringReader(jsonResp.toString());
        UploadPhoto uploadPhoto = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            uploadPhoto = mapper.readValue(reader, UploadPhoto.class);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.info("upload photo  = ", uploadPhoto);
        return uploadPhoto;
    }

    public static PhotoParam saveWallPhoto(PostModel postModel, UploadPhoto uploadPhoto, User user) {
        String link = null;
        try {
            Params params = Params.create()
                    .add(Parameters.GROUP_ID.getTitle(), postModel.getUser().getUserId().toString())
                    .add(Parameters.PHOTO.getTitle(), URLEncoder.encode(uploadPhoto.getPhoto(), "UTF-8"))
                    .add(Parameters.SERVER.getTitle(), uploadPhoto.getServer().toString())
                    .add(Parameters.HASH.getTitle(), uploadPhoto.getHash());
            link = BaseRequestVK.baseRequestVK(Methods.PHOTOS_SAVE_WALL_PHOTO, params, user);

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException", e);
        }
        JSONObject jsonResp = HttpConnection.getStringResponse(link);
        PhotoParam photoParam = null;
        JSONArray jsonArray = (JSONArray) jsonResp.get("response");
        for (Object o : jsonArray) {
            JSONObject jsonLineItem = (JSONObject) o;
            StringReader reader = new StringReader(jsonLineItem.toString());

            ObjectMapper mapper = new ObjectMapper();
            try {
                photoParam = mapper.readValue(reader, PhotoParam.class);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
            }
        }
        return photoParam;
    }
}
