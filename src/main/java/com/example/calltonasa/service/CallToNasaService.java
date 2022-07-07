package com.example.calltonasa.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CallToNasaService {
    private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos";
    private static final String API_KEY = "DEMO_KEY";
    private RestTemplate restTemplate;
    public URI getLargestPicture(Integer sol, String apiKey) {
        Objects.requireNonNull(sol);
        var uri = UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("api_key", apiKey == null ? API_KEY : apiKey)
                .queryParam("sol", sol)
                .build()
                .toUri();

        var response = restTemplate.getForObject(uri, JsonNode.class);

        var imgs = response.get("photos").findValuesAsText("img_src");
        var imgWithMaxSize = imgs.stream()
                .map(URI::create)
                .collect(Collectors.collectingAndThen(Collectors.toMap(u -> u, u ->
                        restTemplate.headForHeaders(Objects.requireNonNull(restTemplate.headForHeaders(u).getLocation())).getContentLength()),
                        o -> o.entrySet().stream().max(Map.Entry.comparingByValue())));

        return imgWithMaxSize.get().getKey();
    }
}
