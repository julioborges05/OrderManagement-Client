package br.com.fiap.client_management.usecase;

import br.com.fiap.client_management.entity.ClientEntity;

public class UpdateClientUseCase {

    public static ClientEntity updateClient(ClientEntity oldClient, ClientEntity newClient) {
        validateOldClient(oldClient);
        validateNewClient(newClient);
        validateChanges(oldClient, newClient);

        return oldClient.updateFields(newClient);
    }

    private static void validateOldClient(ClientEntity oldClient) {
        if (oldClient == null) {
            throw new IllegalArgumentException("Old client is required");
        }
    }

    private static void validateNewClient(ClientEntity newClient) {
        if (newClient == null) {
            throw new IllegalArgumentException("New client is required");
        }
    }

    private static void validateChanges(ClientEntity oldClient, ClientEntity newClient) {
        if (oldClient.equals(newClient)) {
            throw new IllegalArgumentException("Old client is equal to new client");
        }
    }

}
