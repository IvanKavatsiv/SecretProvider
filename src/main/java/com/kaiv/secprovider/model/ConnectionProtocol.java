package com.kaiv.secprovider.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ConnectionProtocol {
    private String id;
    private ProtocolType protocolType;
}
