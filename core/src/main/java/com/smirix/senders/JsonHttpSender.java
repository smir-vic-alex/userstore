package com.smirix.senders;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class JsonHttpSender<Rq, Rs> {

    protected String send(Rq request, String url) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            StringEntity requestEntity = new StringEntity(serializeToJson(request), ContentType.APPLICATION_JSON);
            HttpPost postMethod = new HttpPost(url);
            postMethod.setEntity(requestEntity);
            HttpResponse rawResponse = httpClient.execute(postMethod);

            return EntityUtils.toString(rawResponse.getEntity(), JsonEncoding.UTF8.getJavaName());
        } catch (IOException e) {
            return null;
        }
    }

    private String serializeToJson(Rq request) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

}
