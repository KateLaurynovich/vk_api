package application.serializableObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.json.simple.JSONObject;

import java.io.Serializable;

public class FileJSON implements Serializable {
    @JsonIgnore
    private String type;

    private JSONObject doc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JSONObject getDoc() {
        return doc;
    }

    public void setDoc(JSONObject doc) {
        this.doc = doc;
    }
}
