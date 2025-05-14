package br.com.fiap.client_management.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void validAddressComplement() {
        Address address = new Address(this.defaultStreet, this.defaultNumber, null, this.defaultZipCode, this.defaultCity);

        assertEquals(this.defaultStreet, address.getStreet());
        assertEquals(this.defaultNumber, address.getNumber());
        assertNull(address.getComplement());
        assertEquals(this.defaultZipCode, address.getZipCode());
        assertEquals(this.defaultCity, address.getCity());
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

    @Test
    void equalsReturnsTrueForSameObject() {
        Address address = new Address(defaultStreet, defaultNumber, defaultComplement, defaultZipCode, defaultCity);

        assertEquals(address, address);
    }

    @Test
    void equalsReturnsTrueForEqualObjects() {
        Address address1 = new Address(defaultStreet, defaultNumber, defaultComplement, defaultZipCode, defaultCity);
        Address address2 = new Address(defaultStreet, defaultNumber, defaultComplement, defaultZipCode, defaultCity);

        assertEquals(address1, address2);
    }

    @Test
    void equalsReturnsFalseForDifferentStreet() {
        Address address1 = new Address(defaultStreet, defaultNumber, defaultComplement, defaultZipCode, defaultCity);
        Address address2 = new Address("Different Street", defaultNumber, defaultComplement, defaultZipCode, defaultCity);

        assertNotEquals(address1, address2);
    }

    @Test
    void equalsReturnsFalseForDifferentNumber() {
        Address address1 = new Address(defaultStreet, defaultNumber, defaultComplement, defaultZipCode, defaultCity);
        Address address2 = new Address(defaultStreet, "2000", defaultComplement, defaultZipCode, defaultCity);

        assertNotEquals(address1, address2);
    }

    @Test
    void equalsReturnsFalseForDifferentComplement() {
        Address address1 = new Address(defaultStreet, defaultNumber, defaultComplement, defaultZipCode, defaultCity);
        Address address2 = new Address(defaultStreet, defaultNumber, "Different Complement", defaultZipCode, defaultCity);

        assertNotEquals(address1, address2);
    }

    @Test
    void equalsReturnsFalseForDifferentZipCode() {
        Address address1 = new Address(defaultStreet, defaultNumber, defaultComplement, defaultZipCode, defaultCity);
        Address address2 = new Address(defaultStreet, defaultNumber, defaultComplement, "00000-000", defaultCity);

        assertNotEquals(address1, address2);
    }

    @Test
    void equalsReturnsFalseForDifferentCity() {
        Address address1 = new Address(defaultStreet, defaultNumber, defaultComplement, defaultZipCode, defaultCity);
        Address address2 = new Address(defaultStreet, defaultNumber, defaultComplement, defaultZipCode, "Rio de Janeiro");

        assertNotEquals(address1, address2);
    }

    @Test
    void equalsReturnsFalseForNullObject() {
        Address address = new Address(defaultStreet, defaultNumber, defaultComplement, defaultZipCode, defaultCity);

        assertNotEquals(address, null);
    }

    @Test
    void equalsReturnsFalseForDifferentClass() {
        Address address = new Address(defaultStreet, defaultNumber, defaultComplement, defaultZipCode, defaultCity);

        assertNotEquals(address, "Some String");
    }

}
