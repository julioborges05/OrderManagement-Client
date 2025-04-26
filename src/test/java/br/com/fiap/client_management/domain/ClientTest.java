package br.com.fiap.client_management.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClientTest {

    private final String defaultName = "John Doe";
    private final String defaultCpf = "12345678901";
    private final LocalDate defaultBirthDate = LocalDate.of(1980, 1, 6);
    private final Address defaultAddress = new Address("Paulista Avenue", "1000", "Apt 123", "01310-100", "São Paulo");

    @Test
    void throwsExceptionWhenNameIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Client(null, this.defaultCpf, this.defaultBirthDate, this.defaultAddress),
                "Name is required"
        );
    }

    @Test
    void throwsExceptionWhenNameIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Client("", this.defaultCpf, this.defaultBirthDate, this.defaultAddress),
                "Name is required"
        );
    }

    @Test
    void throwsExceptionWhenCpfIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Client(this.defaultName, null, this.defaultBirthDate, this.defaultAddress),
                "Cpf is required"
        );
    }

    @Test
    void throwsExceptionWhenCpfIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Client(this.defaultName, "", this.defaultBirthDate, this.defaultAddress),
                "Cpf is required"
        );
    }

    @Test
    void throwsExceptionWhenBirthDateIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Client(this.defaultName, this.defaultCpf, null, this.defaultAddress),
                "Birth date is required"
        );
    }

    @Test
    void throwsExceptionWhenBirthDateIsInTheFuture() {
        LocalDate futureDate = LocalDate.now().plusDays(1);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Client(this.defaultName, this.defaultCpf, futureDate, this.defaultAddress),
                "Birth date must be in the past"
        );
    }

    @Test
    void throwsExceptionWhenAddressIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Client(this.defaultName, this.defaultCpf, this.defaultBirthDate, null),
                "Address is required"
        );
    }

    @Test
    void validClient() {
        Client client = new Client(1L, this.defaultName, this.defaultCpf, this.defaultBirthDate, this.defaultAddress);

        assertEquals(1L, client.getId());
        assertEquals(this.defaultName, client.getName());
        assertEquals(this.defaultCpf, client.getCpf());
        assertEquals(this.defaultBirthDate, client.getBirthDate());
        assertEquals(this.defaultAddress, client.getAddress());
    }

}
