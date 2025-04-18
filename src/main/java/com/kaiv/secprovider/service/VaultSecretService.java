package com.kaiv.secprovider.service;

import com.kaiv.secprovider.model.ConnectionProtocol;
import com.kaiv.secprovider.model.SecretData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.support.VaultResponseSupport;

@Service
@RequiredArgsConstructor
public class VaultSecretService {

    @Value("${spring.application.vault-path}")
    private String vaultPath;

    private final VaultKeyValueOperations kvOperations;

    public void saveSecretToVault(ConnectionProtocol currentProtocol, SecretData secret) {

        String currentPath = vaultPath + ":" + currentProtocol.getProtocolType() + ":" + currentProtocol.getId();

        kvOperations.put(currentPath, secret);
    }

    public SecretData readSecretFromVault(String path) {

        VaultResponseSupport<SecretData> response = kvOperations.get(path, SecretData.class);

        if (response != null) {
            return response.getData();
        } else {
            throw new RuntimeException("Secret not found!");
        }
    }
}
