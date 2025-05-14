package br.com.fiap.client_management.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ClientTest {

    private final String defaultName = "John Doe";
    private final String updatedName = "Jane Doe";
    private final String defaultCpf = "12345678901";
    private final LocalDate defaultBirthDate = LocalDate.of(1980, 1, 6);
    private final Address defaultAddress = new Address("Paulista Avenue", "1000", "Apt 123", "01310-100", "São Paulo");
    private final Address updatedAddress = new Address("Another Street", "200", "Apt 456", "12345-678", "Rio de Janeiro");

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
    void throwsExceptionWhenIdIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Client(null, "John Doe", "12345678901", LocalDate.of(1980, 1, 6),
                        new Address("Paulista Avenue", "1000", "Apt 123", "01310-100", "São Paulo")),
                "Id is required"
        );
    }

    @Test
    void throwsExceptionWhenIdIsZero() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Client(0L, "John Doe", "12345678901", LocalDate.of(1980, 1, 6),
                        new Address("Paulista Avenue", "1000", "Apt 123", "01310-100", "São Paulo")),
                "Id is required"
        );
    }

    @Test
    void throwsExceptionWhenIdIsNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Client(-1L, "John Doe", "12345678901", LocalDate.of(1980, 1, 6),
                        new Address("Paulista Avenue", "1000", "Apt 123", "01310-100", "São Paulo")),
                "Id is required"
        );
    }

    @Test
    void validIdDoesNotThrowException() {
        new Client(1L, "John Doe", "12345678901", LocalDate.of(1980, 1, 6),
                new Address("Paulista Avenue", "1000", "Apt 123", "01310-100", "São Paulo"));
    }

    @Test
    void updateFieldsSuccessfully() {
        Client client = new Client(1L, defaultName, defaultCpf, defaultBirthDate, defaultAddress);
        Client updatedClient = new Client(1L, updatedName, defaultCpf, defaultBirthDate, updatedAddress);

        client.updateFields(updatedClient);

        assertEquals(updatedName, client.getName());
        assertEquals(updatedAddress, client.getAddress());
    }

    @Test
    void throwsExceptionWhenIdIsChanged() {
        Client client = new Client(1L, defaultName, defaultCpf, defaultBirthDate, defaultAddress);
        Client updatedClient = new Client(2L, updatedName, defaultCpf, defaultBirthDate, updatedAddress);

        assertThrows(IllegalArgumentException.class, () -> client.updateFields(updatedClient), "Id cannot be changed");
    }

    @Test
    void throwsExceptionWhenCpfIsChanged() {
        Client client = new Client(1L, defaultName, defaultCpf, defaultBirthDate, defaultAddress);
        Client updatedClient = new Client(1L, updatedName, "98765432100", defaultBirthDate, updatedAddress);

        assertThrows(IllegalArgumentException.class, () -> client.updateFields(updatedClient), "Cpf cannot be changed");
    }

    @Test
    void throwsExceptionWhenBirthDateIsChanged() {
        Client client = new Client(1L, defaultName, defaultCpf, defaultBirthDate, defaultAddress);
        Client updatedClient = new Client(1L, updatedName, defaultCpf, LocalDate.of(1990, 1, 1), updatedAddress);

        assertThrows(IllegalArgumentException.class, () -> client.updateFields(updatedClient), "Birth date cannot be changed");
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

    @Test
    void equalsReturnsTrueForSameObject() {
        Client client = new Client(1L, defaultName, defaultCpf, defaultBirthDate, defaultAddress);

        assertTrue(client.equals(client));
    }

    @Test
    void equalsReturnsTrueForObjectsWithSameValues() {
        Client client1 = new Client(1L, defaultName, defaultCpf, defaultBirthDate, defaultAddress);
        Client client2 = new Client(1L, defaultName, defaultCpf, defaultBirthDate, defaultAddress);

        assertTrue(client1.equals(client2));
    }

    @Test
    void equalsReturnsFalseForObjectsWithDifferentNames() {
        Client client1 = new Client(1L, defaultName, defaultCpf, defaultBirthDate, defaultAddress);
        Client client2 = new Client(1L, "Jane Doe", defaultCpf, defaultBirthDate, defaultAddress);

        assertFalse(client1.equals(client2));
    }

    @Test
    void equalsReturnsFalseForObjectsWithDifferentCpfs() {
        Client client1 = new Client(1L, defaultName, defaultCpf, defaultBirthDate, defaultAddress);
        Client client2 = new Client(1L, defaultName, "98765432100", defaultBirthDate, defaultAddress);

        assertFalse(client1.equals(client2));
    }

    @Test
    void equalsReturnsFalseForObjectsWithDifferentBirthDates() {
        Client client1 = new Client(1L, defaultName, defaultCpf, defaultBirthDate, defaultAddress);
        Client client2 = new Client(1L, defaultName, defaultCpf, LocalDate.of(1990, 1, 1), defaultAddress);

        assertFalse(client1.equals(client2));
    }

    @Test
    void equalsReturnsFalseForObjectsWithDifferentAddresses() {
        Client client1 = new Client(1L, defaultName, defaultCpf, defaultBirthDate, defaultAddress);
        Address differentAddress = new Address("Another Street", "200", "Apt 456", "12345-678", "Rio de Janeiro");
        Client client2 = new Client(1L, defaultName, defaultCpf, defaultBirthDate, differentAddress);

        assertFalse(client1.equals(client2));
    }

    @Test
    void equalsReturnsFalseWhenComparingWithNull() {
        Client client = new Client(1L, defaultName, defaultCpf, defaultBirthDate, defaultAddress);

        assertFalse(client.equals(null));
    }

    @Test
    void equalsReturnsFalseWhenComparingWithDifferentClass() {
        Client client = new Client(1L, defaultName, defaultCpf, defaultBirthDate, defaultAddress);
        String otherObject = "Not a Client";

        assertFalse(client.equals(otherObject));
    }

}
