package br.com.fiap.client_management.domain;

import io.micrometer.common.util.StringUtils;

import java.util.Objects;

public class Address {

    private String street;
    private String number;
    private String complement;
    private String zipCode;
    private String city;

    public Address(String street, String number, String complement, String zipCode, String city) {
        validateAddressStreet(street);
        validateAddressNumber(number);
        validateAddressComplement(complement);
        validateAddressZipCode(zipCode);
        validateAddressCity(city);

        this.street = street;
        this.number = number;
        this.complement = complement;
        this.zipCode = zipCode;
        this.city = city;
    }

    private void validateAddressStreet(String street) {
        if (StringUtils.isBlank(street)) {
            throw new IllegalArgumentException("Street is required");
        }
    }

    private void validateAddressNumber(String number) {
        if (StringUtils.isBlank(number)) {
            throw new IllegalArgumentException("Number is required");
        }
    }

    private void validateAddressZipCode(String cep) {
        if (StringUtils.isBlank(cep)) {
            throw new IllegalArgumentException("Cep is required");
        }
    }

    private void validateAddressCity(String city) {
        if (StringUtils.isBlank(city)) {
            throw new IllegalArgumentException("City is required");
        }
    }

    private void validateAddressComplement(String complement) {
        if (complement != null && complement.isEmpty()) {
            throw new IllegalArgumentException("Complement is required");
        }
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;
        return Objects.equals(street, address.street)
                && Objects.equals(number, address.number)
                && Objects.equals(complement, address.complement)
                && Objects.equals(zipCode, address.zipCode)
                && Objects.equals(city, address.city);
    }

}
