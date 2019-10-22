package framework.api;

import framework.logger.MyLogger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

public class HttpConnection {

    private static final MyLogger LOGGER = new MyLogger();

    public static HttpResponse getResponse(String link) {
        URI uri = URI.create(link);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(uri);
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
            System.exit(-1);
        }
        return response;
    }

    public static JSONObject getStringResponse(String link) {
        HttpResponse response = getResponse(link);
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