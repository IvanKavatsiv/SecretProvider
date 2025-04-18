package com.kaiv.secprovider.providers;

import com.kaiv.secprovider.model.ProtocolType;
import com.kaiv.secprovider.model.SecretData;
import com.kaiv.secprovider.model.SecretProvider;
import com.kaiv.secprovider.service.VaultSecretService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FtpSecretProvider implements SecretProvider {

    private final VaultSecretService vaultSecretService;

    @Override
    public ProtocolType getProtocolType() {
        return ProtocolType.FTP;
    }

    @Override
    public SecretData getSecret(String secretPath) {
        return vaultSecretService.readSecretFromVault(secretPath);
    }

}
