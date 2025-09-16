package com.lorenzosprojects.urlshortner.service;

import com.lorenzosprojects.urlshortner.entity.UrlEntity;

public interface UrlService {

    String saveUrl(UrlEntity urlEntity);

    String retrieveUrl(String id);
}
