package com.linkedIn.api.service;

import java.time.LocalDateTime;

public final class AccessToken {
    private String accessToken;
    private Integer expiresIn;
    private LocalDateTime expirationDate;

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public AccessToken(String accessToken, Integer expiresIn) {
        initialize(accessToken, expiresIn);
    }

    private void initialize(String accessToken, Integer expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        if (accessToken == null || expiresIn == null) return;
        expirationDate = LocalDateTime.now().plusSeconds(expiresIn);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setToken(String accessToken, Integer expiresIn) {
        initialize(accessToken, expiresIn);
    }

    public Boolean isExpired() {
        if (expirationDate == null) return true;
        return LocalDateTime.now().isAfter(expirationDate);
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
