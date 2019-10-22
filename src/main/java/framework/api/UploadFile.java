package framework.api;

import application.enums.Parameters;
import framework.logger.MyLogger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UploadFile {
    private static final MyLogger LOGGER = new MyLogger();

    public static HttpResponse uploadFile(String url, String path, Parameters parameters) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);
        File f = new File(path);
        try {
            builder.addBinaryBody(
                    parameters.getTitle(),
                    new FileInputStream(f),
                    ContentType.MULTIPART_FORM_DATA,
                    f.getName()
            );
        } catch (FileNotFoundException e) {
            LOGGER.error("FileNotFoundException", e);
        }
        HttpEntity multipart = builder.build();
        uploadFile.setEntity(multipart);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(uploadFile);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        return response;
    }

    public static JSONObject getStringResponse(String url, String path, Parameters parameters) {
        HttpResponse response = uploadFile(url, path, parameters);
        HttpEntity entity = response.getEntity();
        String stringResponse = null;
        try {
            stringResponse = EntityUtils.toString(entity);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.info("get HttpResponse  getStringResponse" + stringResponse);
        JSONParser parser = new JSONParser();
        JSONObject jsonResp = null;
        try {
            jsonResp = (JSONObject) parser.parse(stringResponse);
        } catch (ParseException e) {
            LOGGER.error("ParseException", e);
        }
        return jsonResp;
    }
}
