package com.example.calltonasa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CallToNasaApplication {

    public static void main(String[] args) {

        SpringApplication.run(CallToNasaApplication.class, args);

    }

    @Bean
    public static RestTemplate createRestTemplateBean() {
        return new RestTemplate();
    }


}
