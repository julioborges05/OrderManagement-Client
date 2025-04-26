package br.com.fiap.client_management.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressTest {

    private final String defaultStreet = "Paulista Avenue";
    private final String defaultNumber = "1000";
    private final String defaultComplement = "Apt 123";
    private final String defaultZipCode = "01310-100";
    private final String defaultCity = "São Paulo";

    @Test
    void throwsExceptionWhenStreetIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Address(null, this.defaultNumber, this.defaultComplement, this.defaultZipCode, this.defaultCity),
                "Street is required"
        );
    }

    @Test
    void throwsExceptionWhenStreetIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Address("", this.defaultNumber, this.defaultComplement, this.defaultZipCode, this.defaultCity),
                "Street is required"
        );
    }

    @Test
    void throwsExceptionWhenNumberIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Address(this.defaultStreet, null, this.defaultComplement, this.defaultZipCode, this.defaultCity),
                "Number is required"
        );
    }

    @Test
    void throwsExceptionWhenNumberIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Address(this.defaultStreet, "", this.defaultComplement, this.defaultZipCode, this.defaultCity),
                "Number is required"
        );
    }

    @Test
    void throwsExceptionWhenZipCodeIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Address(this.defaultStreet, this.defaultNumber, this.defaultComplement, null, this.defaultCity),
                "Cep is required"
        );
    }

    @Test
    void throwsExceptionWhenZipCodeIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Address(this.defaultStreet, this.defaultNumber, this.defaultComplement, "", this.defaultCity),
                "Cep is required"
        );
    }

    @Test
    void throwsExceptionWhenCityIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Address(this.defaultStreet, this.defaultNumber, this.defaultComplement, this.defaultZipCode, null),
                "City is required"
        );
    }

    @Test
    void throwsExceptionWhenCityIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Address(this.defaultStreet, this.defaultNumber, this.defaultComplement, this.defaultZipCode, ""),
                "City is required"
        );
    }

    @Test
    void throwsExceptionWhenComplementIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Address(this.defaultStreet, this.defaultNumber, "", this.defaultZipCode, this.defaultCity),
                "Complement is required"
        );
    }

    @Test
    void validAddress() {
        Address address = new Address(this.defaultStreet, this.defaultNumber, this.defaultComplement, this.defaultZipCode, this.defaultCity);

        assertEquals(this.defaultStreet, address.getStreet());
        assertEquals(this.defaultNumber, address.getNumber());
        assertEquals(this.defaultComplement, address.getComplement());
        assertEquals(this.defaultZipCode, address.getZipCode());
        assertEquals(this.defaultCity, address.getCity());
    }

}
