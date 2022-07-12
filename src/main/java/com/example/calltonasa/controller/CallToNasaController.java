package com.example.calltonasa.controller;

import com.example.calltonasa.service.CallToNasaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pictures/")
@AllArgsConstructor
public class CallToNasaController {

    private CallToNasaService callToNasaService;

    @GetMapping("{sol}/largest")
    public ResponseEntity<Object> callToNasa(@PathVariable Integer sol)  {
        var uri = callToNasaService.getLargestPicture(sol, null);

        return ResponseEntity
                .status(HttpStatus.PERMANENT_REDIRECT)
                .location(uri)
                .build();
    }


}
