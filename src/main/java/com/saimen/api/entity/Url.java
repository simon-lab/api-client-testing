package com.saimen.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Url {
    @JsonProperty("UrlEndpoint")
    private String UrlEndpoint;

    @JsonProperty("detectedService")
    private String DetectedService;

    public Url() {
    }

    public Url(String urlEndpoint, String detectedService) {
        this.UrlEndpoint = urlEndpoint;
        this.DetectedService = detectedService;
    }

    public String getUrlEndpoint() {
        return UrlEndpoint;
    }

    public void setUrlEndpoint(String urlEndpoint) {
        UrlEndpoint = urlEndpoint;
    }

    public String getDetectedService() {
        return DetectedService;
    }

    public void setDetectedService(String detectedService) {
        DetectedService = detectedService;
    }

}
