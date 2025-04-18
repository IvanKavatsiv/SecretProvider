package com.kaiv.secprovider.model;

public interface SecretProvider {
    ProtocolType getProtocolType();
    SecretData getSecret(String secretPath);
}