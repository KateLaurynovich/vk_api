package application.request;

import application.enums.Methods;
import application.enums.Parameters;
import application.model.User;
import framework.logger.MyLogger;
import framework.utils.PropertiesManager;

public class BaseRequestVK {
    private static MyLogger LOGGER = new MyLogger();

    public static String baseRequestVK(Methods method, Params parameters, User user) {
        String link = PropertiesManager.getProperties("server") + method.getTitle() + parameters.build()
                + Parameters.TOKEN.getTitle() + "=" + PropertiesManager.getProperties(user.getToken()) + "&"
                + Parameters.VERSION.getTitle() + "=" + PropertiesManager.getProperties("version");
        LOGGER.info(link);
        return link;
    }
}
