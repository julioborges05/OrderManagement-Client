package br.com.fiap.client_management.usecase;

import br.com.fiap.client_management.entity.AddressEntity;
import br.com.fiap.client_management.entity.ClientEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateClientUseCaseTest {

    private final String defaultCpf = "12345678901";
    private final LocalDate defaultBirthDate = LocalDate.of(1980, 1, 6);
    private final AddressEntity defaultAddressEntity = new AddressEntity("Paulista Avenue", "1000", "Apt 123", "01310-100", "São Paulo");

    private final ClientEntity defaultOldClient = new ClientEntity("John Doe", defaultCpf, defaultBirthDate, defaultAddressEntity);
    private final ClientEntity defaultNewClient = new ClientEntity("John N. Doe", defaultCpf, defaultBirthDate, defaultAddressEntity);

    @Test
    void throwsExceptionWhenOldClientIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> UpdateClientUseCase.updateClient(null, this.defaultNewClient),
                "Old client is required"
        );
    }

    @Test
    void throwsExceptionWhenNewClientIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> UpdateClientUseCase.updateClient(this.defaultOldClient, null),
                "New client is required"
        );
    }

    @Test
    void throwsExceptionWhenOldClientIsEqualToNewClient() {
        ClientEntity newClient = new ClientEntity("John Doe", defaultCpf, defaultBirthDate, defaultAddressEntity);

        assertThrows(
                IllegalArgumentException.class,
                () -> UpdateClientUseCase.updateClient(this.defaultOldClient, newClient),
                "Old client is equal to new client"
        );
    }

    @Test
    void throwsExceptionWhenCpfIsChanged() {
        ClientEntity newClient = new ClientEntity("John Doe", "123", defaultBirthDate, defaultAddressEntity);

        assertThrows(
                IllegalArgumentException.class,
                () -> UpdateClientUseCase.updateClient(this.defaultOldClient, newClient),
                "Cpf cannot be changed"
        );
    }

    @Test
    void updatesClient() {
        ClientEntity updatedClient = UpdateClientUseCase.updateClient(this.defaultOldClient, this.defaultNewClient);

        assertEquals(this.defaultNewClient, updatedClient);
    }

}
