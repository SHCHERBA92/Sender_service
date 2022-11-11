package com.example.sender_service.service.sender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class SenderSms implements Sender{

    private final HttpClient httpClient;

    @Value("${sms.areo.api_key}")
    private String API_KEY;

    @Value("${sms.user}")
    private String USER;

    @Value("${sms.sign}")
    private String SIGN;

    @Value("${sms.prostosms.password}")
    private String PASSWORD;

    private final String BEGIN_URL = "http://api.sms-prosto.ru/";

    private final String TEXT = "Для авторизации на портале введите код: ";

    public SenderSms(HttpClient httpClient) {
        this.httpClient = httpClient;
    }


    @Override
    public void sendMessToRegistration(String to, String code) {
        StringBuilder urlBuilder = new StringBuilder(BEGIN_URL);
        urlBuilder.append("?")
                .append("method=push_msg")
                .append("&").append("format=json")
                .append("&").append("email=").append(USER)
                .append("&").append("password=").append(PASSWORD)
                .append("&").append("text=").append(URLEncoder.encode(TEXT, StandardCharsets.UTF_8)+code)
                .append("&").append("phone=").append(to)
                .append("&").append("sender_name=").append(SIGN);

        URI uri = URI.create(urlBuilder.toString());

        HttpRequest httpRequest = HttpRequest.newBuilder(uri).GET().build();
        try {
            var response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessToInfo(String to) {

    }
}
