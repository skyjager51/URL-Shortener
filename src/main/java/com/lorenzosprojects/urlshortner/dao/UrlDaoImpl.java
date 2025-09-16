package com.lorenzosprojects.urlshortner.dao;

import org.springframework.stereotype.Repository;

import com.lorenzosprojects.urlshortner.entity.UrlEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

@Repository
public class UrlDaoImpl implements UrlDao{

    private EntityManager entityManager;

    public UrlDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public UrlEntity saveUrl(UrlEntity urlEntity) {

        TypedQuery<UrlEntity> query = entityManager.createQuery(
            "SELECT s FROM UrlEntity s WHERE s.urlValue = :data", UrlEntity.class
        );
        query.setParameter("data", urlEntity.getUrlValue());

        try{

            return query.getSingleResult();
        }
        catch (NoResultException e){

            return entityManager.merge(urlEntity);
        }
    }

    @Override
    public UrlEntity retrieveUrl(int id) {
        return entityManager.find(UrlEntity.class, id);
    }

}
