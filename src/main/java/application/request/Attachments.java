package application.request;

import application.enums.Attachment;
import application.model.User;
import framework.logger.MyLogger;

import java.util.HashMap;

public class Attachments {
    private static MyLogger LOGGER = new MyLogger();
    public static Attachments create() {
        return new Attachments();
    }

    private final HashMap<Attachment, Long> attachments;

    private Attachments() {
        attachments = new HashMap<>();
    }

    public Attachments add(Attachment key, Long value) {
        attachments.put(key, value);
        return this;
    }


    public String build(User user) {
        if (attachments.isEmpty()) return "";
        final StringBuilder result = new StringBuilder();
        attachments.keySet().stream().forEach(key -> {
            result.append(key.getTitle()).append(user.getUserId().toString()).append("_").append(attachments.get(key)).append(",");
        });
        result.deleteCharAt(result.length()-1);
        LOGGER.info(result.toString());

        return result.toString();
    }
}

