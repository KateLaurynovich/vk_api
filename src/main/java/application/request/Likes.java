package application.request;

import application.enums.Methods;
import application.enums.Parameters;
import application.model.PostModel;
import application.model.User;
import application.serializableObject.LikeIs;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.api.HttpConnection;
import framework.logger.MyLogger;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringReader;

public class Likes {
    private static final MyLogger LOGGER = new MyLogger();

    public static LikeIs likesIsLiked(PostModel postModel, User user, User requestUser) {
        Params params = Params.create()
                .add(Parameters.USER_ID.getTitle(), user.getUserId().toString())
                .add(Parameters.TYPE.getTitle(), "post")
                .add(Parameters.ITEM_ID.getTitle(), postModel.getID().toString());
        String link = BaseRequestVK.baseRequestVK(Methods.LIKE_IS_LIKED, params, requestUser);
        JSONObject jsonResp = HttpConnection.getStringResponse(link);
        JSONObject jsonObject = (JSONObject) jsonResp.get("response");
        StringReader reader = new StringReader(jsonObject.toString());
        LikeIs likeIs = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            likeIs = mapper.readValue(reader, LikeIs.class);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.info("liked = ", likeIs);
        return likeIs;
    }
}
