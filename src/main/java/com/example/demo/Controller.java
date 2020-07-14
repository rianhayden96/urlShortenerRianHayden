package com.example.demo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

public class Controller {

    public final ConversionSerivce conversionSerivce;

    public Controller(ConversionSerivce conversionSerivce) {
        this.conversionSerivce = conversionSerivce;
    }

    @PostMapping("createSamllerUrl")
    public String conversionToSmallerUrl(@RequestBody UrlPost request) {
        return conversionSerivce.convertToShortMethod(request);
    }

    @GetMapping(value = "{smallerUrl}")
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        var url = conversionSerivce.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
