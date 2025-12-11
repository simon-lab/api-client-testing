package com.saimen.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Url {
    @JsonProperty("UrlEndpoint")
    private String UrlEndpoint;

    public Url() {
    }

    public Url(String urlEndpoint) {
        this.UrlEndpoint = urlEndpoint;
    }

    public String getUrlEndpoint() {
        return UrlEndpoint;
    }

    public void setUrlEndpoint(String urlEndpoint) {
        UrlEndpoint = urlEndpoint;
    }

   
}
