package br.com.fiap.client_management.entity;

import io.micrometer.common.util.StringUtils;

public class AddressEntity {

    private String street;
    private String number;
    private String complement;
    private String cep;
    private String city;

    public AddressEntity(String street, String number, String complement, String cep, String city) {
        validateAddressStreet(street);
        validateAddressNumber(number);
        validateAddressComplement(complement);
        validateAddressCep(cep);
        validateAddressCity(city);

        this.street = street;
        this.number = number;
        this.complement = complement;
        this.cep = cep;
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

    private void validateAddressCep(String cep) {
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

}
