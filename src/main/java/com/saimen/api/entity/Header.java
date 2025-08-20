package com.saimen.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Header {
    private String Authorization;

    @JsonProperty("X-TIMESTAMP")
    private String xTimeStamp;

    @JsonProperty("X-SIGNATURE")
    private String xSignature;

    @JsonProperty("X-PARTNER-ID")
    private String xPartnerId;

    @JsonProperty("X-EXTERNAL-ID")
    private String xExternalId;

    @JsonProperty("CHANNEL-ID")
    private String channelId;

    public Header() {
    }

    public Header(String authorization, String xTimeStamp, String xSignature, String xPartnerId, String xExternalId,
            String channelId) {
        Authorization = authorization;
        this.xTimeStamp = xTimeStamp;
        this.xSignature = xSignature;
        this.xPartnerId = xPartnerId;
        this.xExternalId = xExternalId;
        this.channelId = channelId;
    }

    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }

    public String getxTimeStamp() {
        return xTimeStamp;
    }

    public void setxTimeStamp(String xTimeStamp) {
        this.xTimeStamp = xTimeStamp;
    }

    public String getxSignature() {
        return xSignature;
    }

    public void setxSignature(String xSignature) {
        this.xSignature = xSignature;
    }

    public String getxPartnerId() {
        return xPartnerId;
    }

    public void setxPartnerId(String xPartnerId) {
        this.xPartnerId = xPartnerId;
    }

    public String getxExternalId() {
        return xExternalId;
    }

    public void setxExternalId(String xExternalId) {
        this.xExternalId = xExternalId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

}
