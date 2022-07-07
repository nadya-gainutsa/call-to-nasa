package com.example.calltonasa.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CallToNasaService {
    private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?";
    private static final String API_KEY_PARAMETER_TEMPLATE = "api_key=%s";
    private static final String API_KEY_PARAMETER_DEFAULT = "DEMO_KEY";
    private static final String SOL_PARAMETER_TEMPLATE = "sol=%d";
    public URI getLargestPicture(Integer sol, String apiKey) {
        Objects.requireNonNull(sol);
        String url = BASE_URL +
                "?" +
                String.format(API_KEY_PARAMETER_TEMPLATE, apiKey == null ? API_KEY_PARAMETER_DEFAULT : apiKey) +
                "&" +
                String.format(SOL_PARAMETER_TEMPLATE, sol);
        var restTemplate = new RestTemplate();
        var response = restTemplate.getForObject(url, JsonNode.class);

        var imgs = response.get("photos").findValuesAsText("img_src");
        var imgWithMaxSize = imgs.stream()
                .map(URI::create)
                .collect(Collectors.collectingAndThen(Collectors.toMap(u -> u, u ->
                        restTemplate.headForHeaders(Objects.requireNonNull(restTemplate.headForHeaders(u).getLocation())).getContentLength()),
                        o -> o.entrySet().stream().max(Map.Entry.comparingByValue())));

        return imgWithMaxSize.get().getKey();
    }
}
