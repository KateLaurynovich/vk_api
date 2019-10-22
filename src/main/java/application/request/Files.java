package application.request;

import application.enums.Methods;
import application.enums.Parameters;
import application.model.PostModel;
import application.model.User;
import application.serializableObject.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.api.HttpConnection;
import framework.api.UploadFile;
import framework.logger.MyLogger;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Files {

    private static final MyLogger LOGGER = new MyLogger();

    public static UploadFilesServer getWallUploadServer(PostModel postModel, User user) {
        Params params = Params.create()
                .add(Parameters.USER_ID.getTitle(), postModel.getUser().getUserId().toString());
        String link = BaseRequestVK.baseRequestVK(Methods.DOCS_GET_WALL_UPLOAD_SERVER, params, user);
        JSONObject jsonResp = HttpConnection.getStringResponse(link);
        JSONObject jsonObject = (JSONObject) jsonResp.get("response");
        StringReader reader = new StringReader(jsonObject.toString());
        UploadFilesServer uploadServer = new UploadFilesServer();
        ObjectMapper mapper = new ObjectMapper();
        try {
            uploadServer = mapper.readValue(reader, UploadFilesServer.class);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.info("upload server  = ", uploadServer.getUploadUrl());
        return uploadServer;
    }

    public static SerUploadFile getUpload(PostModel postModel, String path, Parameters parameters, User user) {
        LOGGER.info("upload " + path + " on upload server");
        JSONObject jsonResp = UploadFile.getStringResponse(getWallUploadServer(postModel, user).getUploadUrl(), path, parameters);
        StringReader reader = new StringReader(jsonResp.toString());
        SerUploadFile serUploadFile = null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            serUploadFile = mapper.readValue(reader, SerUploadFile.class);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.info("upload photo  = ", serUploadFile.getFile());
        return serUploadFile;
    }

    public static FileParam docsSave(PostModel postModel, SerUploadFile files, User user) {
        String link;
        Params params = null;
        try {
            params = Params.create()
                    .add(Parameters.FILE.getTitle(), URLEncoder.encode(files.getFile(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException", e);
        }
        link = BaseRequestVK.baseRequestVK(Methods.DOCS_SAVE, params, user);
        JSONObject jsonResp = HttpConnection.getStringResponse(link);
        JSONObject jsonObject = (JSONObject) jsonResp.get("response");
        FileJSON fileJSON = new FileJSON();
        StringReader reader = new StringReader(jsonObject.toString());
        ObjectMapper mapper = new ObjectMapper();
        try {
            fileJSON = mapper.readValue(reader, FileJSON.class);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.info(fileJSON.getDoc().toString());
        StringReader reader1 = new StringReader(fileJSON.getDoc().toString());
        ObjectMapper mapper1 = new ObjectMapper();
        FileParam fileParam = null;
        try {
            fileParam = mapper1.readValue(reader1, FileParam.class);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.info(fileParam.getId().toString());
        return fileParam;
    }
}
