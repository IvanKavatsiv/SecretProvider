package com.kaiv.secprovider;

import com.kaiv.secprovider.model.ConnectionProtocol;
import com.kaiv.secprovider.model.ProtocolType;
import com.kaiv.secprovider.model.SecretData;
import com.kaiv.secprovider.service.SecretProviderService;
import com.kaiv.secprovider.service.VaultSecretService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class App implements CommandLineRunner {

    private final VaultSecretService vaultSecretService;

    private final SecretProviderService secretProviderService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {

        // create Test secret Object
        SecretData secret = new SecretData();
        secret.setUsername("MyUsername");
        secret.setPassword("MyPassword");

        // create and get Test Protocol Objects
        List<ConnectionProtocol> testProtocolsList = getListOfProtocols();

        // save credentials of test Protocols Objects with test secret to Vault
        testProtocolsList.forEach(currentProtocol -> vaultSecretService.saveSecretToVault(currentProtocol, secret));

        // print credentials of test Protocol Objects
        testProtocolsList.forEach(currentProtocol -> System.out.println(currentProtocol.toString() + "-" + secretProviderService.getCredentialsForProtocol(currentProtocol)));

    }

    private List<ConnectionProtocol> getListOfProtocols() {

        ConnectionProtocol connectionEntityFtp = ConnectionProtocol.builder()
                .id("1")
                .protocolType(ProtocolType.FTP)
                .build();

        ConnectionProtocol connectionEntityFtp2 = ConnectionProtocol.builder()
                .id("2")
                .protocolType(ProtocolType.FTP)
                .build();

        ConnectionProtocol connectionEntityRdp = ConnectionProtocol.builder()
                .id("1")
                .protocolType(ProtocolType.RDP)
                .build();

        ConnectionProtocol connectionEntityRdp2 = ConnectionProtocol.builder()
                .id("2")
                .protocolType(ProtocolType.RDP)
                .build();

        ConnectionProtocol connectionEntitySmb = ConnectionProtocol.builder()
                .id("1")
                .protocolType(ProtocolType.SMB)
                .build();

        ConnectionProtocol connectionEntitySmb2 = ConnectionProtocol.builder()
                .id("2")
                .protocolType(ProtocolType.SMB)
                .build();

        List<ConnectionProtocol> testProtocolsList = List.of(connectionEntityFtp, connectionEntityFtp2, connectionEntityRdp, connectionEntityRdp2, connectionEntitySmb, connectionEntitySmb2);

        return testProtocolsList;
    }
}
