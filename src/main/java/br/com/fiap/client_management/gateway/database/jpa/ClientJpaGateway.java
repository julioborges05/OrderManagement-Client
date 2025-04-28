package br.com.fiap.client_management.gateway.database.jpa;

import br.com.fiap.client_management.domain.Client;
import br.com.fiap.client_management.gateway.ClientGateway;
import br.com.fiap.client_management.gateway.database.jpa.entity.ClientEntity;
import br.com.fiap.client_management.gateway.database.jpa.repository.ClientRepository;

import java.util.Optional;

public class ClientJpaGateway implements ClientGateway {

    private final ClientRepository clientRepository;

    public ClientJpaGateway(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client findByCpf(String cpf) {
        Optional<ClientEntity> clientEntity = clientRepository.findByCpf(cpf);

        return convertOptionalToDomain(clientEntity);
    }

    private Client convertOptionalToDomain(Optional<ClientEntity> clientEntity) {
        if (clientEntity.isEmpty()) {
            throw new IllegalArgumentException("Client not found");
        }

        return clientEntity.get().toDomain();
    }

    @Override
    public Client create(Client client) {
        if (client.getId() != null) {
            throw new IllegalArgumentException("Client already exists");
        }

        Optional<ClientEntity> optionalClient = clientRepository.findByCpf(client.getCpf());

        if (optionalClient.isPresent()) {
            throw new IllegalArgumentException("Client already exists");
        }

        ClientEntity clientEntity = clientRepository.save(new ClientEntity(client));
        return clientEntity.toDomain();
    }

    @Override
    public Client findById(Long id) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);

        return convertOptionalToDomain(clientEntity);
    }

    @Override
    public Client update(Client client) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(client.getId());

        if (clientEntity.isEmpty()) {
            throw new IllegalArgumentException("Client not found");
        }

        ClientEntity updatedClientEntity = clientRepository.save(new ClientEntity(client));

        return updatedClientEntity.toDomain();
    }

    @Override
    public void delete(Client client) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(client.getId());

        if (clientEntity.isEmpty()) {
            throw new IllegalArgumentException("Client not found");
        }

        clientEntity.get().setActive(false);
        clientRepository.save(clientEntity.get());
    }

    @Override
    public boolean getClientIsActive(Long clientId) {
        var clientEntity = clientRepository.findClientById(clientId);

        if (clientEntity.isEmpty()) {
            throw new IllegalArgumentException("Client not found");
        }

        return clientEntity.get().getIsActive();
    }
}
