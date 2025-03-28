package br.com.fiap.client_management.usecase;

import br.com.fiap.client_management.domain.Address;
import br.com.fiap.client_management.domain.Client;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateClientUseCaseTest {

    private final String defaultCpf = "12345678901";
    private final LocalDate defaultBirthDate = LocalDate.of(1980, 1, 6);
    private final Address defaultAddress = new Address("Paulista Avenue", "1000", "Apt 123", "01310-100", "São Paulo");

    private final Client defaultOldClient = new Client("John Doe", defaultCpf, defaultBirthDate, defaultAddress);
    private final Client defaultNewClient = new Client("John N. Doe", defaultCpf, defaultBirthDate, defaultAddress);

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
        Client newClient = new Client("John Doe", defaultCpf, defaultBirthDate, defaultAddress);

        assertThrows(
                IllegalArgumentException.class,
                () -> UpdateClientUseCase.updateClient(this.defaultOldClient, newClient),
                "Old client is equal to new client"
        );
    }

    @Test
    void throwsExceptionWhenCpfIsChanged() {
        Client newClient = new Client("John Doe", "123", defaultBirthDate, defaultAddress);

        assertThrows(
                IllegalArgumentException.class,
                () -> UpdateClientUseCase.updateClient(this.defaultOldClient, newClient),
                "Cpf cannot be changed"
        );
    }

    @Test
    void updatesClient() {
        Client updatedClient = UpdateClientUseCase.updateClient(this.defaultOldClient, this.defaultNewClient);

        assertEquals(this.defaultNewClient, updatedClient);
    }

}
