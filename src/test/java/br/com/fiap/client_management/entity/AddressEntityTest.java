package br.com.fiap.client_management.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressEntityTest {

    private final String defaultStreet = "Paulista Avenue";
    private final String defaultNumber = "1000";
    private final String defaultComplement = "Apt 123";
    private final String defaultCep = "01310-100";
    private final String defaultCity = "São Paulo";

    @Test
    void throwsExceptionWhenStreetIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AddressEntity(null, this.defaultNumber, this.defaultComplement, this.defaultCep, this.defaultCity),
                "Street is required"
        );
    }

    @Test
    void throwsExceptionWhenStreetIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AddressEntity("", this.defaultNumber, this.defaultComplement, this.defaultCep, this.defaultCity),
                "Street is required"
        );
    }

    @Test
    void throwsExceptionWhenNumberIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AddressEntity(this.defaultStreet, null, this.defaultComplement, this.defaultCep, this.defaultCity),
                "Number is required"
        );
    }

    @Test
    void throwsExceptionWhenNumberIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AddressEntity(this.defaultStreet, "", this.defaultComplement, this.defaultCep, this.defaultCity),
                "Number is required"
        );
    }

    @Test
    void throwsExceptionWhenCepIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AddressEntity(this.defaultStreet, this.defaultNumber, this.defaultComplement, null, this.defaultCity),
                "Cep is required"
        );
    }

    @Test
    void throwsExceptionWhenCepIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AddressEntity(this.defaultStreet, this.defaultNumber, this.defaultComplement, "", this.defaultCity),
                "Cep is required"
        );
    }

    @Test
    void throwsExceptionWhenCityIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AddressEntity(this.defaultStreet, this.defaultNumber, this.defaultComplement, this.defaultCep, null),
                "City is required"
        );
    }

    @Test
    void throwsExceptionWhenCityIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AddressEntity(this.defaultStreet, this.defaultNumber, this.defaultComplement, this.defaultCep, ""),
                "City is required"
        );
    }

    @Test
    void throwsExceptionWhenComplementIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AddressEntity(this.defaultStreet, this.defaultNumber, "", this.defaultCep, this.defaultCity),
                "Complement is required"
        );
    }

}
