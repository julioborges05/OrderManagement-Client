package br.com.fiap.client_management.usecase;

import br.com.fiap.client_management.gateway.database.jpa.ClientJpaGateway;
import org.springframework.stereotype.Service;

@Service
public class GetClientIsActiveUseCase {

    private final ClientJpaGateway clientJpaGateway;

    public GetClientIsActiveUseCase(ClientJpaGateway clientGateway) {
        this.clientJpaGateway = clientGateway;
    }

    public boolean get(Long clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }
        if (clientId <= 0) {
            throw new IllegalArgumentException("Client ID must be greater than zero");
        }
        return clientJpaGateway.getClientIsActive(clientId);
    }
}
