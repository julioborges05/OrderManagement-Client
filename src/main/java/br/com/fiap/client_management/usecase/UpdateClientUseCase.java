package br.com.fiap.client_management.usecase;

import br.com.fiap.client_management.domain.Client;

public class UpdateClientUseCase {

    public static Client updateClient(Client oldClient, Client newClient) {
        validateOldClient(oldClient);
        validateNewClient(newClient);
        validateChanges(oldClient, newClient);

        return oldClient.updateFields(newClient);
    }

    private static void validateOldClient(Client oldClient) {
        if (oldClient == null) {
            throw new IllegalArgumentException("Old client is required");
        }
    }

    private static void validateNewClient(Client newClient) {
        if (newClient == null) {
            throw new IllegalArgumentException("New client is required");
        }
    }

    private static void validateChanges(Client oldClient, Client newClient) {
        if (oldClient.equals(newClient)) {
            throw new IllegalArgumentException("Old client is equal to new client");
        }
    }

}
