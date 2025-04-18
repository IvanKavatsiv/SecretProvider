package com.kaiv.secprovider.providers;

import com.kaiv.secprovider.model.ProtocolType;
import com.kaiv.secprovider.model.SecretProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SecretProviderFactory {

    private final Map<ProtocolType, SecretProvider> providerMap;

    public SecretProviderFactory(List<SecretProvider> providers) {
        this.providerMap = providers.stream()
                .collect(Collectors.toMap(SecretProvider::getProtocolType, Function.identity()));
    }

    public SecretProvider getProvider(ProtocolType protocolType) {
        return Optional.ofNullable(providerMap.get(protocolType))
                       .orElseThrow(() -> new IllegalArgumentException("No provider for " + protocolType));
    }
}
