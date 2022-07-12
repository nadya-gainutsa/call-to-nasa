package com.example.calltonasa;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@Log4j2
public class CallToNasaApplication {

    public static void main(String[] args) {

        SpringApplication.run(CallToNasaApplication.class, args);

    }

    @Bean
    public static RestTemplate createRestTemplateBean(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Scheduled(fixedDelayString = "P1D")
    @CacheEvict(value = "largestPicture", allEntries = true)
    public void clearCache() {
        log.info("Clearing cache ...");
    }


}
