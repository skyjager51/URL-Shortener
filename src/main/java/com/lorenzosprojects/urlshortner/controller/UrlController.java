package com.lorenzosprojects.urlshortner.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.lorenzosprojects.urlshortner.entity.EncodedUrlPojo;
import com.lorenzosprojects.urlshortner.entity.UrlEntity;
import com.lorenzosprojects.urlshortner.service.UrlService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/url-shortener")
public class UrlController {

    //contructor injection 
    private UrlService urlService;
    
    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }

    @PostMapping("/reduce")
    public EncodedUrlPojo encodeUrl(@RequestBody UrlEntity urlEntity){

        String encodedUrl = urlService.saveUrl(urlEntity);

        EncodedUrlPojo restUrl = new EncodedUrlPojo(encodedUrl);

        return restUrl;
    }

    @GetMapping("/reduce/{reducedUrl}")
    public RedirectView goToSite(@PathVariable String reducedUrl){

        System.out.println(reducedUrl);

        String decodedUrl = urlService.retrieveUrl(reducedUrl);

        return new RedirectView(decodedUrl);
    }
    


    //return new RedirectView("/") you can use this to be able to redirect ot external sites
}
