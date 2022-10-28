package com.shrt.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class ApplicationConfig {

    @Bean
    public Gson gson() {
        return new GsonBuilder().create();
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder().build();
    }
}
