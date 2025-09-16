package com.lorenzosprojects.urlshortner.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lorenzosprojects.urlshortner.dao.UrlDao;
import com.lorenzosprojects.urlshortner.entity.UrlEntity;
import com.lorenzosprojects.urlshortner.utilities.EncodeDecodeAlgorithm;

import jakarta.transaction.Transactional;

@Service
public class UrlServiceImpl implements UrlService{

    //injecting contructors for accessing class methods and base url
    private UrlDao urlDao;

    private EncodeDecodeAlgorithm encodeDecodeAlgorithm;

    @Value("${application.base-url}")
    private String baseUrl;

    public UrlServiceImpl(UrlDao theUrlDao, EncodeDecodeAlgorithm theEncodeDecodeAlgorithm){
        urlDao = theUrlDao;
        encodeDecodeAlgorithm = theEncodeDecodeAlgorithm;
    }

    //saving the passed url in the db and return the encoded value of the id
    @Override
    @Transactional
    public String saveUrl(UrlEntity urlEntity) {

        UrlEntity theUrlEntity = urlDao.saveUrl(urlEntity);

        String theId = String.valueOf(theUrlEntity.getId());

        String encodedId = encodeDecodeAlgorithm.encodeAlgorithm(theId);
        
        return baseUrl + "/" + encodedId;
    }

    @Override
    public String retrieveUrl(String id) {

        //decode the base62 url back to its original decimal value
        String decodedId = encodeDecodeAlgorithm.decodeAlgorithm(id);
        int theId = Integer.parseInt(decodedId);

        UrlEntity theUrlEntity = urlDao.retrieveUrl(theId);

        return theUrlEntity.getUrlValue();
    }

}
