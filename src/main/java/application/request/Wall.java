package application.request;

import application.enums.Methods;
import application.enums.Parameters;
import application.model.PostModel;
import application.model.User;
import application.serializableObject.CreateComment;
import application.serializableObject.WallEdit;
import application.serializableObject.WallPost;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.api.HttpConnection;
import framework.logger.MyLogger;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringReader;

public class Wall {
    private static final MyLogger LOGGER = new MyLogger();

    public static WallPost wallPost(PostModel postModel, User user) {
        Params params = Params.create()
                .add(Parameters.OWNER_ID.getTitle(), postModel.getUser().getUserId().toString())
                .add(Parameters.MESSAGE.getTitle(), postModel.getText())
                .add(Parameters.ATTACHMENTS.getTitle(), postModel.getAttachments().build(user));
        String link = BaseRequestVK.baseRequestVK(Methods.WALL_POST, params, user);
        JSONObject jsonResp = HttpConnection.getStringResponse(link);
        JSONObject jsonObject = (JSONObject) jsonResp.get("response");
        StringReader reader = new StringReader(jsonObject.toString());
        WallPost wallPost = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            wallPost = mapper.readValue(reader, WallPost.class);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.info("wall post = ", wallPost);
        return wallPost;
    }

    public static WallEdit wallEdit(PostModel postModel, User user) {
        Params params = Params.create()
                .add(Parameters.OWNER_ID.getTitle(), postModel.getUser().getUserId().toString())
                .add(Parameters.POST_ID.getTitle(), postModel.getID().toString())
                .add(Parameters.MESSAGE.getTitle(), postModel.getText())
                .add(Parameters.ATTACHMENTS.getTitle(), postModel.getAttachments().build(user));
        String link = BaseRequestVK.baseRequestVK(Methods.WALL_EDIT, params, user);
        JSONObject jsonResp = HttpConnection.getStringResponse(link);
        JSONObject jsonObject = (JSONObject) jsonResp.get("response");
        StringReader reader = new StringReader(jsonObject.toString());
        WallEdit wallEdit = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            wallEdit = mapper.readValue(reader, WallEdit.class);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.info("edit post = ", wallEdit);
        return wallEdit;
    }

    public static CreateComment wallCreateComment(PostModel postModel, User user) {
        Params params = Params.create()
                .add(Parameters.OWNER_ID.getTitle(), postModel.getUser().getUserId().toString())
                .add(Parameters.POST_ID.getTitle(), postModel.getID().toString())
                .add(Parameters.MESSAGE.getTitle(), postModel.getText());
        String link = BaseRequestVK.baseRequestVK(Methods.WALL_CREATE_COMMENT, params, user);
        JSONObject jsonResp = HttpConnection.getStringResponse(link);
        JSONObject jsonObject = (JSONObject) jsonResp.get("response");
        StringReader reader = new StringReader(jsonObject.toString());
        CreateComment createComment = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            createComment = mapper.readValue(reader, CreateComment.class);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.info("create comment = ", createComment);
        return createComment;
    }

    public static Long wallDelete(PostModel postModel, User user) {
        Params params = Params.create()
                .add(Parameters.OWNER_ID.getTitle(), postModel.getUser().getUserId().toString())
                .add(Parameters.POST_ID.getTitle(), postModel.getID().toString());
        String link = BaseRequestVK.baseRequestVK(Methods.WALL_DELETE, params, user);
        JSONObject jsonResp = HttpConnection.getStringResponse(link);
        Long result = (Long) jsonResp.get("response");
        LOGGER.info("response delete = ", result);
        return result;
    }
}
