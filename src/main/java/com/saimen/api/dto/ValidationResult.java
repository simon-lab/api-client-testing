package com.saimen.api.dto;

public class ValidationResult {
    private String status;
    private String message;
    private String detectedService;

    public ValidationResult(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ValidationResult(String status, String message, String detectedService) {
        this.status = status;
        this.message = message;
        this.detectedService = detectedService;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetectedService() {
        return detectedService;
    }

    public void setDetectedService(String detectedService) {
        this.detectedService = detectedService;
    }
}
