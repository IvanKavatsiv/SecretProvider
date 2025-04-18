package com.kaiv.secprovider.service;

import com.kaiv.secprovider.model.ConnectionProtocol;
import com.kaiv.secprovider.model.ProtocolType;
import com.kaiv.secprovider.model.SecretData;
import com.kaiv.secprovider.model.SecretProvider;
import com.kaiv.secprovider.providers.SecretProviderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecretProviderService {

    @Value("${spring.application.vault-path}")
    private String vaultGeneralPath;

    private final SecretProviderFactory secretProviderFactory;

    public SecretData getCredentialsForProtocol(ConnectionProtocol connectionProtocol) {
        ProtocolType protocolType = connectionProtocol.getProtocolType();
        SecretProvider provider = secretProviderFactory.getProvider(protocolType);
        String currentPath = vaultGeneralPath + ":" + protocolType + ":" + connectionProtocol.getId();
        return provider.getSecret(currentPath);
    }
}
