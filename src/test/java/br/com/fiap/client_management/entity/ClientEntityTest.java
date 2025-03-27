package br.com.fiap.client_management.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClientEntityTest {

    private final String defaultName = "John Doe";
    private final String defaultCpf = "12345678901";
    private final LocalDate defaultBirthDate = LocalDate.of(1980, 1, 6);
    private final AddressEntity defaultAddressEntity = new AddressEntity("Paulista Avenue", "1000", "Apt 123", "01310-100", "São Paulo");

    @Test
    void throwsExceptionWhenNameIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ClientEntity(null, this.defaultCpf, this.defaultBirthDate, this.defaultAddressEntity),
                "Name is required"
        );
    }

    @Test
    void throwsExceptionWhenNameIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ClientEntity("", this.defaultCpf, this.defaultBirthDate, this.defaultAddressEntity),
                "Name is required"
        );
    }

    @Test
    void throwsExceptionWhenCpfIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ClientEntity(this.defaultName, null, this.defaultBirthDate, this.defaultAddressEntity),
                "Cpf is required"
        );
    }

    @Test
    void throwsExceptionWhenCpfIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ClientEntity(this.defaultName, "", this.defaultBirthDate, this.defaultAddressEntity),
                "Cpf is required"
        );
    }

    @Test
    void throwsExceptionWhenBirthDateIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ClientEntity(this.defaultName, this.defaultCpf, null, this.defaultAddressEntity),
                "Birth date is required"
        );
    }

    @Test
    void throwsExceptionWhenBirthDateIsInTheFuture() {
        LocalDate futureDate = LocalDate.now().plusDays(1);

        assertThrows(
                IllegalArgumentException.class,
                () -> new ClientEntity(this.defaultName, this.defaultCpf, futureDate, this.defaultAddressEntity),
                "Birth date must be in the past"
        );
    }

    @Test
    void throwsExceptionWhenAddressIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ClientEntity(this.defaultName, this.defaultCpf, this.defaultBirthDate, null),
                "Address is required"
        );
    }

}
