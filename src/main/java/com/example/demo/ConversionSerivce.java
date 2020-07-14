package com.example.demo;


import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

@Service
public class ConversionSerivce {

    public  UrlJpa urlJpa;
    public  ConversionClass conversionClass;

    public ConversionSerivce(UrlJpa urlRepository, ConversionClass baseConversion) {
        this.urlJpa = urlRepository;
        this.conversionClass = baseConversion;
    }

    public String convertToShortMethod(UrlPost request) {
        var url = new UrlObj();
        url.setFullUrl(request.getFullUrl());
        var jpa = urlJpa.save(url);
        return conversionClass.encodeUrlMethod(jpa.getIdNum());
    }

    public String getOriginalUrl(String shorterUrl) {
        var id = conversionClass.decodeUrlMethod(shorterUrl);
        var jpa = urlJpa.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shorterUrl));
        return jpa.getFullUrl();
    }
}
