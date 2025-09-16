package com.lorenzosprojects.urlshortner.entity;

public class EncodedUrlPojo {

    private String encodedUrl;

    public EncodedUrlPojo(String encodedUrl) {
        this.encodedUrl = encodedUrl;
    }

    public EncodedUrlPojo() {
    }

    public String getEncodedUrl() {
        return encodedUrl;
    }

    public void setEncodedUrl(String encodedUrl) {
        this.encodedUrl = encodedUrl;
    }
   
}
