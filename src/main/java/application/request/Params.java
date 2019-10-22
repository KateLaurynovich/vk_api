package application.request;

import java.util.HashMap;

public class Params {
    public static Params create() {
        return new Params();
    }

    private final HashMap<String, String> params;

    private Params() {
        params = new HashMap<>();
    }

    public Params add(String key, String value) {
        params.put(key, value);
        return this;
    }

    public String build() {
        if (params.isEmpty()) return "";
        final StringBuilder result = new StringBuilder();
        params.keySet().stream().forEach(key -> {
            result.append(key).append('=').append(params.get(key)).append('&');
        });
        return "?"+result.toString();
    }
}
