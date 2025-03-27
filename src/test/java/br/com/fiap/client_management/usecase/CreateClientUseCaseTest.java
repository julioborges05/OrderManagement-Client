package br.com.fiap.client_management.usecase;

import br.com.fiap.client_management.entity.AddressEntity;
import br.com.fiap.client_management.entity.ClientEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CreateClientUseCaseTest {

    private final String defaultStreet = "Paulista Avenue";
    private final String defaultNumber = "1000";
    private final String defaultComplement = "Apt 123";
    private final String defaultCep = "01310-100";
    private final String defaultCity = "São Paulo";

    private final String defaultName = "John Doe";
    private final String defaultCpf = "12345678901";
    private final LocalDate defaultBirthDate = LocalDate.of(1980, 1, 6);

    @Test
    void shouldCreateClient() {
        var result = CreateClientUseCase.createClient(this.defaultName, this.defaultCpf, this.defaultBirthDate,
                this.defaultStreet, this.defaultNumber, this.defaultComplement, this.defaultCep, this.defaultCity);

        assertInstanceOf(ClientEntity.class, result);
    }

}
