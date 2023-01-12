package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=aw7l4IU9SgZ44HgBjNNUbx19Yddexyyuv9orD6wo");
        AnswerNasa answerNasa = null;
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
            Arrays.stream(response.getAllHeaders()).forEach(System.out::println);
            System.out.println();
            String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(body);
            answerNasa = mapper.readValue(body, AnswerNasa.class);
            request = new HttpGet(answerNasa.getUrl().toURI());
            System.out.println("https://apod.nasa.gov/apod/image/2301/RockyArchAurora_Pellegrini_960.jpg");

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        System.out.println(answerNasa);
        String fileName = answerNasa.urlName();
        try (CloseableHttpResponse response = httpClient.execute(request)) {

            OutputStream outputStream = new FileOutputStream(fileName);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = response.getEntity().getContent().read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
