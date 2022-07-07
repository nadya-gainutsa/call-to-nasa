package com.example.calltonasa.controller;

import com.example.calltonasa.service.CallToNasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("pictures/")
public class CallToNasaController {
    private CallToNasaService callToNasaService;

    public CallToNasaController(@Autowired CallToNasaService callToNasaService) {}

    @GetMapping("{sol}/largest")
    public Object callToNasa(@PathVariable Integer sol) throws IOException {
        var uri = callToNasaService.getLargestPicture(sol, null);
        var restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, Object.class);

    }


}
