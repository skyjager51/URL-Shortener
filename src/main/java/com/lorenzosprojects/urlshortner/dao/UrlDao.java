package com.lorenzosprojects.urlshortner.dao;

import com.lorenzosprojects.urlshortner.entity.UrlEntity;

public interface UrlDao {

    UrlEntity saveUrl(UrlEntity urlEntity);

    UrlEntity retrieveUrl(int id);
}
